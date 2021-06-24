package com.example.babycon;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.babycon.model.SzczepieniaLista;
import com.example.babycon.model.SzczepieniaListaAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wykonane extends Activity {

    private DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wykonane);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String idch = extras.getString("idchild");

        myDb = new DataBaseHelper(this);
        Cursor szczepionkiwykonane = myDb.getSzczepionkiWyk(idch);
        ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();

        if (szczepionkiwykonane.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<szczepionkiwykonane.getColumnCount();i++)
                {
                    map.put(szczepionkiwykonane.getColumnName(i), szczepionkiwykonane.getString(i));
                }

                maplist.add(map);
            } while (szczepionkiwykonane.moveToNext());
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
            if(j%2 == 0){
                szczepienia.add(new SzczepieniaLista(name.get(j+1), "Wykonane: "+ name.get(j), R.drawable.ic_baseline_help_24));
            }
        }


        SzczepieniaListaAdapter szczepieniaAdapter = new SzczepieniaListaAdapter(this, szczepienia);
        ListView listView = (ListView) findViewById(R.id.listview_wykonane);
        listView.setAdapter(szczepieniaAdapter);

    }
}

