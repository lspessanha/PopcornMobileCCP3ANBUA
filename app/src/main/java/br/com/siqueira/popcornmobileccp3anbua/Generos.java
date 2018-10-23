package br.com.siqueira.popcornmobileccp3anbua;

import java.io.Serializable;
import java.util.List;

public class Generos implements Serializable {

    private int id;
    private String nome;
    private List<Filmes> filmes;

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

    public List<Filmes> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filmes> filmes) {
        this.filmes = filmes;
    }

    public Generos(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}