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

public class Login extends AppCompatActivity {
private EditText emailfield,passwordfield;
private Button conf;
private TextView reg;
public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailfield = findViewById(R.id.loginemail);
        passwordfield = findViewById(R.id.loginpass);
        conf = findViewById(R.id.butt);
        reg = findViewById(R.id.openReg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailfield.getText().toString();
                String pass = passwordfield.getText().toString();
                if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(pass)){
                    Toast.makeText(Login.this,"All fields are requird",Toast.LENGTH_SHORT).show();

                }
                else {
                    Database database = Database.getUserDatabase(getApplicationContext());
                    UserDao userDao = database.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                           UserEntity userEntity = userDao.login(email,pass);
                           if (userEntity==null){
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(getApplicationContext(),"Invalid data",Toast.LENGTH_LONG).show();
                                   }
                               });
                           }
                           else {
                               id = userEntity.getId();

                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(getApplicationContext(),"User found",Toast.LENGTH_LONG).show();
                                   }
                               });

                               Intent inti = new Intent(getApplicationContext(),MainActivity.class);
                               startActivity(inti);
                           }
                        }
                    }).start();
                }
            }
        });
    }
}