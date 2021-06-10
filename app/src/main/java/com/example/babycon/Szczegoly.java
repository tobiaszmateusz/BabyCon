package com.example.babycon;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.babycon.model.SzczepieniaLista;
import com.example.babycon.model.SzczepieniaListaAdapter;
import com.example.babycon.model.WpisyLista;
import com.example.babycon.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Szczegoly extends Activity {

    private DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.szczegoly);


        myDb = new DataBaseHelper(this);
        Cursor szczepionki = myDb.getSzczepionki();
        ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();

        if (szczepionki.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=1; i<szczepionki.getColumnCount();i++)
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
            if(j%5 == 0){
                szczepienia.add(new SzczepieniaLista(name.get(j+3), "Czas: "+ name.get(j) + "\n" + "Opis: "+ name.get(j+4), R.drawable.ic_baseline_help_24));
            }
        }


        SzczepieniaListaAdapter szczepieniaAdapter = new SzczepieniaListaAdapter(this, szczepienia);
        ListView listView = (ListView) findViewById(R.id.listview_szczepienia);
        listView.setAdapter(szczepieniaAdapter);

    }
}

