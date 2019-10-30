package com.example.learnthewords.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.learnthewords.R;

public class WordViewHolder extends RecyclerView.ViewHolder {

    public TextView name, translate;
    public ImageView deleteWord;
    public  ImageView editWord;

    public WordViewHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.origin_word);
        translate = (TextView)itemView.findViewById(R.id.word_translate);
        deleteWord = (ImageView)itemView.findViewById(R.id.delete_word);
        editWord = (ImageView)itemView.findViewById(R.id.edit_word);
    }
}