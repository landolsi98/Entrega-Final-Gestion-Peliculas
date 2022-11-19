package es.uah.cFilmsActores.controller;

import es.uah.cFilmsActores.model.Actor;
import es.uah.cFilmsActores.model.Film;
import es.uah.cFilmsActores.paginator.PageRender;
import es.uah.cFilmsActores.service.IActoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/cactores")
public class ActoresController {
    @Autowired
    IActoresService actoresService;

    @GetMapping("/listado")
    public String listadoActores(Model model, @RequestParam(name="page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page,6);
        Page<Actor> listado = actoresService.buscarTodos(pageable);
        PageRender<Actor> pageRender = new PageRender<Actor>("/cactores/listado", listado);
        model.addAttribute("titulo", "Listado de todos los actores");
        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "films/listActor";
    }
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Nuevo actor");
        Actor actor = new Actor();
        model.addAttribute("actor", actor);
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
    public String editarActor(Model model, @PathVariable("id") Integer id) {
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
    @GetMapping("/cactores/insc/{ida}/{idf}")
    public void inscribirFilm(@PathVariable("ida") Integer ida, @PathVariable("idf") Integer idf) {
        actoresService.inscribirFilm(ida, idf);
    }
}


