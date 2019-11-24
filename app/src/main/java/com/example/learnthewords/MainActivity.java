package com.example.learnthewords;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.example.learnthewords.test.TestActivity;
import com.example.learnthewords.vocabulary.Vocabulary;
import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity {


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {

        super.onSaveInstanceState(outState, outPersistentState);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar("LearnTheWords");

        MaterialButton addNewWordsbtn = findViewById(R.id.add_new_words);
        MaterialButton startLearnButton = findViewById(R.id.start_learn);

        addNewWordsbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Vocabulary.class);
                startActivity(intent);
//                showBottomSheet();
            }
        });

        startLearnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
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


}
