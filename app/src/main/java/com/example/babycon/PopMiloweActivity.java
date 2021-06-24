package com.example.babycon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class PopMiloweActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener{


    Button zatw;
    Button wyjdz;
    TextView text;
    TextView textView;
    Spinner spiner;
    String[] value = {"Wybierz krok","1 KROK","2 KROK","3 KROK","4 KROK"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_layout_milowe);

        DataBaseHelper myDB;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String idch = extras.getString("idch");

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


/*        spiner = findViewById(R.id.krok);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, elementy);
        spiner.setAdapter(adapter);

        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int id, long position) {
                // ta metoda wykonuje się za każdym razem, gdy zostanie wybrany jakiś element z naszej listy
                Toast.makeText(PopMiloweActivity.this, "Wybrano opcję" + (id+1), Toast.LENGTH_SHORT).show();

                switch((int)position)
                {
                    case 0:
                        //wybrano pierwszy element
                        break;
                    case 1:
                        //wybrano drugi element
                        break;
                    case 2:
                        //wybrano trzeci element
                        break;
                    case 3:
                        //wybrano czwarty element
                        break;
                    case 4:
                        //wybrano piąty element
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // ta metoda wykonuje sie gdy lista zostanie wybrana, ale nie zostanie wybrany żaden element z listy

            }
        });*/


        spiner = findViewById(R.id.krok);
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(value));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.style_spinner, R.id.tvLeague2, arrayList){
            @SuppressLint("ResourceAsColor")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    tv.setTextColor(getApplicationContext().getResources().getColor(R.color.white));
                }
                else {
                    tv.setTextColor(getApplicationContext().getResources().getColor(R.color.white));
                }
                return view;
            }
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    tv.setTextColor(getApplicationContext().getResources().getColor(R.color.white));
                }
                else {
                    tv.setTextColor(getApplicationContext().getResources().getColor(R.color.white));
                }
                return view;
            }
        };
        
        arrayAdapter.setDropDownViewResource(R.layout.style_spinner);
        spiner.setAdapter(arrayAdapter);




        zatw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String wData = text.getText().toString();
                String krok = spiner.getSelectedItem().toString();
                myDB.addMilowy(idch, wData, krok);

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
