package es.uah.FilmsActores.dao;

import es.uah.FilmsActores.model.Actor;
import java.util.List;

public interface IActoresDAO {

    List<Actor> buscarTodos();

    Actor buscarActorPorId (Integer idActor);

    Actor buscarActorPorNombre(String nombre);

    void guardarActor(Actor actor);

    void eliminarActor(Integer idActor);

    void actualizarActor(Actor actor);

    void inscribirFilm(Integer idActor, Integer idFilm);



}
