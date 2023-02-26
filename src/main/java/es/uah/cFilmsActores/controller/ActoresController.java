package es.uah.cFilmsActores.controller;

import es.uah.cFilmsActores.model.Actor;
import es.uah.cFilmsActores.model.Film;
import es.uah.cFilmsActores.paginator.PageRender;
import es.uah.cFilmsActores.service.IActoresService;
import es.uah.cFilmsActores.service.IFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
@RequestMapping("/cactores")
public class ActoresController {
    @Autowired
    IActoresService actoresService;

    @Autowired
    IFilmsService filmsService;

    @GetMapping("/listado")
    public String listadoActores(Model model, Principal principal, @RequestParam(name="page", defaultValue = "0") int page) {
        if (principal != null) {
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
        Pageable pageable = PageRequest.of(page,6);
        Page<Actor> listado = actoresService.buscarTodos(pageable);
        PageRender<Actor> pageRender = new PageRender<Actor>("/cactores/listado", listado);
        model.addAttribute("titulo", "Listado de todos los actores");
        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "films/listActor";
    }
    @GetMapping("/nuevo")
    public String nuevo(Model model,Principal principal, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (principal != null) {
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
        model.addAttribute("titulo", "Nuevo actor");
        Pageable pageable = PageRequest.of(page, 30);
        Page<Film> listado = filmsService.buscarTodos(pageable);
        Actor actor = new Actor();
        model.addAttribute("actor", actor);
        model.addAttribute("listFilms", listado);
        return "films/formActor";
    }

    @PostMapping("/guardar/")
    public String guardarActor(Model model, Actor actor, RedirectAttributes attributes) {
        actoresService.guardarActor(actor);
        model.addAttribute("titulo", "Nuevo actor");
        attributes.addFlashAttribute("msg", "Los datos del actor fueron guardados!");
        return "redirect:/cactores/listado";
    }
    @GetMapping("/editar/{id}")
    public String editarActor(Model model,Principal principal, @PathVariable("id") Integer id) {
        if (principal != null) {
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
        Actor actor = actoresService.buscarActorPorId(id);
        model.addAttribute("titulo", "Editar actor");
        model.addAttribute("actor", actor);
        return "films/formActor";
    }
    @GetMapping("/borrar/{id}")
    public String eliminarActor(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        actoresService.eliminarActor(id);
        attributes.addFlashAttribute("msg", "Los datos del actor fueron borrados!");
        return "redirect:/cactores/listado";
    }
    @GetMapping("/insc/{ida}/{idf}")
    public String inscribirFilm(@PathVariable("ida") Integer ida,
                              @PathVariable("idf") Integer idf) {
        actoresService.inscribirFilm(ida, idf);
        return "films/formActor";
    }
}

