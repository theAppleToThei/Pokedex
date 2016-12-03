package com.example.pokdex;

import android.os.Bundle;
import android.app.Activity;

public class PokemonDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);
        setTitle("Pokémon Details");
    }
}
