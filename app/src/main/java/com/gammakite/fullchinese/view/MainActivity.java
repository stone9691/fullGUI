package com.gammakite.fullchinese.view;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.gammakite.fullchinese.R;
import com.gammakite.fullchinese.view.decks.DecksFragment;
import com.gammakite.fullchinese.view.dictionary.DictionaryFragment;
import com.gammakite.fullchinese.view.more.MoreFragment;
import com.gammakite.fullchinese.view.store.StoreFragment;
import com.gammakite.fullchinese.view.text.TextsFragment;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends AppCompatActivity {

    private final int BOTTOM_TAB_TEXTS = 0;
    private final int BOTTOM_TAB_DECKS = 1;
    private final int BOTTOM_TAB_DICTIONARY = 2;
    private final int BOTTOM_TAB_STORE = 3;
    private final int BOTTOM_TAB_MORE = 4;

    private TextsFragment mTextsFragment = TextsFragment.newInstance();
    private DecksFragment mDecksFragment = DecksFragment.newInstance();
    private DictionaryFragment mDictionaryFragment = DictionaryFragment.newInstance();
    private StoreFragment mStoreFragment = StoreFragment.newInstance();
    private MoreFragment mMoreFragment = MoreFragment.newInstance();

    private Fragment mCurrentFragment = null;

    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.Silver)));

            LayoutInflater mInflater = LayoutInflater.from(this);
            View mCustomView = mInflater.inflate(R.layout.action_bar, null);
            actionBar.setCustomView(mCustomView);
            actionBar.setDisplayShowCustomEnabled(true);
        }

        PageNavigationView tab = (PageNavigationView) findViewById(R.id.activity_main_bottom_tab);
        NavigationController navigationController = tab.material()
                .addItem(R.drawable.ic_bottom_tab_text, "Texts")
                .addItem(R.drawable.ic_bottom_tab_decks, "Decks")
                .addItem(R.drawable.ic_bottom_tab_dictionary, "Dictionary")
                .addItem(R.drawable.ic_bottom_tab_store, "Store")
                .addItem(R.drawable.ic_bottom_tab_more, "More")
                .build();

        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                showFragment(index);
            }

            @Override
            public void onRepeat(int index) {
            }
        });

        showFragment(BOTTOM_TAB_TEXTS);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void showFragment(int index) {
        if (index < 0) {
            return;
        }
        Fragment toFragment = null;

        switch (index) {
            case BOTTOM_TAB_TEXTS:
                toFragment = mTextsFragment;
                break;
            case BOTTOM_TAB_DECKS:
                toFragment = mDecksFragment;
                break;
            case BOTTOM_TAB_DICTIONARY:
                toFragment = mDictionaryFragment;
                break;
            case BOTTOM_TAB_STORE:
                toFragment = mStoreFragment;
                break;
            case BOTTOM_TAB_MORE:
                toFragment = mMoreFragment;
                break;
            default:
                break;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mCurrentFragment != null && mCurrentFragment.isAdded()) {
            ft.hide(mCurrentFragment);
        }
        if (toFragment.isAdded()) {
            ft.show(toFragment);
        } else {
            ft.add(R.id.activity_main_container, toFragment);
        }

        mCurrentFragment = toFragment;
        ft.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(this, getString(R.string.double_back_to_exit), Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
