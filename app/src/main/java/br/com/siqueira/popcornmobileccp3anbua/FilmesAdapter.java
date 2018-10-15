package br.com.siqueira.popcornmobileccp3anbua;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FilmesAdapter extends ArrayAdapter <Filmes> {

    private List <Filmes> filmes;
    private Context context;
    public FilmesAdapter (List <Filmes> filmes, Context context){
        super(context, -1, filmes);
        this.filmes = filmes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return filmes.size();
    }

    @Override
    public Filmes getItem(int position) {
        return filmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Filmes deInteresse = filmes.get(position);
        TextView tituloDoItemTextView;
        TextView descricaoDoItemTextView;
        ViewHolder vh;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            tituloDoItemTextView = convertView.findViewById(R.id.tituloDoItemTextView);
            descricaoDoItemTextView = convertView.findViewById(R.id.descricaoDoItemTextView);
            vh = new ViewHolder();
            vh.tituloDoItemTextView = tituloDoItemTextView;
            vh.descricaoDoItemTextView = descricaoDoItemTextView;
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
        vh.tituloDoItemTextView.setText(deInteresse.getTitulo());
        vh.descricaoDoItemTextView.setText(deInteresse.getDescricao());
        return convertView;
    }

    private class ViewHolder {
        TextView tituloDoItemTextView;
        TextView descricaoDoItemTextView;
    }
}
