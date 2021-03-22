package com.example.babycon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;


public class Daily extends Fragment {

    Context mContext;
    boolean isReverse;

    ImageView mWeekAscOrDesc;
    TextView mName ;
    TextView mAge;
    TextView mHeight;
    LinearLayout mLinearLayout;
    RecyclerView mRecyclerView;
    Button dodaj;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily, container, false);

    }

    private LinearLayoutManager getReverseManager(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        return linearLayoutManager;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mWeekAscOrDesc = (ImageView) getView().findViewById(R.id.updownimage);
        mLinearLayout = (LinearLayout) getView().findViewById(R.id.dateorder);
        mName = (TextView) getView().findViewById(R.id.imie2);
        mAge = (TextView) getView().findViewById(R.id.wiek2);
        mHeight = (TextView) getView().findViewById(R.id.wzrost2);

        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isReverse) {
                    isReverse = false;
                    mWeekAscOrDesc.setImageResource(android.R.drawable.arrow_up_float);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                } else {
                    isReverse = true;
                    mWeekAscOrDesc.setImageResource(android.R.drawable.arrow_down_float);
                    mRecyclerView.setLayoutManager(getReverseManager());
                }
            }
        });

        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        dodaj = getView().findViewById(R.id.dodaj);
        dodaj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getContext(), PopActivity.class);
                startActivity(intent);
            }
        });

        setData();
    }


    public void setData() {
        mName.setText("Jakub");
        mAge.setText("20 miesięcy");
        mHeight.setText("100 cm");
    }
}