package com.example.learnthewords.vocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.learnthewords.R;
import com.example.learnthewords.adapter.WordAdapter;
import com.example.learnthewords.dataBase.SQLiteDatabase;
import com.example.learnthewords.model.Words;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Vocabulary extends AppCompatActivity  {

    private static final String TAG = Vocabulary.class.getSimpleName();

    private SQLiteDatabase mDatabase;
    private ArrayList<Words> allWords =new ArrayList<>();
    private WordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        FrameLayout fLayout =  findViewById(R.id.activity_to_do);
        setToolbar("Words");

        RecyclerView wordsView = findViewById(R.id.product_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        wordsView.setLayoutManager(linearLayoutManager);
        wordsView.setHasFixedSize(true);
        mDatabase = new SQLiteDatabase(this);
        allWords = mDatabase.listWords();

        if(allWords.size() > 0){
            wordsView.setVisibility(View.VISIBLE);
            mAdapter = new WordAdapter(this, allWords);
            wordsView.setAdapter(mAdapter);

        }else {
            wordsView.setVisibility(View.GONE);
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTaskDialog();
            }
        });
    }

    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.layout_dialog_add_new_words, null);

        final EditText nameField =subView.findViewById(R.id.origin_text);
        final EditText noField = subView.findViewById(R.id.translate_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("ADD VOCABULARY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String word = nameField.getText().toString();
                final String translate = noField.getText().toString();

                if(TextUtils.isEmpty(word)){
                    Toast.makeText(Vocabulary.this, "Вы пытаетесь сохранить пустую строку!", Toast.LENGTH_LONG).show();
                }
                else{
                    Words newContact = new Words(word, translate);
                    mDatabase.addWords(newContact);

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(Vocabulary.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }


    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mAdapter!=null)
                    mAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    public void setToolbar( @NonNull String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        (this).setSupportActionBar(toolbar);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }





}
