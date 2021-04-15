package com.example.babycon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.babycon.model.SzczepieniaLista;
import com.example.babycon.model.SzczepieniaListaAdapter;
import com.example.babycon.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Szczegoly extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.szczegoly);

        // Create an ArrayList of AndroidFlavor objects
        ArrayList<SzczepieniaLista> szczepienia = new ArrayList<SzczepieniaLista>();
        szczepienia.add(new SzczepieniaLista("aaa", "1.6", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("bbb", "2.0-2.1", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("ccc", "2.2-2.2.3", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("ddd", "2.3-2.3.7", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("eee", "3.0-3.2.6", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("fff", "4.0-4.0.4", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("ggg", "4.1-4.3.1", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("hhh", "4.4-4.4.4", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("iii", "5.0-5.1.1", R.drawable.ic_baseline_help_24));
        szczepienia.add(new SzczepieniaLista("jjj", "6.0-6.0.1", R.drawable.ic_baseline_help_24));

        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        SzczepieniaListaAdapter szczepieniaAdapter = new SzczepieniaListaAdapter(this, szczepienia);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_szczepienia);
        listView.setAdapter(szczepieniaAdapter);
    }
}

