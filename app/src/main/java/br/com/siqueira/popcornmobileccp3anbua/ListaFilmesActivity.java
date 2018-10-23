package br.com.siqueira.popcornmobileccp3anbua;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaFilmesActivity extends AppCompatActivity {
    public static final String FILME = "filme";
    List<Filmes> filmesList;
    private FilmesAdapter filmesAdapter;
    private ListView filmeListView;
    Activity atividade;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);
        atividade = this;
        Intent intent = getIntent();
        String genero = intent.getStringExtra(MainActivity.CHAVE_GENERO);
        filmesList = DataFilmes.listarFilmes(genero);
        filmeListView = findViewById(R.id.filmesListView);
        System.out.println(filmesList);
        filmesAdapter = new FilmesAdapter(this, filmesList);
        filmeListView.setAdapter(filmesAdapter);

        filmeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                Intent intent = new Intent(atividade, DadosFilmeActivity.class);
                intent.putExtra(FILME,filmesList.get(position));
                startActivity(intent);
            }
        });
    }
}