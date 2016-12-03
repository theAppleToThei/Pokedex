package com.example.pokdex;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pokdex.R;

/**
 * Created by alex on 12/3/16.
 */

public class NationalFragment extends android.support.v4.app.Fragment {

    TableLayout nationalTable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.national_fragment, container, false);
        Pokemon bulbasaur = new Pokemon(this.getContext(), "Bulbasaur", 1, 1, 1, 12, 4, R.drawable.bulbasaur);
        nationalTable = (TableLayout) view.findViewById(R.id.nationalTable);
        TableRow bulbasaurRow = new TableRow(this.getContext());
        TextView number = new TextView(this.getContext());
        number.setText(bulbasaur.getNationalPokedexNumber());
        nationalTable.addView(bulbasaurRow);
        bulbasaurRow.addView(number);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
    }
}
