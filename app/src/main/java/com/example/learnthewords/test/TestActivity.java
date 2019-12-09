package com.example.learnthewords.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.learnthewords.R;
import com.example.learnthewords.dataBase.SQLiteDatabase;
import com.example.learnthewords.model.Words;

import java.util.ArrayList;


public class TestActivity extends AppCompatActivity {

    private TextView currectAnswer, element1, element2, element3,element4,element5,element6;
    private boolean added;
    private String trueAnsver = "";
    private String originWord = "";


    private SQLiteDatabase mDatabase;
    private ArrayList<Words> allWords =new ArrayList<>();



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        currectAnswer = findViewById(R.id.correct_answer);
        element1 = findViewById(R.id.element_1);
        element2 = findViewById(R.id.element_2);
        element3 = findViewById(R.id.element_3);
        element4 = findViewById(R.id.element_4);
        element5 = findViewById(R.id.element_5);
        element6 = findViewById(R.id.element_6);

        LinearLayout layou1 = findViewById(R.id.layot_1);
        LinearLayout layou2 = findViewById(R.id.layot_2);
        LinearLayout layou3 = findViewById(R.id.layot_3);
        LinearLayout layou4 = findViewById(R.id.layot_4);
        LinearLayout layou5 = findViewById(R.id.layot_5);
        LinearLayout layou6 = findViewById(R.id.layot_6);





        viewWords();


        layou1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(element1.getText() == originWord){
                    hideViewEllements();

                    currectAnswer.setText(element1.getText()+ "-" + trueAnsver);
                }
            }
        });

        layou2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(element2.getText() == originWord){
                    hideViewEllements();
                    currectAnswer.setText("");
                    currectAnswer.setText(element2.getText()+ "-" + trueAnsver);
                }
            }
        });

        layou3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(element3.getText() == originWord){
                    hideViewEllements();
                    currectAnswer.setText("");
                    currectAnswer.setText(element3.getText()+ "-" + trueAnsver);
                }
            }
        });

        layou4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(element4.getText() == originWord){
                    hideViewEllements();
                    currectAnswer.setText("");
                    currectAnswer.setText(element4.getText()+ "-" + trueAnsver);
                }
            }
        });

        layou5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(element5.getText() == originWord){
                    hideViewEllements();
                    currectAnswer.setText("");
                    currectAnswer.setText(element5.getText()+ "-" + trueAnsver);
                }
            }
        });

        layou6.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                if(element6.getText() == originWord){
                    hideViewEllements();
                    currectAnswer.setText("");
                    currectAnswer.setText(element6.getText()+ "-" + trueAnsver);
                }
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void viewWords(){


        mDatabase = new SQLiteDatabase(this);
        allWords = mDatabase.listWords();


        for(int i = 1; i<=6; i++){
            int id = (int) getId(allWords);
            String en = allWords.get(id).getOriginWord();
            if(!added){
                trueAnsver = allWords.get(id).getTranslateWord();
                originWord = allWords.get(id).getOriginWord();
                currectAnswer.setText(trueAnsver);
                added=false;
            }


            switch (i) {
                case 1:
                    element1.setText(en);
                    break;
                case 2:
                    element2.setText(en);
                    break;
                case 3:
                    element3.setText(en);
                    break;
                case 4:
                    element4.setText(en);
                    break;
                case 5:
                    element5.setText(en);
                    break;
                case 6:
                    element6.setText(en);
                    break;

            }



        }
    }

    @SuppressLint("ResourceAsColor")
    public long getId(ArrayList<Words> allWords){

        int a = 0;
        int b = allWords.size();

        int id = a + (int) (Math.random() * b);
        return id;

    }

    public void hideViewEllements(){
//        element1.setVisibility(View.GONE);
//        element2.setVisibility(View.GONE);
//        element3.setVisibility(View.GONE);
//        element4.setVisibility(View.GONE);
//        element5.setVisibility(View.GONE);
//        element6.setVisibility(View.GONE);
    }

}
