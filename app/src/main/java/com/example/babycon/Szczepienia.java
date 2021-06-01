package com.example.babycon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.babycon.model.SzczepieniaLista;
import com.example.babycon.model.SzczepieniaListaAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_szczepienia, container, false);
        // Create an ArrayList of AndroidFlavor objects
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
        ListView listView = view.findViewById(R.id.listview_szczepienia2);
        listView.setAdapter(szczepieniaAdapter);
        // Inflate the layout for this fragment
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
}