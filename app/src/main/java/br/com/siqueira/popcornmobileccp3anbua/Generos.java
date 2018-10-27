package br.com.siqueira.popcornmobileccp3anbua;

import java.io.Serializable;
import java.util.List;

public class Generos implements Serializable {

    private int id;
    private String name;
    private List<Filmes> filmes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Filmes> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filmes> filmes) {
        this.filmes = filmes;
    }

    public Generos(int id, String nome) {
        this.id = id;
        this.name = nome;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    @Override
    public String toString() {
        return name;
    }

}
