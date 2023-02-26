package es.uah.cFilmsActores.controller;

import es.uah.cFilmsActores.model.Actor;
import es.uah.cFilmsActores.model.Critica;
import es.uah.cFilmsActores.model.Film;
import es.uah.cFilmsActores.model.User;
import es.uah.cFilmsActores.paginator.PageRender;
import es.uah.cFilmsActores.service.IActoresService;
import es.uah.cFilmsActores.service.IFilmsService;
import es.uah.cFilmsActores.service.IUploadFileService;
import es.uah.cFilmsActores.service.IUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;


@Controller
@RequestMapping("/cfilms")
public class FilmsController {

    @Autowired
    IFilmsService filmsService;

    @Autowired
    IUsersService usersService;

    @Autowired
    IActoresService actoresService;
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
    public String nuevo(Model model,Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
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
    public String listadoFilms(Model model , Principal principal, @RequestParam(name = "page", defaultValue = "0") int page) {
            if (principal != null){
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
        Pageable pageable = PageRequest.of(page, 30);
        Page<Film> listado = filmsService.buscarTodos(pageable);
        PageRender<Film> pageRender = new PageRender<Film>("/cfilms/listado", listado);
        model.addAttribute("titulo", "Listado de todos los films");
        model.addAttribute("listadoFilms", listado);
        model.addAttribute("page", pageRender);

        return "home";

    }
    @GetMapping("/all")
    public String AllFilms(Model model,Principal principal, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (principal != null){
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
        Pageable pageable = PageRequest.of(page, 30);
        Page<Film> all = filmsService.buscarTodos(pageable);
        PageRender<Film> pageRender = new PageRender<Film>("/cfilms/all", all);
        model.addAttribute("titulo", "Listado de todos los films");
        model.addAttribute("allFilms", all);
        model.addAttribute("page", pageRender);
        return "films/listFilm";

    }

    @GetMapping("/idfilm/{id}")
    public String buscarFilmPorId(Model model,Principal principal, @PathVariable("id") Integer id) {
        if (principal != null) {
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
        Film film = filmsService.buscarFilmPorId(id);
        model.addAttribute("film", film);
        Critica critica1 = new Critica();
        model.addAttribute ("critica", critica1);
        return "FilmDetails";

    }

    @GetMapping("/idfilm/actor/{id}")
    public String buscarActoPorId(Model model,Principal principal, @PathVariable("id") Integer id) {
        if (principal != null) {
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
        Film film = filmsService.buscarFilmPorId(id);
        model.addAttribute("film", film);
        return "films/ActoresDetails";

    }

    @GetMapping("/idfilm/actorDetail/{id}")
    public String buscarActorPorId(Model model,Principal principal, @PathVariable("id") Integer id) {
        if (principal != null) {
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
        Film film = filmsService.buscarFilmPorId(id);
        model.addAttribute("film", film);
        Actor actor = actoresService.buscarActorPorId(id);
        model.addAttribute("actor", actor);
        return "films/ActorDetails";

    }

        @GetMapping("/titulo")
    public String buscarFilmsPorTitulo(Model model,Principal principal, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("titulo") String titulo) {
            if (principal != null) {
                String email = principal.getName();
                String Username = email.substring(0, email.indexOf("@"));
                model.addAttribute("Username", Username);
            }
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
        return "home";
    }

    @GetMapping("/genero")
    public String buscarFilmsPorGenero(Model model,Principal principal, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("genero") String genero) {
        if (principal != null) {
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
        Pageable pageable = PageRequest.of(page, 5);
        Page<Film> listado = filmsService.buscarFilmsPorGenero(genero, pageable);
        PageRender<Film> pageRender = new PageRender<Film>("/listado", listado);
        model.addAttribute("titulo", "Listado de films por genero");
        model.addAttribute("listadoFilms", listado);
        model.addAttribute("page", pageRender);
        return "home";
    }

    @GetMapping("/idioma")
    public String buscarFilmsPorIdioma(Model model,Principal principal, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("idioma") String idioma) {
        if (principal != null) {
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
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
        return "home";
    }
    @GetMapping("/reparto")
    public String buscarFilmsPorReparto(Model model,Principal principal, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("reparto") String reparto) {
        if (principal != null) {
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
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
        return "home";
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

            verFoto(uniqueFilename);
            film.setImagen(uniqueFilename);
            filmsService.guardarFilm(film);

        }

        model.addAttribute("titulo", "Nuevo film");
        attributes.addFlashAttribute("msg", "Los datos de la pelicula fueron guardados!");
        return "redirect:/cfilms/";
    }

    @GetMapping("/editar/{id}")
    public String editarFilm(Model model,Principal principal, @PathVariable("id") Integer id) {
        if (principal != null) {
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
        Film film = filmsService.buscarFilmPorId(id);
        model.addAttribute("titulo", "Editar film");
        model.addAttribute("film", film);
        return "films/formFilm";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarFilm(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        filmsService.eliminarFilm(id);
        attributes.addFlashAttribute("msg", "Los datos de la pelicula fueron borrados!");
        return "redirect:/cfilms/all";
    }
}


