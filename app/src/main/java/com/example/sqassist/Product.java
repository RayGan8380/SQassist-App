package com.example.sqassist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Product1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Product extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Product() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Product1.
     */
    // TODO: Rename and change types and number of parameters
    public static Product newInstance(String param1, String param2) {
        Product fragment = new Product();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    Button detailsBtn1 , detailsBtn2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product, container, false);

        //Click to show details of product 1
        detailsBtn1 = v.findViewById(R.id.detailsButton);
        detailsBtn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.shop_container, new ProductDetailsOne()).commit();
            }
        });

        //Click to show details of product 2
        detailsBtn2 = v.findViewById(R.id.detailsButton2);
        detailsBtn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.shop_container, new ProductDetailsTwo()).commit();
            }
        });
        return v;
    }
}