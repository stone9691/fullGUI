package com.gammakite.fullchinese.view.dictionary;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gammakite.fullchinese.R;

public class DictionaryActivity extends AppCompatActivity implements View.OnClickListener {

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
                break;
        }
    }
}
