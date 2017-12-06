package com.example.stone.uidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PageNavigationView tab = (PageNavigationView) findViewById(R.id.main_activity_bottom_tab);

        NavigationController navigationController = tab.material()
                .addItem(android.R.drawable.ic_menu_camera, "Texts")
                .addItem(android.R.drawable.ic_menu_compass, "Decks")
                .addItem(android.R.drawable.ic_menu_search, "Dictionary")
                .addItem(android.R.drawable.ic_menu_help, "Store")
                .addItem(android.R.drawable.ic_menu_more, "More")
                .build();

        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                //选中时触发
            }

            @Override
            public void onRepeat(int index) {
                //重复选中时触发
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
