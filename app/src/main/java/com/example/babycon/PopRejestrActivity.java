package com.example.babycon;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PopRejestrActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener{



    EditText imie;
    Button zatw;
    Button wyjdz;
    TextView text;
    TextView textView;
    String userID;
    CheckBox chlopak,dziewczyna;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_layout_rejestruj);

        DataBaseHelper myDB;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String __id = extras.getString("id");


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.7));
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x =0;
        params.y = -50;
        chlopak=(CheckBox)findViewById(R.id.plec2b);
        dziewczyna=(CheckBox)findViewById(R.id.plec2a);
        zatw = findViewById(R.id.zatwierdz);
        wyjdz = findViewById(R.id.wyjdz);
        imie = findViewById(R.id.imie2);
        text = (TextView) findViewById(R.id.data2);
        myDB = new DataBaseHelper(this);


        chlopak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chlopak.setChecked(true);
                chlopak.setSelected(true);
                dziewczyna.setChecked(false);
                dziewczyna.setSelected(false);
            }
        });

        dziewczyna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chlopak.setChecked(false);
                chlopak.setSelected(false);
                dziewczyna.setChecked(true);
                dziewczyna.setSelected(true);
            }
        });


        zatw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String wImie  = imie.getText().toString();
                String wData = text.getText().toString();
                String wPlec = null;
                if(dziewczyna.isSelected()){
                    wPlec = "dziewczyna";
                }
                if(chlopak.isSelected()){
                    wPlec = "chłopak";
                }
                if(!chlopak.isSelected()  && !dziewczyna.isSelected()){
                    Toast.makeText(PopRejestrActivity.this, "Wybierz płeć", Toast.LENGTH_SHORT).show();
                    finish();
                }

                if (TextUtils.isEmpty(wImie)) {
                    imie.setError("Imie jest konieczne");
                    return;
                }

                if (TextUtils.isEmpty(wData)) {
                    text.setError("Wprowadź date");
                    return;
                }

                boolean var = myDB.addBaby(__id, wImie, wData, wPlec);
                if(var){
                    Toast.makeText(PopRejestrActivity.this, "Dodano dziecko", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(PopRejestrActivity.this, "Spróbuj jeszcze raz", Toast.LENGTH_SHORT).show();

                boolean var2 = myDB.stworzSzczepionki(__id);
                if(var2){
                    Toast.makeText(PopRejestrActivity.this, "Dodano dziecko", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(PopRejestrActivity.this, "Spróbuj jeszcze raz", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (getApplication(), LoginActivity.class);
                startActivity(intent);
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
