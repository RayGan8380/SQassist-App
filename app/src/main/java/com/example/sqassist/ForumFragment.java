package com.example.sqassist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ForumFragment extends Fragment {

    public static ForumFragment newInstance() {
        return new ForumFragment();
    }

    Button askBtn;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forum_fragment, container, false);

        //Click to post answer
        askBtn = v.findViewById(R.id.askButton);
        askBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Asked! Waiting doctor to answer!", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

}