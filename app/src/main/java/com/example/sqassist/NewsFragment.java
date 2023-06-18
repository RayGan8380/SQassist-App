package com.example.sqassist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsFragment extends Fragment {

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Choose which news fragment to show
        getParentFragmentManager().beginTransaction().replace(R.id.news_container, new newsList()).commit();
        return inflater.inflate(R.layout.news_fragment, container, false);

    }



}