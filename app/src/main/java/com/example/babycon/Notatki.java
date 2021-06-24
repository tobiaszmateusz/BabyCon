package com.example.babycon;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babycon.model.MiloweLista;
import com.example.babycon.model.MiloweListaAdapter;
import com.example.babycon.model.SzczepieniaLista;
import com.example.babycon.model.SzczepieniaListaAdapter_Tick;
import com.example.babycon.model.WpisyLista;
import com.example.babycon.model.WpisyListaAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class Notatki extends Fragment {

    Button dodajmil;
    private DataBaseHelper myDb;

    public static Notatki newInstance() {
        Notatki fragment = new Notatki();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notatki, container, false);

        return rootView;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        MainActivity activity = (MainActivity)getActivity();
        Bundle results = activity.getMyData();
        String imie = results.getString("danedziecka");
        String dataUrodzenia = results.getString("dataurodzenia");
        String idchild = results.getString("idchild");


        myDb = new DataBaseHelper(getActivity());
        Cursor milowe = myDb.getMilowe(idchild);
        ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();

        if (milowe.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<milowe.getColumnCount();i++)
                {
                    map.put(milowe.getColumnName(i), milowe.getString(i));
                }

                maplist.add(map);
            } while (milowe.moveToNext());
        }

        ArrayList<String> name = new ArrayList<String>();
        ArrayList<MiloweLista> milowelista = new ArrayList<MiloweLista>();
        for(int i = 0; i < maplist.size(); i++)
        {
            for (Map.Entry<String, String> entry : maplist.get(i).entrySet())
            {
                name.add(entry.getValue());
            }
        }


        for (int j = 0; j < name.size(); j++){
            if(j%4 == 0){
                milowelista.add(new MiloweLista(name.get(j+3), "\n" + "Data: " + name.get(j), R.drawable.ic_baseline_help_24));
            }
        }


        MiloweListaAdapter miloweAdapter = new MiloweListaAdapter(getActivity(), milowelista, idchild);
        ListView listView = view.findViewById(R.id.listview_milowe);
        listView.setAdapter(miloweAdapter);




        dodajmil = Objects.requireNonNull(getView()).findViewById(R.id.dodajmilowy);
        dodajmil.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getContext(), PopMiloweActivity.class);
                intent.putExtra("idch", idchild);
                startActivity(intent);
            }
        });
    }
}