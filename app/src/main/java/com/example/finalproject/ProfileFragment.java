package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ProfileFragment extends Fragment {
TextView full, mailfield;
private String fullname="";
private String email="";
private Button change,save;
 private    UserEntity userEntity;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        Database database = Database.getUserDatabase(getContext());
        full = view.findViewById(R.id.full);
        mailfield = view.findViewById(R.id.email);
        change = view.findViewById(R.id.change);
        save = view.findViewById(R.id.save);
        UserDao userDao = database.userDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                userEntity = userDao.getInfo(Login.id);
                fullname = userEntity.getFullname();
                email = userEntity.getEmail();

                if (userEntity!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            full.setText(fullname);
                            mailfield.setText(email);
                        }
                    });

                }

            }
        }).start();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userEntity.setEmail(mailfield.getText().toString());
                userEntity.setFullname(full.getText().toString());
                userDao.updateUser(userEntity);

            }
        });




        return view;

    }
}