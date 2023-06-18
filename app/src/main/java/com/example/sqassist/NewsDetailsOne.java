package com.example.sqassist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsDetailsOne#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsDetailsOne extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsDetailsOne() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsDetailsOne.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsDetailsOne newInstance(String param1, String param2) {
        NewsDetailsOne fragment = new NewsDetailsOne();
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

    ImageView backBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news_details_one, container, false);

        //Click to go back to news fragment
        backBtn = v.findViewById(R.id.backButton3);
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.news_container, new newsList()).commit();
            }
        });

        return v;
}
}