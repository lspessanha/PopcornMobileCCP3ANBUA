package br.com.siqueira.popcornmobileccp3anbua.API;

import br.com.siqueira.popcornmobileccp3anbua.Generos;
import java.util.List;

public class ResponseGenero {

    private List<Generos> genres;

    public List<Generos> getGenres() {
        return genres;
    }

    public void setGenres(List<Generos> genres) {
        this.genres = genres;
    }

}
