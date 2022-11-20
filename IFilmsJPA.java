package es.uah.FilmsActores.dao;


import es.uah.FilmsActores.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface IFilmsJPA extends JpaRepository<Film , Integer> {

    List<Film> findByTituloContainingIgnoreCase(String titulo);

    List<Film> findByGeneroContainingIgnoreCase(String genero);

    List<Film> findByIdioma(String idioma);

    List<Film> findByRepartoContainingIgnoreCase(String reparto);

}
