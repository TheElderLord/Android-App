package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CharactersFragment extends Fragment implements Adapter.OnItemNoteListener{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Adapter charactersAdapter;
    List<Characters> charactersList= new ArrayList<>();


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_characters,container,false);
        recyclerView = layout.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        charactersAdapter = new Adapter(charactersList, this.getContext(), this);
        recyclerView.setAdapter(charactersAdapter);
        fetchCharacters();
        return layout;
    }

    private void fetchCharacters() {

        RetrofitClient.getRetrofitClient().getCharacters().enqueue(new Callback<List<Characters>>() {
            @Override
            public void onResponse(@NonNull Call<List<Characters>> call, Response<List<Characters>> response) {
                if(response.isSuccessful() && response.body() != null){
                    charactersList.addAll(response.body());
                    charactersAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<Characters>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onNoteClick(int position) {
        Characters characters = charactersList.get(position);
        Intent intent = new Intent(this.getContext(), Detail.class);
        intent.putExtra("id", characters.getChar_id());
        startActivity(intent);
    }
}