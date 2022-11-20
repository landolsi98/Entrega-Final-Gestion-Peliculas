package es.uah.FilmsActores.controller;

import es.uah.FilmsActores.model.Film;
import es.uah.FilmsActores.service.IFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class FilmsController {

    @Autowired
    IFilmsService filmsService;

    @GetMapping("/films")
    public List<Film> buscarTodos() {
        return filmsService.buscarTodos();
    }

    @GetMapping("/films/{id}")
    public Film buscarFilmPorId(@PathVariable("id") Integer id) {
        return filmsService.buscarFilmPorId(id);
    }

    @GetMapping("/films/titulo/{titulo}")
    public List<Film> buscarFilmsPorTitulo(@PathVariable("titulo") String titulo) {
        return filmsService.buscarFilmsPorTitulo(titulo);
    }

    @GetMapping("/films/genero/{genero}")
    public List<Film> buscarFilmsPorGenero(@PathVariable("genero") String genero) {
        return filmsService.buscarFilmsPorGenero(genero);
    }

    @GetMapping("/films/idioma/{idioma}")
    public List<Film> buscarFilmsPorIdioma(@PathVariable("idioma") String idioma) {
        return filmsService.buscarFilmsPorIdioma(idioma);
    }
    @GetMapping("/films/reparto/{reparto}")
    public List<Film> buscarFilmsPorReparto(@PathVariable("reparto") String reparto) {
        return filmsService.buscarFilmsPorReparto(reparto);
    }

    @PostMapping("/films")
    public void guardarFilm(@RequestBody Film film) {
        filmsService.guardarFilm(film);
    }

    @PutMapping("/films")
    public void actualizarFilm(@RequestBody Film film) {
        filmsService.actualizarFilm(film);
    }

    @DeleteMapping("/films/{id}")
    public void eliminarFilm(@PathVariable("id") Integer id) {
        filmsService.eliminarFilm(id);
    }

}


