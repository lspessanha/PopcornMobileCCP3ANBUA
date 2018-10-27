package br.com.siqueira.popcornmobileccp3anbua;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import br.com.siqueira.popcornmobileccp3anbua.API.ResponseApiFilme;
import br.com.siqueira.popcornmobileccp3anbua.API.ResponseResult;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListaFilmesActivity extends AppCompatActivity {
    public static final String FILME = "filme";
    List<Filmes> filmesList;
    private FilmesAdapter filmesAdapter;
    private ListView filmeListView;
    Activity atividade;
    private String generoSelect;
    private String generoSelectId;
    private Generos genero;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);
        atividade = this;
        Intent intent = getIntent();
        generoSelect = intent.getStringExtra("generoSelect");
        generoSelectId = intent.getStringExtra("generoSelectId");
        genero = new Generos(Integer.parseInt(generoSelectId), generoSelect);

        filmesList = new ArrayList<>();
        filmeListView = findViewById(R.id.filmesListView);

        WebServiceListFilmes listFilmes = new WebServiceListFilmes();
        listFilmes.execute(genero.getId());

        filmeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                Intent intent = new Intent(atividade, DadosFilmeActivity.class);
                intent.putExtra("filme",filmesList.get(position));
                startActivity(intent);
            }
        });
    }

    private class WebServiceListFilmes extends AsyncTask<Integer,Void,String> {

        @Override
        protected String doInBackground(Integer... idGenero) {
            try {
                String uri = getApplicationContext().getString(R.string.uri_listFilmes) + idGenero[0]+"";
                URL url = new URL(uri);
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
                ResponseApiFilme responseApiFilme = gson.fromJson(json, ResponseApiFilme.class);
                List<ResponseResult> responseResults = responseApiFilme.getResults();
                for (ResponseResult r: responseResults){
                    Filmes filme = new Filmes(
                            r.getId(),
                            r.getTitle(),
                            r.getOverview(),
                            r.getPopularity(),
                            r.getRelease_date(),
                            r.getPoster_path(),
                            r.getDirector(),
                            genero);
                    filmesList.add(filme);
                }

                filmesAdapter = new FilmesAdapter(getApplicationContext(), filmesList);

                filmeListView.setAdapter(filmesAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}