package com.example.pokdex;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alex on 7/9/16.
 */
public class Pokemon {

    String name;
    int nationalPokedexNumber;
    int region; // National is 0, Kanto is 1, ..., Alola is 7
    int regionalPokedexNumber;
    int[] types = {-1, -1};
    Drawable sprite;
    Context context;

    public Pokemon(String pokeJSON) throws JSONException {
        JSONObject jsonOb;
        jsonOb = new JSONObject(pokeJSON);

        name = jsonOb.getString("name");
        nationalPokedexNumber = Integer.parseInt(jsonOb.getString("id"));
        Log.i("Pokedex", "Created " + name + " with Pokédex number " + nationalPokedexNumber + ".");
    }

    public Pokemon(Context context, String name, int nationalPokedexNumber, int region, int regionalPokedexNumber, int type1, int type2, int sprite) {
        this.context = context;
        this.name = name;
        this.nationalPokedexNumber = nationalPokedexNumber;
        this.region = region;
        this.regionalPokedexNumber = regionalPokedexNumber;
        this.types[0] = type1;
        this.types[1] = type2;
        this.sprite = ResourcesCompat.getDrawable(context.getResources(), sprite, null);
    }

    public Pokemon(Context context, String name, int nationalPokedexNumber, int region, int regionalPokedexNumber, int type, int sprite) {
        this.context = context;
        this.name = name;
        this.nationalPokedexNumber = nationalPokedexNumber;
        this.region = region;
        this.regionalPokedexNumber = regionalPokedexNumber;
        this.types[0] = type;
        this.sprite = ResourcesCompat.getDrawable(context.getResources(), sprite, null);
    }

    public String getNationalPokedexNumber() {
        return String.valueOf(nationalPokedexNumber);
    }
}
