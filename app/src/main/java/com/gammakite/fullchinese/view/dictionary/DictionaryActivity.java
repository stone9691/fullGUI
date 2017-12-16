package com.gammakite.fullchinese.view.dictionary;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gammakite.fullchinese.R;
import com.gammakite.fullchinese.object.Sentence;

import java.util.ArrayList;
import java.util.List;

public class DictionaryActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private ItemAdapter mAdapter;
    private List<Sentence> mSentenceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        ImageView imageView = findViewById(R.id.dictionary_bar_icon);
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }

        imageView = findViewById(R.id.activity_dictionary_star);
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }

        View view = findViewById(R.id.activity_dictionary_play);
        if (view != null) {
            view.setOnClickListener(this);
        }

        final String word = "早上";
        mSentenceList = new ArrayList<>();
        mSentenceList.add(new Sentence(0, word,"他早上给花园浇水。", "He waters the garden in the morning."));
        mSentenceList.add(new Sentence(1, word,"大家早上好！", "Good morning everyone!"));
        mSentenceList.add(new Sentence(2, word,"我每天早上六点半起床。", "I get up at six thirty every morning."));
        mSentenceList.add(new Sentence(3, word,"那天早上，冰雹下得厉害。", "That morning, it hailed heavily."));
        mSentenceList.add(new Sentence(4, word,"今天早上吃什么了？", "What did you have this morning?"));
        mSentenceList.add(new Sentence(5, word,"那天早上很冷。", "It was very cold that morning."));

        mListView = findViewById(R.id.activity_dictionary_list_sentences);
        mAdapter = new ItemAdapter(this,
                R.layout.activity_dictionary_list_setence, mSentenceList);
        mListView.setAdapter(mAdapter);

        View layout = findViewById(R.id.activity_dictionary_seperated_word);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 16, 0);
        for (int i = 0; i < word.length(); i++) {
            TextView textView = new TextView(this);
            textView.setText(word.substring(i, i + 1));
            textView.setId(i);
            textView.setLayoutParams(params);
            textView.setTextColor(ContextCompat.getColor(this, R.color.Black));
            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
            textView.setOnClickListener(this);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
            textView.setPadding(4, 4, 4, 4);

            ((LinearLayout) layout).addView(textView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dictionary_bar_icon:
                finish();
                break;
            case R.id.activity_dictionary_star:
                break;
            case R.id.activity_dictionary_play:
                break;
            default:
                Toast.makeText(this, "under development :)", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public class ItemAdapter extends ArrayAdapter<Sentence> {
        private List<Sentence> objects;

        public ItemAdapter(Context context, int textViewResourceId, List<Sentence> objects) {
            super(context, textViewResourceId, objects);
            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater inflater =
                        (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.activity_dictionary_list_setence, null);
            }

            Sentence sentence = objects.get(position);

            if (sentence != null) {
                TextView textView;
                textView = v.findViewById(R.id.activity_dictionary_list_sentences_chinese);
                if (textView != null) {
                    textView.setText(sentence.getChinese());
                }

                textView = v.findViewById(R.id.activity_dictionary_list_sentences_english);
                if (textView != null) {
                    textView.setText(sentence.getEnglish());
                }
            }

            return v;
        }
    }
}
