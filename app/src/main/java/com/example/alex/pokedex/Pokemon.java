package com.example.alex.pokedex;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alex on 7/9/16.
 */
public class Pokemon {

    String name;
    int pokedexNumber;

    public Pokemon(String pokeJSON) throws JSONException {
        JSONObject jsonOb;
        jsonOb = new JSONObject(pokeJSON);

        name = jsonOb.getString("name");
        pokedexNumber = Integer.parseInt(jsonOb.getString("id"));
        Log.i("Pokedex", "Created " + name + " with Pokédex number " + pokedexNumber + ".");
    }
}
