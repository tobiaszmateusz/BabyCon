package com.example.babycon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import com.example.babycon.model.WpisyLista;
import com.example.babycon.model.WpisyListaAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    Button fabb;
    String wzrost = null;
    private DataBaseHelper myDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_daily, container, false);

        return rootView;
    }

    private LinearLayoutManager getReverseManager(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        return linearLayoutManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mWeekAscOrDesc = (ImageView) getView().findViewById(R.id.updownimage);
        mSortowanie = getView().findViewById(R.id.sortowanie);


        mName = (TextView)getView().findViewById(R.id.imie2);
        mAge = (TextView)getView().findViewById(R.id.wiek2);
        mHeight = (TextView)getView().findViewById(R.id.wzrost2);
        MainActivity activity = (MainActivity)getActivity();
        Bundle results = activity.getMyData();
        String imie = results.getString("danedziecka");
        String dataUrodzenia = results.getString("dataurodzenia");
        String idchild = results.getString("idchild");
        String plec = results.getString("plec");

        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yy");
        String currentDateandTime = sdf2.format(new Date());
        int dateDifference = (int) getDateDiff(new SimpleDateFormat("MM/dd/yy"), dataUrodzenia, currentDateandTime);

        mName.setText(imie);
        mAge.setText(dateDifference+ " dni");

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


        dodaj = getView().findViewById(R.id.dodaj);
        dodaj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getContext(), PopActivity.class);
                intent.putExtra("idchild", idchild);
                startActivity(intent);
            }
        });

/*        setData();*/


        myDb = new DataBaseHelper(getActivity());
        Cursor id_child = myDb.getWpisy(idchild);
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


        ArrayList<String> name = new ArrayList<String>();
        ArrayList<WpisyLista> wpisy = new ArrayList<WpisyLista>();
        for(int i = 0; i < maplist.size(); i++)
        {
            for (Map.Entry<String, String> entry : maplist.get(i).entrySet())
            {
                name.add(entry.getValue());
            }
        }

        for (int j = 0; j < name.size(); j++){
            if(j%6 == 0){
                wpisy.add(new WpisyLista(name.get(j+1), "Obówd głowy: " + name.get(j+2) + " \n" + "Obówd klatki: "+ name.get(j+3)+ " \n" + "Waga: "+ name.get(j+4) + " \n" + "Wzrost: "+ name.get(j) + " \n" + "Notatka: "+ name.get(j+5), R.drawable.ic_baseline_help_24));
            }
        }

        if (name != null)
        {
            wzrost=name.get(0);
        }else{
            wzrost="999";
        }

        fabb = getView().findViewById(R.id.fab);
        fabb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Fabb.class);
                intent.putExtra("wiek",dateDifference);
                intent.putExtra("wzrost",wzrost);
                intent.putExtra("plec",plec);
                startActivity(intent);
            }
        });


        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        WpisyListaAdapter wpisyAdapter = new WpisyListaAdapter(getActivity(), wpisy);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = view.findViewById(R.id.listview_wpisy);
        listView.setAdapter(wpisyAdapter);
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