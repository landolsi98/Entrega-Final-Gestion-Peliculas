package es.uah.cFilmsActores.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.uah.cFilmsActores.model.Actor;

public interface IActoresService {
    Page<Actor> buscarTodos(Pageable pageable);

    Actor buscarActorPorId(Integer idActor);

    Actor buscarActorPorNombre(String nombre);

    void guardarActor(Actor actor);

    void eliminarActor(Integer idActor);

    void inscribirFilm(Integer idActor, Integer idFilm);
}
