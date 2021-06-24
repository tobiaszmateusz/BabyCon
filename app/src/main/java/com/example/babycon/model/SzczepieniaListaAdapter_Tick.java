package com.example.babycon.model;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babycon.PopSzczepienieActivity;
import com.example.babycon.R;
import com.example.babycon.Szczegoly;
import com.example.babycon.Szczepienia;

import java.util.ArrayList;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class SzczepieniaListaAdapter_Tick extends ArrayAdapter<SzczepieniaLista> {


/*
 * {@link AndroidFlavorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link AndroidFlavor} objects.
 * */
    Context mContext;
    private static final String LOG_TAG = SzczepieniaListaAdapter_Tick.class.getSimpleName();
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    private static String idCH = null;
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param szczepienia A List of AndroidFlavor objects to display in a list
     */
    public SzczepieniaListaAdapter_Tick(Activity context, ArrayList<SzczepieniaLista> szczepienia, String idchild) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, szczepienia);
        this.idCH = idchild;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_tick, parent, false);
        }

        mContext = parent.getContext();
        // Get the {@link AndroidFlavor} object located at this position in the list
        SzczepieniaLista currentszczepionka = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.version_name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentszczepionka.getVersionName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.version_number);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentszczepionka.getVersionNumber());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        iconView.setImageResource(currentszczepionka.getImageResourceId());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView

        CheckBox check = (CheckBox) listItemView.findViewById(R.id.zatwierdzenie);

        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Intent intent = new Intent(mContext, PopSzczepienieActivity.class);
                    intent.putExtra("idch", idCH);
                    intent.putExtra("nazwa", currentszczepionka.getVersionName());
                    mContext.startActivity(intent);
                }
            }
        });

        return listItemView;
    }

}
