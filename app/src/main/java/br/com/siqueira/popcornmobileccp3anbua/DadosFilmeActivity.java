package br.com.siqueira.popcornmobileccp3anbua;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import br.com.siqueira.popcornmobileccp3anbua.API.ResponseCredits;
import br.com.siqueira.popcornmobileccp3anbua.API.ResponseCrew;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DadosFilmeActivity extends AppCompatActivity {
    private TextView tituloTextView;
    private TextView direcaoTextView;
    private TextView popularidadeTextView;
    private TextView dataLancamentoTextView;
    private TextView descricaoTextView;
    private ImageView posterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_filme);
        Intent intent = getIntent();
        Filmes filme = (Filmes)intent.getSerializableExtra(ListaFilmesActivity.FILME);

        tituloTextView = (TextView) findViewById(R.id.titulo);
        direcaoTextView =(TextView) findViewById(R.id.direcao);
        popularidadeTextView = (TextView) findViewById(R.id.popularidadeText);
        dataLancamentoTextView = (TextView) findViewById(R.id.dataLancamento);
        descricaoTextView = (TextView) findViewById(R.id.descricao);
        posterImageView = findViewById(R.id.poster);

        tituloTextView.setText("ID " + filme.getId() + " - " + filme.getTitulo());
        direcaoTextView.setText((filme.getDiretor()));
        popularidadeTextView.setText(Double.toString(filme.getPopularidade()));
        descricaoTextView.setText(filme.getDescricao());
        WebServiceGetDirector director = new WebServiceGetDirector();
        director.execute(filme.getId());
        ImageGetter imageGetter = new ImageGetter();
        imageGetter.execute(filme.getPosterPath());
    }

    private class ImageGetter extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urlS) {
            try {
                URL url = new URL(getApplicationContext().getString(R.string.uri_image) + urlS[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream= connection.getInputStream();
                Bitmap figura = BitmapFactory.decodeStream(inputStream);
                return figura;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            posterImageView.setImageBitmap(bitmap);
        }
    }

    private class WebServiceGetDirector extends AsyncTask<Integer,Void,String> {

        @Override
        protected String doInBackground(Integer... idFilme) {
            try {
                String uri = getApplicationContext().getString(R.string.uri_director, idFilme[0]+"");
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
                ResponseCredits responseCredits = gson.fromJson(json, ResponseCredits.class);
                List<ResponseCrew> responseResults = responseCredits.getCrew();
                Boolean flag = true;
                Integer contator = 0;
                while (flag){
                    if(responseResults.get(contator).getJob().equals("Director")){
                        direcaoTextView.setText(responseResults.get(contator).getName());
                        flag = false;
                    }
                    contator++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}