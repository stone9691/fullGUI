package com.gammakite.fullchinese.view.text;

import android.content.Context;
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
import android.widget.Toast;

import com.gammakite.fullchinese.R;
import com.gammakite.fullchinese.object.Text;

import java.util.ArrayList;
import java.util.List;

import lib.kingja.switchbutton.SwitchMultiButton;

public class TextsFragment extends Fragment {

    private ListView mListView;
    private ItemAdapter mAdapter;
    private List<Text> mTextList;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_texts, container, false);

        mTextList = new ArrayList<Text>();
        for (int i = 1; i <= 50; i++) {
            if (i % 3 == 0) {
                mTextList.add(new Text("让子弹飞(" + i + ")", "Movies", "12/5/17", true));
            } else {
                mTextList.add(new Text("让子弹飞(" + i + ")", "Movies", "12/5/17", false));
            }
        }

        mListView = (ListView) view.findViewById(R.id.fragment_texts_list_texts);
        mAdapter = new ItemAdapter(getActivity(),
                R.layout.fragment_texts_list_text, mTextList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Text text = mAdapter.getItem(position);
                if (text != null) {
                    // TODO
                }
            }
        });

        SwitchMultiButton mSwitchMultiButton = (SwitchMultiButton) view.findViewById(R.id.fragment_texts_switch);
        mSwitchMultiButton.setText("Device", "Cloud").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                Toast.makeText(getActivity(), tabText, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
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
            if (text != null) {
                tv = (TextView) v.findViewById(R.id.fragment_texts_list_title);
                if (tv != null) {
                    tv.setText(text.getTitle());
                }

                tv = (TextView) v.findViewById(R.id.fragment_texts_list_type);
                if (tv != null) {
                    tv.setText(text.getType());
                }

                tv = (TextView) v.findViewById(R.id.fragment_texts_list_date);
                if (tv != null) {
                    tv.setText(text.getDate());
                }

                image = (ImageView) v.findViewById(R.id.fragment_texts_list_download);
                if (image != null) {
                    if (text.getIsCloud()) {
                        image.setImageResource(R.drawable.ic_download);
                    } else {
                        image.setImageResource(R.drawable.ic_download_finish);
                    }
                }
            }


            return v;
        }
    }
}
