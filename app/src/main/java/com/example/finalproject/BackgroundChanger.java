package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BackgroundChanger extends AppCompatActivity {
RadioGroup radioGroup;
RadioButton green,white,cyan,yellow;
Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_changer);
        radioGroup = findViewById(R.id.group);
        green = findViewById(R.id.greenradio);
        cyan = findViewById(R.id.cyanradio);
        white= findViewById(R.id.whiteradio);
        yellow=findViewById(R.id.yellowradio);
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.greenradio:
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("color","green");
                        startActivity(intent);
                        break;
                    case R.id.cyanradio:
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("color","cyan");
                        startActivity(intent);
                        break;
                    case R.id.whiteradio:
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("color","white");
                        startActivity(intent);
                        break;
                    case R.id.yellowradio:
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("color","yellow");
                        startActivity(intent);
                        break;
                }
            }
        });

    }
}