package es.uah.FilmsActores.dao;

import es.uah.FilmsActores.model.Film;
import java.util.List;

public interface IFilmsDAO {
    List<Film> buscarTodos();

    Film buscarFilmPorId(Integer idFilm);

    List<Film> buscarFilmsPorTitulo(String titulo);

    List<Film> buscarFilmsPorGenero(String genero);

    List<Film> buscarFilmsPorIdioma(String idioma);

    List<Film> buscarFilmsPorReparto(String reparto);

    void guardarFilm(Film film);

    void eliminarFilm(Integer idFilm);

    void actualizarFilm(Film film);

}
