package es.uah.FilmsActores.dao;

import es.uah.FilmsActores.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActoresJPA extends JpaRepository<Actor, Integer> {

    Actor findByNombre(String nombre);

}
