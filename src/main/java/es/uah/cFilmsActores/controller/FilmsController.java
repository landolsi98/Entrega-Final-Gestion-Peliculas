package es.uah.cFilmsActores.controller;

import es.uah.cFilmsActores.model.Film;
import es.uah.cFilmsActores.paginator.PageRender;
import es.uah.cFilmsActores.service.IFilmsService;
import es.uah.cFilmsActores.service.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;


@Controller
@RequestMapping("/cfilms")
public class FilmsController {

    @Autowired
    IFilmsService filmsService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }


   // @GetMapping(value = {"/", "/homepage", ""})
   // public String home(Model model) {
     //   return "home";
  //  }


    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Nuevo film");
        Film film = new Film();
        model.addAttribute("film", film);
        return "films/formFilm";
    }

    @GetMapping("/buscar")
    public String buscar(Model model) {
        return "films/searchFilm";
    }

    @GetMapping(value = {"/", "/home", ""})
    public String listadoFilms(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 30);
        Page<Film> listado = filmsService.buscarTodos(pageable);
        PageRender<Film> pageRender = new PageRender<Film>("/cfilms/listado", listado);
        model.addAttribute("titulo", "Listado de todos los films");
        model.addAttribute("listadoFilms", listado);
        model.addAttribute("page", pageRender);
        return "home";

    }

    @GetMapping("/idfilm/{id}")
    public String buscarFilmPorId(Model model, @PathVariable("id") Integer id) {
        Film film = filmsService.buscarFilmPorId(id);
        model.addAttribute("film", film);
        return "FilmDetails";

    }

    @GetMapping("/titulo")
    public String buscarFilmsPorTitulo(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("titulo") String titulo) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Film> listado;
        if (titulo.equals("")) {
            listado = filmsService.buscarTodos(pageable);
        } else {
            listado = filmsService.buscarFilmsPorTitulo(titulo, pageable);
        }
        PageRender<Film> pageRender = new PageRender<Film>("/listado", listado);
        model.addAttribute("titulo", "Listado de films por titulo");
        model.addAttribute("listadoFilms", listado);
        model.addAttribute("page", pageRender);
        return "films/listFilm";
    }

    @GetMapping("/genero")
    public String buscarFilmsPorGenero(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("genero") String genero) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Film> listado = filmsService.buscarFilmsPorGenero(genero, pageable);
        PageRender<Film> pageRender = new PageRender<Film>("/listado", listado);
        model.addAttribute("titulo", "Listado de films por genero");
        model.addAttribute("listadoFilms", listado);
        model.addAttribute("page", pageRender);
        return "films/listFilm";
    }

    @GetMapping("/idioma")
    public String buscarFilmsPorIdioma(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("idioma") String idioma) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Film> listado;
        if (idioma.equals("")) {
            listado = filmsService.buscarTodos(pageable);
        } else {
            listado = filmsService.buscarFilmsPorIdioma(idioma, pageable);
        }
        PageRender<Film> pageRender = new PageRender<Film>("/listado", listado);
        model.addAttribute("titulo", "Listado de films por idioma");
        model.addAttribute("listadoFilms", listado);
        model.addAttribute("page", pageRender);
        return "films/listFilm";
    }
    @GetMapping("/reparto")
    public String buscarFilmsPorReparto(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("reparto") String reparto) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Film> listado;
        if (reparto.equals("")) {
            listado = filmsService.buscarTodos(pageable);
        } else {
            listado = filmsService.buscarFilmsPorReparto(reparto, pageable);
        }
        PageRender<Film> pageRender = new PageRender<Film>("/listado", listado);
        model.addAttribute("titulo", "Listado de films por actor");
        model.addAttribute("listadoFilms", listado);
        model.addAttribute("page", pageRender);
        return "films/listFilm";
    }


    @PostMapping("/guardar/")
    public String guardarFilm(Model model, Film film,  @RequestParam("file") MultipartFile foto ,RedirectAttributes attributes) {

        if (!foto.isEmpty()) {

            if (film.getImagen() != null
                    && film.getImagen().length() > 0) {

                uploadFileService.delete(film.getImagen());
            }

            String uniqueFilename = null;
            log.info("test1");

            try {
                log.info("test2");

                uniqueFilename = uploadFileService.copy(foto);
                log.info("test3");

            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("test4");
            attributes.addFlashAttribute("msg", "Has subido correctamente '" + uniqueFilename + "'");

            film.setImagen(uniqueFilename);
        }

        filmsService.guardarFilm(film);
        model.addAttribute("titulo", "Nuevo film");
        attributes.addFlashAttribute("msg", "Los datos de la pelicula fueron guardados!");
        return "redirect:/cfilms/";
    }

    @GetMapping("/editar/{id}")
    public String editarFilm(Model model, @PathVariable("id") Integer id) {
        Film film = filmsService.buscarFilmPorId(id);
        model.addAttribute("titulo", "Editar film");
        model.addAttribute("film", film);
        return "films/formFilm";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarFilm(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        filmsService.eliminarFilm(id);
        attributes.addFlashAttribute("msg", "Los datos de la pelicula fueron borrados!");
        return "redirect:/cfilms/listado";
    }
}


