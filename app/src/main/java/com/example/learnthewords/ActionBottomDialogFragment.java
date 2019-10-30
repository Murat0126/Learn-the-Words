package com.example.learnthewords;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.learnthewords.adapter.Adapter;
import com.example.learnthewords.dataBase.DBHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;

public class ActionBottomDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = "ActionBottomDialog";
    private TextInputEditText originText, translateText;
    private MaterialButton addButton,complateButton;
    private DBHelper dbHelper;
    SQLiteDatabase database;
    ImageView closeIcon;
    ArrayList<String> items = new ArrayList<>();
    MainActivity mainActivity;
    Adapter adapter ;


    static ActionBottomDialogFragment newInstance() {
        return new ActionBottomDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_dialog_add_new_words, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        originText = view.findViewById(R.id.origin_text);
        translateText = view.findViewById(R.id.translate_text);
        addButton = view.findViewById(R.id.add_button);
        complateButton = view.findViewById(R.id.complete_button);
        closeIcon = view.findViewById(R.id.close_icon_in_ads_dialog);

        addButton.setOnClickListener(this);
        complateButton.setOnClickListener(this);
        closeIcon.setOnClickListener(this);

        dbHelper = new DBHelper(getContext());

        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

//        complateButton.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });

    }


    @Override
    public void onClick(View v) {

        String origin = originText.getText().toString();
        String translate = translateText.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.add_button:

                contentValues.put(DBHelper.KEY_ORIGIN_WORD, origin);
                contentValues.put(DBHelper.KEY_TRANSLATE_OF_WORD, translate);
                database.insert(DBHelper.TABLE_DICTIONARY, null, contentValues);

                break;

            case R.id.complete_button:

                Cursor cursor = database.query(DBHelper.TABLE_DICTIONARY, null, null, null,
                        null, null, null);
                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int originTextIndex = cursor.getColumnIndex(DBHelper.KEY_ORIGIN_WORD);
                    int translateIndex = cursor.getColumnIndex(DBHelper.KEY_TRANSLATE_OF_WORD);
                    do {

                        items.add(cursor.getString(originTextIndex));
                        Toast.makeText(getContext(), MessageFormat.format("ID = {0}, name = {1}, email = {2}", cursor.getInt(idIndex), cursor.getString(originTextIndex), cursor.getString(translateIndex)), Toast.LENGTH_SHORT).show();

                        File f = getActivity().getDatabasePath(DBHelper.DATABASE_NAME);
                         long dbSize = f.length();

                        Log.d("mLog", "Size = " + dbSize +
                                "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(originTextIndex) +
                                ", email = " + cursor.getString(translateIndex));



                    } while (cursor.moveToNext());

                        adapter = new Adapter(getActivity(), items);
                        adapter.changeAdapter(items);

                } else
                    Log.d("mLog","0 rows");

                dismiss();
                cursor.close();
                break;



//            case R.id.btnClear:
//                database.delete(DBHelper.TABLE_CONTACTS, null, null);
//                break;
        }

//        adapter = new Adapter(getContext(), items);

        dbHelper.close();
    }
}