package com.example.sqassist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuesTwo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuesTwo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuesTwo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuesTwo.
     */
    // TODO: Rename and change types and number of parameters
    public static QuesTwo newInstance(String param1, String param2) {
        QuesTwo fragment = new QuesTwo();
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

    SharedPreference sharedPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ques_two, container, false);    //to use findViewById in fragment

        v.findViewById(R.id.noQues2Button).setOnClickListener(ques2BtnListener);
        v.findViewById(R.id.abitQues2Button).setOnClickListener(ques2BtnListener);
        v.findViewById(R.id.badQues2Button).setOnClickListener(ques2BtnListener);
        v.findViewById(R.id.veryBadQues2Button).setOnClickListener(ques2BtnListener);

        return v;
    }

    //click to redirect to question 2
    private final View.OnClickListener ques2BtnListener = new View.OnClickListener() {
        public void onClick(View view) {
            String ansQues2 = "0";
            switch (view.getId()) {
                case R.id.noQues2Button:
                    ansQues2 = "1";
                    break;
                case R.id.abitQues2Button:
                    ansQues2 = "2";
                    break;
                case R.id.badQues2Button:
                    ansQues2 = "3";
                    break;
                case R.id.veryBadQues2Button:
                    ansQues2 = "4";
                    break;
            }
            //Store answer to preferences
            sharedPreference = new SharedPreference(getActivity());

            sharedPreference.setQuesFragment(2);
            sharedPreference.setCoughLvl(ansQues2);
            getParentFragmentManager().beginTransaction().replace(R.id.question_container, new QuesThree()).commit();
        }
    };
}