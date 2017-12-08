package com.example.stone.uidemo.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.stone.uidemo.R;
import com.example.stone.uidemo.view.decks.DecksFragment;
import com.example.stone.uidemo.view.dictionary.DictionaryFragment;
import com.example.stone.uidemo.view.more.MoreFragment;
import com.example.stone.uidemo.view.store.StoreFragment;
import com.example.stone.uidemo.view.text.TextsFragment;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PageNavigationView tab = (PageNavigationView) findViewById(R.id.activity_main_bottom_tab);

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
}
