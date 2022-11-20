package es.uah.FilmsActores.dao;

import es.uah.FilmsActores.model.Actor;
import es.uah.FilmsActores.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActoresDAOImpl implements IActoresDAO {

    @Autowired
    IActoresJPA actoresJPA;

    @Autowired
    IFilmsJPA filmsJPA;

    @Override
    public List<Actor> buscarTodos() {
        return actoresJPA.findAll();
    }

    @Override
    public Actor buscarActorPorId(Integer idActor) {
        Optional<Actor> optional = actoresJPA.findById(idActor);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Actor buscarActorPorNombre(String nombre) {
        Optional<Actor> optional = Optional.ofNullable(actoresJPA.findByNombre(nombre));
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardarActor(Actor actor) {
        actoresJPA.save(actor);
    }

    @Override
    public void eliminarActor(Integer idActor) {
        Optional<Actor> optional = actoresJPA.findById(idActor);
        if (optional.isPresent()) {
            Actor actor = optional.get();
            List<Film> films = actor.getFilms();
            for (Film film: films) {
                films.remove(actor);
            }
        }
        actoresJPA.deleteById(idActor);
    }

    @Override
    public void actualizarActor(Actor actor) {
        actoresJPA.save(actor);
    }

    @Override
    public void inscribirFilm(Integer idActor, Integer idFilm) {
        Optional<Actor> optionalActor = actoresJPA.findById(idActor);
        if (optionalActor.isPresent()) {
            Actor actor = optionalActor.get();
            Optional<Film> optionalFilm = filmsJPA.findById(idFilm);
            if (optionalFilm.isPresent()) {
                actor.addFilm(optionalFilm.get());
                actoresJPA.save(actor);
            }
        }
    }

}

