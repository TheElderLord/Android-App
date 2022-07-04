package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SettingsFragment extends Fragment {
Button back,intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_settings, container, false);
        back = layout.findViewById(R.id.backGround);
        intent = layout.findViewById(R.id.sharing);
        intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity = new Intent(Intent.ACTION_SEND);
                activity.setType("text/plain");
                activity.putExtra(Intent.EXTRA_TEXT,"I am sharing intent");
                startActivity(activity);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(getContext(),BackgroundChanger.class);
                 startActivity(intent);
            }
        });

        return layout;
    }
}