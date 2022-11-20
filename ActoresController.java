package es.uah.FilmsActores.controller;

import es.uah.FilmsActores.model.Actor;
import es.uah.FilmsActores.service.IActoresService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ActoresController {
    @Autowired
    IActoresService actoresService;

    @GetMapping("/actores")
    public List<Actor> buscarTodos() {
        return actoresService.buscarTodos();
    }

    @GetMapping("/actores/{id}")
    public Actor buscarActorPorId(@PathVariable("id") Integer id) {
        return actoresService.buscarActorPorId(id);
    }

    @GetMapping("/actores/nombre/{nombre}")
    public Actor buscarActorPorNombre(@PathVariable("nombre") String nombre) {
        return actoresService.buscarActorPorNombre(nombre);
    }

    @PostMapping("/actores")
    public void guardarActor(@RequestBody Actor actor) {
        actoresService.guardarActor(actor);
    }

    @PutMapping("/actores")
    public void actualizarActor(@RequestBody Actor actor) {
        actoresService.actualizarActor(actor);
    }

    @DeleteMapping("/actores/{id}")
    public void eliminarActor(@PathVariable("id") Integer id) {
        actoresService.eliminarActor(id);
    }

    @GetMapping("/actores/insc/{ida}/{idf}")
    public void inscribirFilm(@PathVariable("ida") Integer ida, @PathVariable("idf") Integer idf) {
        actoresService.inscribirFilm(ida, idf);
    }

}


