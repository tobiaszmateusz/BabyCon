package com.example.babycon;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class PopSzczepienieActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener{


    Button zatw;
    Button wyjdz;
    TextView text;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_layout_szczepienie_data);

        DataBaseHelper myDB;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String idch = extras.getString("idch");
        String nazwa = extras.getString("nazwa");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.4));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x =0;
        params.y = -50;

        zatw = findViewById(R.id.zatwierdz);
        wyjdz = findViewById(R.id.wyjdz);
        text = (TextView) findViewById(R.id.data2);
        myDB = new DataBaseHelper(this);
        String idszczepienia = null;

        Cursor nazwaszczepionki = myDB.getNazwa(nazwa);
        ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();

        if (nazwaszczepionki.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<nazwaszczepionki.getColumnCount();i++)
                {
                    map.put(nazwaszczepionki.getColumnName(i), nazwaszczepionki.getString(i));
                    idszczepienia = nazwaszczepionki.getString(i);
                }

                maplist.add(map);
            } while (nazwaszczepionki.moveToNext());
        }


        String finalIdszczepienia = idszczepienia;

        zatw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String wData = text.getText().toString();
                myDB.accSzczepienia(idch, wData, finalIdszczepienia);
                finish();
            }
        });

        wyjdz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        textView = (TextView) findViewById(R.id.data2);
        textView.setText(currentDateString);
    }
}
