package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
EditText emailfield,passfield,confpass,fullnamefield;
TextView openLog;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailfield = findViewById(R.id.regemail);
        passfield = findViewById(R.id.regpass);
        confpass = findViewById(R.id.confpass);
        fullnamefield = findViewById(R.id.name);
        openLog = findViewById(R.id.openLog);
        String regex = "^(.+)@(.+)$";
        button = findViewById(R.id.butt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String email = emailfield.getText().toString();
               String password = passfield.getText().toString();
               String conf = confpass.getText().toString();
               String name = fullnamefield.getText().toString();
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(email);
               if (TextUtils.isEmpty(email) || TextUtils.isEmpty(email) || TextUtils.isEmpty(email) || TextUtils.isEmpty(name)){
                   Toast.makeText(Register.this,"All fields are required",Toast.LENGTH_SHORT).show();
               }
               else if(!matcher.matches()){
                   Toast.makeText(Register.this,"Email format is not correct",Toast.LENGTH_SHORT).show();

               }
               else if(!password.equals(conf)){
                   Toast.makeText(Register.this,"Passwords are not the same",Toast.LENGTH_SHORT).show();

               }
               else if(password.length()<8){
                   Toast.makeText(Register.this,"Passwords length is less 8 symbols",Toast.LENGTH_SHORT).show();

               }
               else {
                   UserEntity userEntity = new UserEntity();
                   userEntity.setEmail(email);
                   userEntity.setPassword(password);
                   userEntity.setFullname(name);
                   Database database= Database.getUserDatabase(getApplicationContext());
                   UserDao userDao = database.userDao();
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           userDao.registerUser(userEntity);
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   Toast.makeText(Register.this,"Passwords registration is successfull",Toast.LENGTH_SHORT).show();

                               }
                           });

                       }
                   }).start();
                   Intent intent = new Intent(Register.this,Login.class);
                   startActivity(intent);
               }
            }
        });
        openLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }

}