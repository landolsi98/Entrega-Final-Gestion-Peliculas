package es.uah.cFilmsActores.service;

import es.uah.cFilmsActores.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFilmsService {

    Page<Film> buscarTodos(Pageable pageable);
    Film buscarFilmPorId (Integer idFilm);
    Page <Film> buscarFilmsPorTitulo(String titulo,Pageable pageable);
    Page<Film> buscarFilmsPorGenero(String genero, Pageable pageable);
    Page<Film> buscarFilmsPorIdioma(String idioma, Pageable pageable);

    Page<Film> buscarFilmsPorReparto(String reparto, Pageable pageable);

    void guardarFilm(Film film);
    void eliminarFilm(Integer idFilm);

}