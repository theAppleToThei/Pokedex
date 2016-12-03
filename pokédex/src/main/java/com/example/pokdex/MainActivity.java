package com.example.pokdex;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pokdex.regionfragments.AlolaFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String dexLink = "";
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.Fragment fragment = null;

        if (id == R.id.national) {
            setTitle("National Pokédex");
        } else if (id == R.id.kanto) {
            setTitle("Kanto Pokédex");
        } else if (id == R.id.johto) {
            setTitle("Johto Pokédex");
        } else if (id == R.id.hoenn) {
            setTitle("Hoenn Pokédex");
        } else if (id == R.id.sinnoh) {
            setTitle("Sinnoh Pokédex");
        } else if (id == R.id.unova) {
            setTitle("Unova Pokédex");
        } else if (id == R.id.kalos) {
            setTitle("Kalos Pokédex");
        } else if (id == R.id.alola) {
            fragment = new AlolaFragment();
            setTitle("Alola Pokédex");
        } else if (id == R.id.dreamTeam) {
            setTitle("Dream Team");
        } else if (id == R.id.favorites) {
            setTitle("Favorites");
        } else if (id == R.id.legendary) {
            setTitle("Legendaries");
        }

        if (fragment == null) {
            fragment = new NationalFragment();
        }
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class getPokemon extends
            AsyncTask<String, String, String> {
        String link;

        @Override
        protected void onPreExecute() {
            Log.i("Pokedex", "Made it to onPreExecute()");
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            Log.i("Pokedex", "Made it to doInBackground()");

            try {
                URL url = new URL(params[0]);
                URLConnection connection;
                connection = url.openConnection();

                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                int responseCode = httpConnection.getResponseCode();

                String response;
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream in = httpConnection.getInputStream();
                    String json = getStringFromInputStream(in);
                    Log.i("Pokedex", "response = " + json);
                    if (json.equals("404 HTTP/2.0 404")) {
//                    new AlertDialog(this, R.drawable.warning, R.string.dex_404,
//                            R.string.footnote_404, mOnClickListener).show();
                    }
                } else {
                    Log.e("Pokedex", "Connection was not ok");
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Pokedex", "Error After Program Start: " + e);
            }

            return "Error";
        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onProgressUpdate(String... values) {
        }

        private final DialogInterface.OnClickListener mOnClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                    }
                };
    }
}

