package com.example.finalproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.CharacterHolder> {

    private Context mContext;
    private List<Characters> characters;
    private OnItemNoteListener mOnItemNoteListener;

    public Adapter(List<Characters> charactersList, Context mContext, OnItemNoteListener mOnItemNoteListener) {
        this.mContext = mContext;
        this.characters = charactersList;
        this.mOnItemNoteListener = mOnItemNoteListener;
    }

    @NonNull
    @Override
    public CharacterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.character_list, parent, false);
        return new CharacterHolder(view, mOnItemNoteListener);

    }

    @Override
    public void onBindViewHolder(@NonNull CharacterHolder holder, int position) {

        holder.name.setText(characters.get(position).getName());


        System.out.println(characters.get(position).getImg());
        Picasso.with(mContext)
                .load(characters.get(position).getImg())
                .placeholder(R.drawable.settings)
                .fit()
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public static class CharacterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageView image;
        OnItemNoteListener noteListener;

        public CharacterHolder(View itemView, OnItemNoteListener noteListener) {
            super(itemView);
            name = itemView.findViewById(R.id.charName);
            image = itemView.findViewById(R.id.imageViewUser);
            this.noteListener = noteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            noteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnItemNoteListener{
        void onNoteClick(int position);
    }
}
