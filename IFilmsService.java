package es.uah.FilmsActores.service;
import es.uah.FilmsActores.model.Film;
import java.util.List;

public interface IFilmsService {

    List<Film> buscarTodos();

    Film buscarFilmPorId(Integer idFilm);

    List<Film> buscarFilmsPorTitulo(String titulo);

    List<Film> buscarFilmsPorGenero(String genero);

    List<Film> buscarFilmsPorIdioma(String idioma);

    List<Film> buscarFilmsPorReparto(String reparto);


    void guardarFilm(Film film);

    void actualizarFilm(Film film);

    void eliminarFilm(Integer idFilm);

}
