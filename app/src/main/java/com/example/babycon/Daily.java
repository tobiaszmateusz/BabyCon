package com.example.babycon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.babycon.model.BasicModel;
import com.example.babycon.model.SzczepieniaLista;
import com.example.babycon.model.SzczepieniaListaAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Daily extends Fragment {

    Context mContext;
    boolean isReverse;

    ImageView mWeekAscOrDesc;
    TextView mName ;
    TextView mAge;
    TextView mHeight;
    TextView mSortowanie;
    RecyclerView mRecyclerView;
    Button dodaj;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_daily, container, false);

        mName = (TextView)rootView.findViewById(R.id.imie2);
        mAge = (TextView)rootView.findViewById(R.id.wiek2);
        MainActivity activity = (MainActivity)getActivity();
        Bundle results = activity.getMyData();
        String imie = results.getString("danedziecka");
        String dataUrodzenia = results.getString("dataurodzenia");


        java.util.Date date1 = new java.util.Date();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy"); // here set the pattern as you date in string was containing like date/month/year
            Date d = sdf.parse(dataUrodzenia);
        }catch(ParseException ex){
            // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
        }

        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yy");
        String currentDateandTime = sdf2.format(new Date());
        int dateDifference = (int) getDateDiff(new SimpleDateFormat("dd/MM/yy"), dataUrodzenia, currentDateandTime);


        mName.setText(imie);
        mAge.setText(dateDifference+ " dni");

        return rootView;
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
        mSortowanie = getView().findViewById(R.id.sortowanie);

        mSortowanie.setOnClickListener(new View.OnClickListener() {
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

/*        setData();*/


        ArrayList<SzczepieniaLista> szczepienia = new ArrayList<SzczepieniaLista>();
        szczepienia.add(new SzczepieniaLista("aaa", "1.6", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("bbb", "2.0-2.1", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("ccc", "2.2-2.2.3", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("ddd", "2.3-2.3.7", R.drawable.ic_baseline_help_24));


        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        SzczepieniaListaAdapter szczepieniaAdapter = new SzczepieniaListaAdapter(getActivity(), szczepienia);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = view.findViewById(R.id.listview_wpisy);
        listView.setAdapter(szczepieniaAdapter);
    }


    public void setData() {
        mName.setText("Jakub");
        mAge.setText("20 miesięcy");
    }

    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}