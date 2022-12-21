package es.uah.cFilmsActores.service;

import es.uah.cFilmsActores.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ActoresServiceImpl implements IActoresService {

    @Autowired
    RestTemplate template;

    String url = "http://localhost:8001/actores";

    @Override
    public  Page<Actor> buscarTodos(Pageable pageable) {
        Actor[] actores = template.getForObject(url, Actor[].class);
        assert actores != null;
        List<Actor> actoresList = Arrays.asList(actores);
    int pageSize = pageable.getPageSize();
    int currentPage = pageable.getPageNumber();
    int startItem = currentPage * pageSize;
    List<Actor> list;
        if(actoresList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize,  actoresList.size());
            list =  actoresList.subList(startItem, toIndex);
        }
        Page<Actor> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), actoresList.size());
        return page;
    }
    @Override
    public Actor buscarActorPorId(Integer idActor) {
        Actor actor = template.getForObject(url+"/"+idActor, Actor.class);
        return actor;
    }
    @Override
    public Actor buscarActorPorNombre(String nombre) {
        Actor actor = template.getForObject(url+"/nombre/"+nombre, Actor.class);
        return actor;
    }
    @Override
    public void guardarActor(Actor actor) {
        if (actor.getIdActor() != null && actor.getIdActor() > 0) {
            template.put(url, actor);
        } else {
            actor.setIdActor(0);
            template.postForObject(url, actor, String.class);
        }
    }
        @Override
        public void eliminarActor(Integer idActor) {
            template.delete(url + "/" + idActor);
        }

        @Override
        public void inscribirFilm(Integer idActor, Integer idFilm) {
            template.getForObject(url+"/insc/"+ idActor+ "/"+idFilm, String.class);
        }
    }



