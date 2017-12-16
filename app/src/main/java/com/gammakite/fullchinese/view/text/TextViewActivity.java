package com.gammakite.fullchinese.view.text;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gammakite.fullchinese.R;
import com.gammakite.fullchinese.view.dictionary.DictionaryActivity;

public class TextViewActivity extends AppCompatActivity implements View.OnClickListener {

    private final String text = "1998年的法兰西之夏，法国在本土获得了世界杯冠军奖杯，2年之后，他们又成功获得了欧洲杯冠军奖杯，布兰科在这个过程中都是球队的关键一员。在俱乐部层面，布兰科随欧塞尔和曼联获得了联赛冠军，随蒙彼利埃和巴萨获得了国内杯赛的冠军，并且他还随加泰罗尼亚豪门获得过欧洲优胜者杯的冠军。\n" +
            "然而，布兰科职业生涯共效力9家俱乐部，均未能染指欧冠冠军，他效力的其他俱乐部还包括了那不勒斯、国米和马赛等。执教巴黎圣日耳曼期间，由于未能让球队在欧冠中更进一步，俱乐部选择将他解雇。科库在欧冠中共出场过79次，起初他代表的是荷甲豪门埃因霍温，随后他在效力巴萨6年期间又多次在欧冠中出场。\n" +
            "遗憾的是，在巴萨在欧冠中崛起之前，科库在2004年离开了诺坎普。两年之后，巴萨就获得了新世纪4个欧冠冠军奖杯中的第一个。科库职业生涯曾获得5个联赛冠军奖杯，但却和欧冠冠军奖杯失之交臂。目前，科库在自己球员时代的老东家埃因霍温执教。";
    private SelectionTextView mSelectionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mSelectionTextView = findViewById(R.id.activity_text_view_content);
        if (mSelectionTextView != null) {
            mSelectionTextView.setText(text);
            mSelectionTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        }

        ImageView imageView = findViewById(R.id.activity_text_view_left);
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }

        imageView = findViewById(R.id.activity_text_view_right);
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }

        imageView = findViewById(R.id.text_view_bar_icon);
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }

        View view = findViewById(R.id.activity_text_view_dictionary);
        if (view != null) {
            view.setOnClickListener(this);
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String title = bundle.getString("title");
            if (title != null && title.length() > 0) {
                TextView textView = findViewById(R.id.text_view_bar_title);
                if (textView != null) {
                    textView.setText(title);
                }
            }
        }
    }

    private void startDictionaryActivity() {
        TextView textView = findViewById(R.id.activity_text_view_selected_word);
        if (textView != null) {
            if (textView.getText().length() > 0) {
                Intent intent = new Intent(this, DictionaryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("word", textView.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, getString(R.string.please_select_a_word), Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_text_view_left:
                if (mSelectionTextView != null) {
                    mSelectionTextView.moveLeft();
                }
                break;
            case R.id.activity_text_view_right:
                if (mSelectionTextView != null) {
                    mSelectionTextView.moveRight();
                }
                break;
            case R.id.text_view_bar_icon:
                finish();
                break;
            case R.id.activity_text_view_dictionary:
                startDictionaryActivity();
                break;
            default:
                break;
        }
    }
}
