package com.example.babycon;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.babycon.model.SzczepieniaLista;
import com.example.babycon.model.SzczepieniaListaAdapter;
import com.example.babycon.model.SzczepieniaListaAdapter_Tick;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Szczepienia#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Szczepienia extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DataBaseHelper myDb;

    public Szczepienia() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Szczepienia.
     */
    // TODO: Rename and change types and number of parameters

    Button szczegoly;
    TextView Nazwa;
    TextView Data;
    TextView Warning;

    public static Szczepienia newInstance(String param1, String param2) {
        Szczepienia fragment = new Szczepienia();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        szczegoly = getView().findViewById(R.id.szczepieniaszczeg);
        szczegoly.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getContext(), Szczegoly.class);
                startActivity(intent);
            }
        });

        MainActivity activity = (MainActivity)getActivity();
        Bundle results = activity.getMyData();
        String imie = results.getString("danedziecka");
        String dataUrodzenia = results.getString("dataurodzenia");
        String idchild = results.getString("idchild");



        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yy");
        String currentDateandTime = sdf2.format(new Date());
        int dateDifference = (int) getDateDiff(new SimpleDateFormat("MM/dd/yy"), dataUrodzenia, currentDateandTime);


        // Create an ArrayList of AndroidFlavor objects
/*        ArrayList<SzczepieniaLista> szczepienia = new ArrayList<SzczepieniaLista>();
        szczepienia.add(new SzczepieniaLista("aaa", "1.6", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("bbb", "2.0-2.1", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("ccc", "2.2-2.2.3", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("ddd", "2.3-2.3.7", R.drawable.ic_baseline_help_24));


        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        SzczepieniaListaAdapter szczepieniaAdapter = new SzczepieniaListaAdapter(getActivity(), szczepienia);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = view.findViewById(R.id.listview_szczepienia2);
        listView.setAdapter(szczepieniaAdapter);
        // Inflate the layout for this fragment*/

        myDb = new DataBaseHelper(getActivity());
        Cursor szczepionki = myDb.getSzczepionkiCH(idchild);
        ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();

        if (szczepionki.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<szczepionki.getColumnCount();i++)
                {
                    map.put(szczepionki.getColumnName(i), szczepionki.getString(i));
                }

                maplist.add(map);
            } while (szczepionki.moveToNext());
        }

        ArrayList<String> name = new ArrayList<String>();
        ArrayList<SzczepieniaLista> szczepienia = new ArrayList<SzczepieniaLista>();
        for(int i = 0; i < maplist.size(); i++)
        {
            for (Map.Entry<String, String> entry : maplist.get(i).entrySet())
            {
                name.add(entry.getValue());
            }
        }


        for (int j = 0; j < name.size(); j++){
            if(j%3 == 0){
                if(Integer.parseInt(name.get(j+1)) / 24 < dateDifference)
                {
                    szczepienia.add(new SzczepieniaLista(name.get(j+2), "\n"+"Czas: "+ name.get(j) + "\n\nOpóźnienie!", R.drawable.ic_baseline_help_24));

                }else
                {
                    szczepienia.add(new SzczepieniaLista(name.get(j+2) + "\n", "Czas: "+ name.get(j) + "\n", R.drawable.ic_baseline_help_24));

                }
            }
        }


        SzczepieniaListaAdapter_Tick szczepieniaAdapter = new SzczepieniaListaAdapter_Tick(getActivity(), szczepienia, idchild);
        ListView listView = view.findViewById(R.id.listview_szczepienia2);
        listView.setAdapter(szczepieniaAdapter);

        Nazwa = getView().findViewById(R.id.eNazwa);
        Data = getView().findViewById(R.id.eData);
        Warning = getView().findViewById(R.id.eWarning);

        int zaile = Integer.parseInt(name.get(1));
        String dataSzczepiionki = null;
        int dniurodzenia = Integer.parseInt(dnitwo(dataUrodzenia));
        int miesiacurodzenia = Integer.parseInt(miesiactwo(dataUrodzenia));
        int rokurodzenia = Integer.parseInt(roktwo(dataUrodzenia));
        int dniszczepiomki = dniurodzenia;
        int miesiacszczepiomki = miesiacurodzenia;
        int rokszczepionki = rokurodzenia;
        for (int j = 0; j < zaile/24; j++) {
            dniszczepiomki = dniszczepiomki + 1;
            if (dniszczepiomki == 28 && miesiacszczepiomki == 2)
            {
                miesiacszczepiomki = 3;
            }
            if (dniszczepiomki == 30 && miesiacszczepiomki == 4)
            {
                miesiacszczepiomki = 5;
            }
            if (dniszczepiomki == 30 && miesiacszczepiomki == 6)
            {
                miesiacszczepiomki = 7;
            }
            if (dniszczepiomki == 30 && miesiacszczepiomki == 9)
            {
                miesiacszczepiomki = 10;
            }
            if (dniszczepiomki == 30 && miesiacszczepiomki == 11)
            {
                miesiacszczepiomki = 11;
            }
            if (dniszczepiomki == 31 && miesiacszczepiomki == 1)
            {
                miesiacszczepiomki = 2;
            }
            if (dniszczepiomki == 31 && miesiacszczepiomki == 3)
            {
                miesiacszczepiomki = 4;
            }
            if (dniszczepiomki == 31 && miesiacszczepiomki == 5)
            {
                miesiacszczepiomki = 6;
            }
            if (dniszczepiomki == 31 && miesiacszczepiomki == 7)
            {
                miesiacszczepiomki = 8;
            }
            if (dniszczepiomki == 31 && miesiacszczepiomki == 8)
            {
                miesiacszczepiomki = 9;
            }
            if (dniszczepiomki == 31 && miesiacszczepiomki == 10)
            {
                miesiacszczepiomki = 11;
            }
            if (dniszczepiomki == 31 && miesiacszczepiomki == 12)
            {
                miesiacszczepiomki = 1;
                rokszczepionki = rokszczepionki + 1;
            }
        }

        dataSzczepiionki = dniszczepiomki + "/" + miesiacszczepiomki + "/" + rokszczepionki;
        String dataSzczepiionki2 = miesiacszczepiomki + "/" + dniszczepiomki + "/" + rokszczepionki;

        int dateDifference2 = (int) getDateDiff(new SimpleDateFormat("MM/dd/yy"), currentDateandTime, dataSzczepiionki2);


        Data.setText(dataSzczepiionki);
        Nazwa.setText(name.get(2));

        if (dateDifference2 < 0) {
            Warning.setText("Opóźnienie " + dateDifference2 + " DNI");
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_szczepienia, container, false);


        return view;

    }

    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String dnitwo(String str) {
        String[] temp = str.split("/");
        return temp[1];
    }

    public String miesiactwo(String str) {
        String[] temp = str.split("/");
        return temp[0];
    }

    public String roktwo(String str) {
        String[] temp = str.split("/");
        return temp[2];
    }
}