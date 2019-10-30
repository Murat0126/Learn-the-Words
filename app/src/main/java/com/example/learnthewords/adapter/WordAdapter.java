package com.example.learnthewords.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnthewords.R;
import com.example.learnthewords.dataBase.SQLiteDatabase;
import com.example.learnthewords.model.Words;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Words> listWords;
    private ArrayList<Words> mArrayList;

    private SQLiteDatabase mDatabase;

    public WordAdapter(Context context, ArrayList<Words> listWords) {
        this.context = context;
        this.listWords = listWords;
        this.mArrayList= listWords;
        mDatabase = new SQLiteDatabase(context);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        final Words words = listWords.get(position);

        holder.name.setText(words.getOriginWord());
        holder.translate.setText(words.getTranslateWord());

        holder.editWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(words);
            }
        });

        holder.deleteWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database

                mDatabase.deleteWord(words.getId());

                //refresh the activity page.
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    listWords = mArrayList;
                } else {

                    ArrayList<Words> filteredList = new ArrayList<>();

                    for (Words words : mArrayList) {

                        if (words.getOriginWord().toLowerCase().contains(charString)) {

                            filteredList.add(words);
                        }
                    }

                    listWords = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listWords;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listWords = (ArrayList<Words>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    @Override
    public int getItemCount() {
        return listWords.size();
    }


    private void editTaskDialog(final Words words){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.layout_dialog_add_new_words, null);

        final EditText wordField = (EditText)subView.findViewById(R.id.add_new_words);
        final EditText translateField = (EditText)subView.findViewById(R.id.translate_text);

        if(words != null){
            wordField.setText(words.getOriginWord());
            translateField.setText(String.valueOf(words.getTranslateWord()));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit word");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDIT VACABULARY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String origin = wordField.getText().toString();
                final String translate = translateField.getText().toString();

                if(TextUtils.isEmpty(origin)){
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }

                else{
                    mDatabase.updateWords(new Words(words.getId(), origin, translate));
                    //refresh the activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}