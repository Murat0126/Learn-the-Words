package com.example.learnthewords;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.learnthewords.adapter.Adapter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Adapter adapter;
    ArrayList<String> items = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar("LearnTheWords");

        MaterialButton addNewWordsbtn = findViewById(R.id.add_new_words);

        RecyclerView recyclerView = findViewById(R.id.recycler_in_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration divider = new DividerItemDecoration(
                recyclerView.getContext(),
                DividerItemDecoration.VERTICAL
        );
        divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.recycler_dividier)));
        recyclerView.addItemDecoration(divider);
        items.add("Sava");
        adapter = new Adapter(this, items);
        recyclerView.setAdapter(adapter);



        addNewWordsbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                showBottomSheet();
            }
        });

    }

    public void setToolbar( @NonNull String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        this.setSupportActionBar(toolbar);
    }

    public void showBottomSheet() {
        ActionBottomDialogFragment addPhotoBottomDialogFragment =
                ActionBottomDialogFragment.newInstance();
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(),ActionBottomDialogFragment.TAG);

    }

    public ArrayList<String>  changeAdapter(ArrayList<String> items){

        adapter = new Adapter(this, items);
        adapter.notifyDataSetChanged();
        return items;
    }


//    public void getTableItems(SQLiteDatabase database){
//
//        Cursor cursor = database.query(DBHelper.TABLE_DICTIONARY, null, null, null,
//                null, null, null);
//
//        if (cursor.moveToFirst()) {
//            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
//            int originTextIndex = cursor.getColumnIndex(DBHelper.KEY_ORIGIN_WORD);
//            int translateIndex = cursor.getColumnIndex(DBHelper.KEY_TRANSLATE_OF_WORD);
//            File f = this.getDatabasePath(DBHelper.DATABASE_NAME);
////            long dbSize = f.length();
//            do {
//
//                     items.add(cursor.getString(originTextIndex));
//
//                Toast.makeText(this, MessageFormat.format("ID = {0}, name = {1}, email = {2}", cursor.getInt(idIndex), cursor.getString(originTextIndex), cursor.getString(translateIndex)), Toast.LENGTH_SHORT).show();
//
//                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
//                        ", name = " + cursor.getString(originTextIndex) +
//                        ", email = " + cursor.getString(translateIndex));
//            } while (cursor.moveToNext());
//
//            adapter.notifyDataSetChanged();
////            createResyclerData();
//
//        } else
//            Log.d("mLog","0 rows");
//
//        cursor.close();
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createResyclerData(){


    }

}
