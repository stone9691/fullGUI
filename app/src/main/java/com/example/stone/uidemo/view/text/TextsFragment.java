package com.example.stone.uidemo.view.text;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stone.uidemo.R;

public class TextsFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_texts, container, false);
    }
}
