package com.example.sqassist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuesOne#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuesOne extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuesOne() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuesOne.
     */
    // TODO: Rename and change types and number of parameters
    public static QuesOne newInstance(String param1, String param2) {
        QuesOne fragment = new QuesOne();
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
    EditText Date, Temperature, BloodOxi;
    String editDate, editTemperature, editBloodOxi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ques_one, container, false);    //to use findViewById in fragment

        Button ques1Btn = v.findViewById(R.id.ques1Btn);
        sharedPreference = new SharedPreference(getActivity());

        Date = (EditText) v.findViewById(R.id.editTextDate);
        Temperature = (EditText) v.findViewById(R.id.editTextTemperature);
        BloodOxi = (EditText) v.findViewById(R.id.editTextBloodOxi);

        //Click to redirect to next questin fragment when all fields are filled up
        ques1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDate = Date.getText().toString();
                editTemperature = Temperature.getText().toString();
                editBloodOxi = BloodOxi.getText().toString();

                //Check if the fields are empty or already registered in database else then register
                if (editDate.equals("") || editDate.equals("") || editBloodOxi.equals(""))
                    Toast.makeText(getActivity(), "Please enter all the fields!", Toast.LENGTH_SHORT).show();

                else {
                    //Store data to preferences
                    sharedPreference.setQuesFragment(1);
                    sharedPreference.setTemperature(editTemperature);
                    sharedPreference.setBloodOxi(editBloodOxi);
                    getParentFragmentManager().beginTransaction().replace(R.id.question_container, new QuesTwo()).commit();

                }}
        });

        return v;
    }

}