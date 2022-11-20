package es.uah.FilmsActores.service;

import es.uah.FilmsActores.dao.IActoresDAO;
import es.uah.FilmsActores.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActoresServiceImpl implements IActoresService{

    @Autowired
    IActoresDAO actoresDAO;

    @Override
    public List<Actor> buscarTodos() {
        return actoresDAO.buscarTodos();
    }

    @Override
    public Actor buscarActorPorId(Integer idActor) {
        return actoresDAO.buscarActorPorId(idActor);
    }

    @Override
    public Actor buscarActorPorNombre(String nombre) {
        return actoresDAO.buscarActorPorNombre(nombre);
    }

    @Override
    public void guardarActor(Actor actor) {
        actoresDAO.guardarActor(actor);
    }

    @Override
    public void eliminarActor(Integer idActor) {
        actoresDAO.eliminarActor(idActor);
    }

    @Override
    public void actualizarActor(Actor actor) {
        actoresDAO.actualizarActor(actor);
    }

    @Override
    public void inscribirFilm(Integer idActor, Integer idFilm) {
        actoresDAO.inscribirFilm(idActor, idFilm);
    }

}
