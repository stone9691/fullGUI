package com.gammakite.fullchinese.view.text;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.gammakite.fullchinese.R;

public class TextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            LayoutInflater mInflater = LayoutInflater.from(this);
            View mCustomView = mInflater.inflate(R.layout.text_view_action_bar, null);
            actionBar.setCustomView(mCustomView);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }
}
