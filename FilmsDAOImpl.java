package es.uah.FilmsActores.dao;

import es.uah.FilmsActores.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class FilmsDAOImpl implements IFilmsDAO {

    @Autowired
    IFilmsJPA filmsJPA;

    @Override
    public List<Film> buscarTodos() {
        return filmsJPA.findAll();
    }

    @Override
    public Film buscarFilmPorId(Integer idFilm) {
        Optional<Film> optional = filmsJPA.findById(idFilm);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Film> buscarFilmsPorTitulo(String titulo) {
        return filmsJPA.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<Film> buscarFilmsPorGenero(String genero) {
        return filmsJPA.findByGeneroContainingIgnoreCase(genero);
    }

    @Override
    public List<Film> buscarFilmsPorIdioma(String idioma) {
        return filmsJPA.findByIdioma(idioma);
    }

    @Override
    public List<Film> buscarFilmsPorReparto(String reparto) {
        return filmsJPA.findByRepartoContainingIgnoreCase(reparto);
    }

    @Override
    public void guardarFilm(Film film) {
        filmsJPA.save(film);
    }

    @Override
    public void eliminarFilm(Integer idFilm) {
        filmsJPA.deleteById(idFilm);
    }

    @Override
    public void actualizarFilm(Film film) {
        filmsJPA.save(film);
    }

}
