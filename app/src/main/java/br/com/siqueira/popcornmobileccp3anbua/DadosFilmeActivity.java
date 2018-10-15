package br.com.siqueira.popcornmobileccp3anbua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DadosFilmeActivity extends AppCompatActivity {
    TextView id, titulo, descricao, diretor, dataLancamento, popularidade, genero;

    private TextView tituloFilmeTextView;
    private TextView descricaoFilmeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_filme);
        tituloFilmeTextView =
                findViewById(R.id.tituloDoItemTextView);
        descricaoFilmeTextView =
                findViewById(R.id.descricaoDoItemTextView);
        Intent intent = getIntent();
        Filmes oCaraQueFoiTocado = (Filmes)
                intent.getSerializableExtra("oCaraQueFoiTocado");
        tituloFilmeTextView.setText(oCaraQueFoiTocado.getTitulo().trim());
        descricaoFilmeTextView.setText(oCaraQueFoiTocado.getDescricao().trim());
    }

}