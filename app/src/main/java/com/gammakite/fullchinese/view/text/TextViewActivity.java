package com.gammakite.fullchinese.view.text;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.gammakite.fullchinese.R;

public class TextViewActivity extends AppCompatActivity {

    private final String text = "1998年的法兰西之夏，法国在本土获得了世界杯冠军奖杯，2年之后，他们又成功获得了欧洲杯冠军奖杯，布兰科在这个过程中都是球队的关键一员。在俱乐部层面，布兰科随欧塞尔和曼联获得了联赛冠军，随蒙彼利埃和巴萨获得了国内杯赛的冠军，并且他还随加泰罗尼亚豪门获得过欧洲优胜者杯的冠军。\n" +
            "然而，布兰科职业生涯共效力9家俱乐部，均未能染指欧冠冠军，他效力的其他俱乐部还包括了那不勒斯、国米和马赛等。执教巴黎圣日耳曼期间，由于未能让球队在欧冠中更进一步，俱乐部选择将他解雇。科库在欧冠中共出场过79次，起初他代表的是荷甲豪门埃因霍温，随后他在效力巴萨6年期间又多次在欧冠中出场。\n" +
            "遗憾的是，在巴萨在欧冠中崛起之前，科库在2004年离开了诺坎普。两年之后，巴萨就获得了新世纪4个欧冠冠军奖杯中的第一个。科库职业生涯曾获得5个联赛冠军奖杯，但却和欧冠冠军奖杯失之交臂。目前，科库在自己球员时代的老东家埃因霍温执教。";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        SelectionTextView tv = findViewById(R.id.activity_text_view_content);
        if (tv != null) {
            tv.setText(text);
            tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }
}
