package com.gammakite.fullchinese.view.text;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gammakite.fullchinese.R;
import com.gammakite.fullchinese.event.TextDownloadEvent;
import com.gammakite.fullchinese.object.Text;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import lib.kingja.switchbutton.SwitchMultiButton;

public class TextsFragment extends Fragment {

    private ListView mListView;
    private ItemAdapter mAdapter;
    private List<Text> mTextList;
    private List<Text> mDisplayTextList;

    private final int SWITCH_TAB_DEVICE = 0;
    private final int SWITCH_TAB_CLOUD = 1;
    private int mCurrentTab = SWITCH_TAB_DEVICE;

    public TextsFragment() {
        // Required empty public constructor
    }

    public static TextsFragment newInstance() {
        TextsFragment fragment = new TextsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_texts, container, false);

        mTextList = new ArrayList<>();
        mDisplayTextList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            mTextList.add(new Text(i, "让子弹飞(" + i + ")", "Movies", "12/5/17", false));
        }

        for (int i = 5; i <= 30; i++) {
            mTextList.add(new Text(i, "让子弹飞(" + i + ")", "Movies", "12/5/17", true));
        }

        if (mCurrentTab == SWITCH_TAB_DEVICE) {
            for (Text t : mTextList) {
                if (!t.getIsCloud()) {
                    mDisplayTextList.add(t);
                }
            }
        } else {
            mDisplayTextList.addAll(mTextList);
        }

        mListView = (ListView) view.findViewById(R.id.fragment_texts_list_texts);
        mAdapter = new ItemAdapter(getActivity(),
                R.layout.fragment_texts_list_text, mDisplayTextList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Text text = mAdapter.getItem(position);
                if (text != null) {
                    if (text.getIsCloud()) {
                        EventBus.getDefault().post(new TextDownloadEvent(text.getId()));
                    } else {
                        Intent intent = new Intent(getActivity(), TextViewActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        SwitchMultiButton mSwitchMultiButton = (SwitchMultiButton) view.findViewById(R.id.fragment_texts_switch);
        mSwitchMultiButton.setText("Device", "Cloud").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if (mCurrentTab != position) {
                    mCurrentTab = position;
                    mAdapter.clear();
                    mDisplayTextList.clear();

                    if (mCurrentTab == SWITCH_TAB_DEVICE) {
                        for (Text t : mTextList) {
                            if (!t.getIsCloud()) {
                                mDisplayTextList.add(t);
                            }
                        }
                    } else {
                        mDisplayTextList.addAll(mTextList);
                    }

                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TextDownloadEvent event) {
        if (!event.completed) {
            // TODO start to download text
            for (Text t : mTextList) {
                if (t.getId() == event.id) {
                    t.setIsDownloading(true);
                    mAdapter.notifyDataSetChanged();
                    final long id = t.getId();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                                EventBus.getDefault().post(new TextDownloadEvent(id, true));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    break;
                }
            }
        } else {
            for (Text t : mTextList) {
                if (t.getId() == event.id) {
                    t.setIsCloud(false);
                    t.setIsDownloading(false);
                    mAdapter.notifyDataSetChanged();
                    Intent intent = new Intent(getActivity(), TextViewActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        }
    }

    public class ItemAdapter extends ArrayAdapter<Text> {
        private List<Text> objects;

        public ItemAdapter(Context context, int textViewResourceId, List<Text> objects) {
            super(context, textViewResourceId, objects);
            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater inflater =
                        (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.fragment_texts_list_text, null);
            }

            Text text = objects.get(position);

            TextView tv;
            ImageView image;
            AVLoadingIndicatorView avi;
            if (text != null) {
                tv = v.findViewById(R.id.fragment_texts_list_title);
                if (tv != null) {
                    tv.setText(text.getTitle());
                }

                tv = v.findViewById(R.id.fragment_texts_list_type);
                if (tv != null) {
                    tv.setText(text.getType());
                }

                tv = v.findViewById(R.id.fragment_texts_list_date);
                if (tv != null) {
                    tv.setText(text.getDate());
                }

                if (text.getIsDownloading()) {

                }
                image = v.findViewById(R.id.fragment_texts_list_download);
                avi = v.findViewById(R.id.fragment_texts_list_downloading);
                if (image != null && avi != null) {
                    if (text.getIsDownloading()) {
                        image.setVisibility(View.INVISIBLE);
                        avi.setVisibility(View.VISIBLE);
                        avi.show();
                    } else {
                        image.setVisibility(View.VISIBLE);
                        avi.setVisibility(View.INVISIBLE);
                        avi.hide();
                        if (text.getIsCloud()) {
                            image.setImageResource(R.drawable.ic_download);
                        } else {
                            image.setImageResource(R.drawable.ic_download_finish);
                        }
                    }
                }
            }


            return v;
        }
    }
}
