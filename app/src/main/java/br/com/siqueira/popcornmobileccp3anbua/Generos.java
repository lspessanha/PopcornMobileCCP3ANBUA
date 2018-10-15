package br.com.siqueira.popcornmobileccp3anbua;

import java.util.ArrayList;
import java.util.Arrays;

public class Generos {

    private int id;
    private String nome;
    private ArrayList<Generos> listaGeneros;

    public Generos(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Generos> getList() {
        return listaGeneros;
    }

    public void setList(ArrayList<Generos> listaGeneros) {
        this.listaGeneros = listaGeneros;
    }

    public ArrayList<Generos> getGeneros(){
        return new ArrayList<>(Arrays.asList(
                new Generos(16, "Animação"),
                new Generos(12, "Aventura"),
                new Generos(35, "Comédia"),
                new Generos(80, "Crime"),
                new Generos(99, "Documentário"),
                new Generos(18, "Drama"),
                new Generos(10751, "Família"),
                new Generos(14, "Fantasia"),
                new Generos(878, "Ficção Científica"),
                new Generos(10770, "Filme para TV"),
                new Generos(10752, "Guerra"),
                new Generos(36, "História"),
                new Generos(27, "Horror"),
                new Generos(9648, "Mistério"),
                new Generos(10402, "Musical"),
                new Generos(10749, "Romance"),
                new Generos(53, "Suspense"),
                new Generos(37, "Western")
            )
        );
    }
}