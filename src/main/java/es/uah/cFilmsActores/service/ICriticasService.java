package es.uah.cFilmsActores.service;

import es.uah.cFilmsActores.model.Critica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICriticasService {


    Page<Critica> findAll(Pageable pageable);

    List<Critica> findCriticasByIdFilm(Integer idFilm);

    Page<Critica> findCriticaByIdFilm(Integer idFilm, Pageable pageable);

    Critica findCriticaById(Integer idCritica);


    void saveCritica(Critica critica);


    void deleteCritica(Integer idCritica);



}
