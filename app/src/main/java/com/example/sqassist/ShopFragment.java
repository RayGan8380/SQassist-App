package com.example.sqassist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShopFragment extends Fragment {


    public static ShopFragment newInstance() {
        return new ShopFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Choose which product fragment to show
        getParentFragmentManager().beginTransaction().replace(R.id.shop_container, new Product()).commit();
        return inflater.inflate(R.layout.shop_fragment, container, false);
    }


}