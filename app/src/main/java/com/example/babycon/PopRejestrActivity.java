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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.app.DatePickerDialog;
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

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_layout_rejestruj);

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

        zatw = findViewById(R.id.zatwierdz);
        wyjdz = findViewById(R.id.wyjdz);
        imie = findViewById(R.id.imie2);
        text = (TextView) findViewById(R.id.data2);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        zatw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String wImie  = imie.getText().toString();
                String wData = text.getText().toString();

                if (TextUtils.isEmpty(wImie)) {
                    imie.setError("Imie jest konieczne");
                    return;
                }

                if (TextUtils.isEmpty(wData)) {
                    text.setError("Wprowadź date");
                    return;
                }

                userID = fAuth.getCurrentUser().getUid();

                DocumentReference dR = fStore.collection("users").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("fname", wImie);
                user.put("fdata", wData);
                dR.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    private static final String TAG = "TAG";
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSucces: User profile created:"+userID);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
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
