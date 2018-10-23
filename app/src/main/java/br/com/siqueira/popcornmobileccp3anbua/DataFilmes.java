package br.com.siqueira.popcornmobileccp3anbua;

import java.util.ArrayList;
import java.util.Arrays;

public class DataFilmes {

    public static ArrayList<String> listarNomes(ArrayList<Filmes> filmes){
        ArrayList<String> nomes = new ArrayList<>();
        for(Filmes filme: filmes){
            nomes.add(filme.getTitulo());
        }
        return nomes;
    }

    public static ArrayList<Filmes> listarFilmes(String genero){
        Filmes[] lista;
        ArrayList<Filmes> filmes =  new ArrayList<>();
        for(Filmes filme: todosOsFilmes()){
            if(filme.getGenero().equals(genero) || genero.equals("Todos")){
                filmes.add(filme);
            }
        }
        lista = filmes.toArray(new Filmes[0]);

        Arrays.sort(lista);
        filmes = new ArrayList<>();
        for(int i = 0; i < lista.length; i++){
            filmes.add(lista[i]);
        }
        return  filmes;
    }

    private static ArrayList<Filmes> todosOsFilmes(){

        ArrayList<Filmes> filmes = new ArrayList<>();
        Filmes filme;
        filme = new Filmes();
        filme.setId(1);
        filme.setTitulo("Náufrago");
        filme.setDescricao("Descrição do filme");
        filme.setPopularidade(99.0);
        filme.setDataLancamento("04/10/2017");
        filme.setPosterPath("posterPath");
        filme.setDiretor("Robert Zemeckis");
        filme.setGenero("Drama");
        filmes.add(filme);

        filme = new Filmes();
        filme.setId(2);
        filme.setTitulo("Blade Runner 2049");
        filme.setDescricao("Descrição do filme");
        filme.setPopularidade(99.0);
        filme.setDataLancamento("04/10/2017");
        filme.setPosterPath("posterPath");
        filme.setDiretor("Dennis Villeneuve");
        filme.setGenero("Suspense");
        filmes.add(filme);

        filme = new Filmes();
        filme.setId(3);
        filme.setTitulo("Platoon");
        filme.setDescricao("Descrição do filme");
        filme.setPopularidade(99.0);
        filme.setDataLancamento("26/02/1987");
        filme.setPosterPath("posterPath");
        filme.setDiretor("Oliver Stone");
        filme.setGenero("Guerra");
        filmes.add(filme);

        filme = new Filmes();
        filme.setId(4);
        filme.setTitulo("Guerra nas Estrelas");
        filme.setDescricao("Descrição do filme");
        filme.setPopularidade(99.0);
        filme.setDataLancamento("19/11/1977");
        filme.setPosterPath("posterPath");
        filme.setDiretor("George Lucas");
        filme.setGenero("Ficção Científica");
        filmes.add(filme);

        filme = new Filmes();
        filme.setId(5);
        filme.setTitulo("O Exterminador do Futuro");
        filme.setDescricao("Descrição do filme");
        filme.setPopularidade(99.0);
        filme.setDataLancamento("26/10/1984");
        filme.setPosterPath("posterPath");
        filme.setDiretor("James Cameron");
        filme.setGenero("Ficção Científica");
        filmes.add(filme);

        return filmes;
    }

}