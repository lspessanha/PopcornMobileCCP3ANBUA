package br.com.siqueira.popcornmobileccp3anbua;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerGeneros;
    String genero = "todos";
    public static final String CHAVE_GENERO = "textGenero";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerGeneros = (Spinner)findViewById(R.id.generos);
        spinnerGeneros.setOnItemSelectedListener(new FilmeSelecionado());
    }

    public void listarFilmes(View view ){
        Intent intent = new Intent(this,ListaFilmesActivity.class);
        intent.putExtra(CHAVE_GENERO,genero);
        startActivity(intent);
    }

    private class FilmeSelecionado implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent,View view,int position,long id){
            genero = (String) parent.getItemAtPosition(position);
        }
        public void onNothingSelected(AdapterView<?> parent){

        }
    }
}