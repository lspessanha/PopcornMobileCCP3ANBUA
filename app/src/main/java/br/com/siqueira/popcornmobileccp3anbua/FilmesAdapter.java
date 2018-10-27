package br.com.siqueira.popcornmobileccp3anbua;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import br.com.siqueira.popcornmobileccp3anbua.API.ResponseCredits;
import br.com.siqueira.popcornmobileccp3anbua.API.ResponseCrew;

public class FilmesAdapter  extends ArrayAdapter<Filmes>{

    public FilmesAdapter(Context context, List<Filmes> cast ){
        super(context,-1,cast);
    }

    private static class ViewHolder{
        ImageView posterImageView;
        TextView titulo;
        TextView diretor;
        TextView dataLancamento;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent){
        Filmes filme = getItem(position);
        View raiz = null;
        ViewHolder viewHolder = null;
        Context context = getContext();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            raiz = inflater.inflate(R.layout.list_item, parent,false);
            viewHolder = new ViewHolder();
            raiz.setTag(viewHolder);
            viewHolder.posterImageView = raiz.findViewById(R.id.poster);
            viewHolder.titulo = raiz.findViewById(R.id.tituloFilme);
            viewHolder.diretor = raiz.findViewById(R.id.diretorFilme);
            viewHolder.dataLancamento = raiz.findViewById(R.id.dataLancamentoFilme);
        } else {
            raiz = convertView;
            viewHolder = (ViewHolder) raiz.getTag();
        }

        viewHolder.titulo.setText(filme.getTitulo() + " - ID " + filme.getId());
        viewHolder.dataLancamento.setText(filme.getDateFormat());
        WebServiceGetDirector director = new WebServiceGetDirector(filme);
        director.execute(filme.getId());
        viewHolder.diretor.setText(filme.getDiretor());
        return raiz;
    }

    private class ImageGetter extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urlS) {
            try {
                URL url = new URL(getContext().getString(R.string.uri_image) + urlS[0]);
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
//            posterImageView.setImageBitmap(bitmap);
        }
    }

    private class WebServiceGetDirector extends AsyncTask<Integer,Void,String> {

        Filmes fil;

        public WebServiceGetDirector(Filmes filme) {
            this.fil = filme;
        }

        @Override
        protected String doInBackground(Integer... idFilme) {
            try {
                String uri = getContext().getString(R.string.uri_director, idFilme[0]+"");
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
                Integer count = 0;
                while (flag){
                    if(responseResults.get(count).getJob().equals("Director")){
                        this.fil.setDiretor(responseResults.get(count).getName());
                        flag = false;
                    }
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}