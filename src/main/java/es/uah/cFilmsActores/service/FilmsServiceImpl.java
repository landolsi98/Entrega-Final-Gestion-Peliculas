package es.uah.cFilmsActores.service;
import es.uah.cFilmsActores.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FilmsServiceImpl implements es.uah.cFilmsActores.service.IFilmsService {
    @Autowired
    RestTemplate template;

    String url ="http://localhost:8001/films";

    @Override
    public Page<Film> buscarTodos(Pageable pageable){
        Film [] films = template.getForObject(url, Film[].class);
        List<Film> filmsList = Arrays.asList(films);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List <Film> list;

        if (filmsList.size() < startItem) {
            list = Collections.emptyList();
        }else {
            int toIndex = Math.min(startItem + pageSize, filmsList.size());
            list = filmsList.subList(startItem, toIndex);
        }
        Page<Film> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize) , filmsList.size());
        return page;
    }
    @Override
    public Film buscarFilmPorId(Integer idFilm)  {
        Film film = template.getForObject(url + "/" + idFilm, Film.class);
        return film;

    }
    @Override
    public Page<Film> buscarFilmsPorTitulo(String titulo, Pageable pageable){
        Film [] films = template.getForObject(url + "/titulo/" + titulo, Film[].class);
        assert films != null;
        List<Film> lista = Arrays.asList(films);
        Page<Film> page = new PageImpl<>(lista, pageable, lista.size());
        return page;
    }
    @Override
    public Page<Film> buscarFilmsPorGenero(String genero, Pageable pageable) {
        Film[] films = template.getForObject(url + "/genero/" + genero,
                Film[].class);
        List<Film> lista = Arrays.asList(films);
        Page<Film> page = new PageImpl<>(lista, pageable, lista.size());
        return page;
    }
    @Override
    public Page<Film> buscarFilmsPorIdioma(String idioma, Pageable pageable) {
        Film[] films = template.getForObject(url + "/idioma/" + idioma,
                Film[].class);
        List<Film> lista = Arrays.asList(films);
        Page<Film> page = new PageImpl<>(lista, pageable, lista.size());
        return page;
    }
    @Override
    public Page<Film> buscarFilmsPorReparto(String reparto, Pageable pageable){
        Film [] films = template.getForObject(url + "/reparto/" + reparto,
                Film[].class);
        List<Film> lista = Arrays.asList(films);
        Page<Film> page = new PageImpl<>(lista, pageable, lista.size());
        return page;
    }
    @Override
    public void guardarFilm(Film film) {
        if (film.getIdFilm() != null && film.getIdFilm() > 0) {
            template.put(url, film);
        } else {
            film.setIdFilm(0);
            template.postForObject(url, film, String.class);
        }
    }
    @Override
    public void eliminarFilm(Integer idFilm) {
        template.delete(url + "/" + idFilm);
    }
}




