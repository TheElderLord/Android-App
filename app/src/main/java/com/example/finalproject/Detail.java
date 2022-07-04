package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {

    private TextView name;
    private TextView birthday;
    private TextView status;
    private int id;
    private Characters characters;
    private ArrayList<String> occ;
    private ListView listViewOccupation;
    private ImageView imageViewD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",1);
        name = findViewById(R.id.charName);
        birthday = findViewById(R.id.charBirthday);
        status = findViewById(R.id.charstatus);
        listViewOccupation = findViewById(R.id.charOccupation);
        imageViewD = findViewById(R.id.charImage);
        fetchCharacters();

    }

    private void fetchCharacters() {

        RetrofitClient.getRetrofitClient().getCharacterById(id).enqueue(new Callback<List<Characters>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Characters>> call, Response<List<Characters>> response) {
                if(response.isSuccessful() && response.body() != null){
                    characters = new Characters(response.body());
                    name.setText(characters.getName());
                    birthday.setText(characters.getBirthday());
                    occ = characters.getOccupation();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_list_item_1, occ);
                    listViewOccupation.setAdapter(adapter);
                    Picasso.with(getApplicationContext())
                            .load(characters.getImg())
                            .placeholder(R.drawable.settings)
                            .fit()
                            .into(imageViewD);
                    status.setText(characters.getStatus());

                }
            }

            @Override
            public void onFailure(Call<List<Characters>> call, Throwable t) {
                Toast.makeText(Detail.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}