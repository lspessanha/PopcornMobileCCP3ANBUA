package br.com.siqueira.popcornmobileccp3anbua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaFilmesActivity extends AppCompatActivity {

    private ListView listView;
    private List <Filmes> listaFilmes;
    private FilmesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);
        listView = (ListView) findViewById(R.id.lista_filmes);
        listaFilmes = new ArrayList<>();
        adapter = new FilmesAdapter(listaFilmes, this);
        listView.setAdapter(adapter);
        Intent intent = getIntent();

        listaFilmes.addAll(todosOsFilmes());
        adapter.notifyDataSetChanged();
        ListView.OnItemClickListener itemClickListener =
                (parent, view, position, id) -> {
                    Filmes oCaraQueFoiTocado = listaFilmes.get(position);
                    Intent detalhesIntent = new Intent(this, DadosFilmeActivity.class);
                    detalhesIntent.putExtra("oCaraQueFoiTocado", oCaraQueFoiTocado);
                    startActivity(detalhesIntent);
                };
        listView.setOnItemClickListener(itemClickListener);

    }

    public ArrayList<Filmes> buscaFilmes(String chave){
        ArrayList<Filmes> lista = todosOsFilmes();
        if (chave == null || chave.length() == 0){
            return lista;
        } else {
            ArrayList<Filmes> subLista = new ArrayList<>();
            for(Filmes filme:lista){
                if(filme.getDescricao().toUpperCase().contains(chave.toUpperCase())){
                    subLista.add(filme);
                }
            }
            return subLista;
        }
    }

    private ArrayList<Filmes> todosOsFilmes() {
        return new ArrayList<>(Arrays.asList(
                new Filmes(1, "Náufrago", "Descrição do filme", "Robert Zemeckis", "posterPath", "04/10/2017", 99, new Generos(18, "Drama")),
                new Filmes(2, "Blade Runner 2049", "Descrição do filme", "Dennis Villeneuve", "posterPath", "04/10/2017", 99, new Generos(53, "Suspense")),
                new Filmes(3, "Platoon", "Descrição do filme", "Oliver Stone", "posterPath", "26/02/1987", 99, new Generos(10752, "Guerra")),
                new Filmes(4, "Guerra nas Estrelas", "Descrição do filme", "George Lucas", "posterPath", "19/11/1977", 99, new Generos(878, "Ficção Científica")),
                new Filmes(5, "O Exterminador do Futuro", "Descrição do filme", "James Cameron", "posterPath", "26/10/1984", 99, new Generos(878, "Ficção Científica"))
            )
        );
    }

}