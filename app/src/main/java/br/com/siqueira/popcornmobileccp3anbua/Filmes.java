package br.com.siqueira.popcornmobileccp3anbua;

import java.io.Serializable;
import java.util.ArrayList;

public class Filmes implements Serializable {

    private int id;
    private String titulo;
    private String descricao;
    private String diretor;
    private String posterPath;
    private String dataLancamento;
    private double popularidade;
    private Generos genero;
    private ArrayList<Filmes> listaFilmes;

    public Filmes(int id, String titulo, String descricao, String diretor, String posterPath, String dataLancamento, double popularidade, Generos genero) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.diretor = diretor;
        this.posterPath = posterPath;
        this.dataLancamento = dataLancamento;
        this.popularidade = popularidade;
        this.genero = genero;
    }

    public Filmes() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(double popularidade) {
        this.popularidade = popularidade;
    }

    public Generos getGenero() {
        return genero;
    }

    public void setGenero(Generos genero) {
        this.genero = genero;
    }

    public ArrayList<Filmes> getList() {
        return listaFilmes;
    }

    public void setList(ArrayList<Filmes> listaFilmes) {
        this.listaFilmes = listaFilmes;
    }

    @Override
    public String toString() {
        return titulo + " -  ID: " + id + "\n" + diretor + " " + dataLancamento;
    }
}
