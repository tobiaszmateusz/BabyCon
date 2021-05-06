package com.example.babycon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import com.example.babycon.ui.main.PlaceholderFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.PopupWindow;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.content.Context;
import com.example.babycon.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DataBaseHelper myDb;
    public String ___id;
    public String _idchild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        myDb = new DataBaseHelper(this);


        Intent intent2 = getIntent();
        Bundle extras = intent2.getExtras();
        ___id = extras.getString("id");
        _idchild = extras.getString("idchild");


        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public Bundle getMyData() {
        Cursor id_child = myDb.checkBaby(___id);
        ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();

        if (id_child.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=2; i<id_child.getColumnCount();i++)
                {
                    map.put(id_child.getColumnName(i), id_child.getString(i));
                }

                maplist.add(map);
            } while (id_child.moveToNext());
        }

        Bundle hm = new Bundle();
        hm.putString("danedziecka", maplist.get(0).get("BABYNAME"));
        hm.putString("dataurodzenia", maplist.get(0).get("BIRTHDAY"));
        hm.putString("idchild", _idchild);
        return hm;
    }
}