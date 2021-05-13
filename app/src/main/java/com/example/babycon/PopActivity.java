package com.example.babycon;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PopActivity extends FragmentActivity {

    private EditText  obwodglowy, obwodklatki, notatnik, waga;
    Button dodaj;
    Button wyjdz;
    String _id;
    private DataBaseHelper myDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_layout);

        myDb = new DataBaseHelper(this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        _id = extras.getString("idchild");

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.7));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x =0;
        params.y = -50;

        dodaj = findViewById(R.id.dodaj);
        wyjdz = findViewById(R.id.wyjdz);

        obwodglowy = findViewById(R.id.obwodglowy2);
        obwodklatki = findViewById(R.id.obwodklatki2);
        notatnik = findViewById(R.id.notatnik);
        waga = findViewById(R.id.waga2);

        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yy");
        String currentDateandTime = sdf2.format(new Date());

        dodaj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean var = myDb.insertdata(_id, currentDateandTime, obwodglowy.getText().toString(), obwodklatki.getText().toString(), notatnik.getText().toString(), waga.getText().toString());
                Toast.makeText(PopActivity.this, "Dane dodano", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        wyjdz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
