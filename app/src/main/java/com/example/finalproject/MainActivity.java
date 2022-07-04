package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView navigationView;
ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigation);
        layout = findViewById(R.id.main_content);
        Intent intent = getIntent();
        String backColor = intent.getStringExtra("color");
        if(backColor!=null){
            switch (backColor){
                case "green":layout.setBackgroundColor(Color.GREEN);break;
                case "cyan":layout.setBackgroundColor(Color.CYAN);break;
                case "white":layout.setBackgroundColor(Color.WHITE);break;
                case  "yellow":layout.setBackgroundColor(Color.YELLOW);break;
            }
        }

          getSupportFragmentManager().beginTransaction().replace(R.id.main_content,
                  new CharactersFragment()).commit();
          navigationView.setSelectedItemId(R.id.nav_char);
          navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
              @Override
              public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                  Fragment fragment=null;
                  switch (item.getItemId()){
                      case  R.id.nav_char:
                          fragment=new CharactersFragment();break;
                      case R.id.nav_profile:
                          fragment=new ProfileFragment();break;
                      case  R.id.nav_settings:
                          fragment=new SettingsFragment();break;
                  }
                  getSupportFragmentManager().beginTransaction().replace(R.id.main_content,fragment).commit();

                  return true;
              }
          });

    }
}