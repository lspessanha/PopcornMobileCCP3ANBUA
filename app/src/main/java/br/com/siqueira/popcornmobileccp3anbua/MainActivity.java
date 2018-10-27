package br.com.siqueira.popcornmobileccp3anbua;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import br.com.siqueira.popcornmobileccp3anbua.API.ResponseGenero;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Object generoSelect;
    private int generoSelectId;
    Spinner spinnerGeneros;
    private List<Generos> spinnerArrayGeneros;
    private ArrayAdapter<Generos> adapterSpinnerGeneros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerGeneros = (Spinner)findViewById(R.id.generos);
        spinnerGeneros.setOnItemSelectedListener(new GeneroSelecionado());

        WebServiceGeneros getGeneros = new WebServiceGeneros();
        getGeneros.execute("");
    }

    public void listarFilmes(View view){
        Generos gen = (Generos) generoSelect;
        generoSelectId = gen.getId();

        Intent intent = new Intent(MainActivity.this, ListaFilmesActivity.class);
        intent.putExtra("generoSelect", generoSelect.toString());
        intent.putExtra("generoSelectId", String.valueOf(generoSelectId));
        startActivity(intent);
    }

    private class GeneroSelecionado implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
            generoSelect = parent.getItemAtPosition(position);
        }
        public void onNothingSelected(AdapterView<?> parent){

        }
    }

    private class WebServiceGeneros extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... generos) {
            try {
                URL url = new URL(getApplicationContext().getString(R.string.uri_genres));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String linha = null;
                StringBuilder stringBuilder = new StringBuilder("");
                while ((linha = reader.readLine()) != null) {
                    stringBuilder.append(linha);
                }
                String json = stringBuilder.toString();
                return json;
            } catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String json) {
            try {
                Gson gson = new Gson();
                ResponseGenero responseGenero = gson.fromJson(json, ResponseGenero.class);
                spinnerArrayGeneros = responseGenero.getGenres();
                adapterSpinnerGeneros = new ArrayAdapter<Generos>(
                        getApplicationContext(),
                        R.layout.spinner_item,
                        spinnerArrayGeneros
                );
                spinnerGeneros.setAdapter(adapterSpinnerGeneros);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}