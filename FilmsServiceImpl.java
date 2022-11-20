package es.uah.FilmsActores.service;

import es.uah.FilmsActores.dao.IFilmsDAO;
import es.uah.FilmsActores.model.Film;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class FilmsServiceImpl implements IFilmsService{

    @Autowired
    IFilmsDAO filmsDAO;

    @Override
    public List<Film> buscarTodos() {
        return filmsDAO.buscarTodos();
    }

    @Override
    public Film buscarFilmPorId(Integer idFilm) {
        return filmsDAO.buscarFilmPorId(idFilm);
    }

    @Override
    public List<Film> buscarFilmsPorTitulo(String titulo) {
        return filmsDAO.buscarFilmsPorTitulo(titulo);
    }

    @Override
    public List<Film> buscarFilmsPorGenero(String genero) {
        return filmsDAO.buscarFilmsPorGenero(genero);
    }

    @Override
    public List<Film> buscarFilmsPorIdioma(String idioma) {
        return filmsDAO.buscarFilmsPorIdioma(idioma);
    }

    @Override
    public List<Film> buscarFilmsPorReparto(String reparto) {
        return filmsDAO.buscarFilmsPorReparto(reparto);
    }

    @Override
    public void guardarFilm(Film film) {
        filmsDAO.guardarFilm(film);

    }

    @Override
    public void actualizarFilm(Film film) {
        if (filmsDAO.buscarFilmPorId(film.getIdFilm())!=null) {
            filmsDAO.actualizarFilm(film);
        }
    }

    @Override
    public void eliminarFilm(Integer idFilm) {
        if (filmsDAO.buscarFilmPorId(idFilm)!=null) {
            filmsDAO.eliminarFilm(idFilm);
        }
    }
}



