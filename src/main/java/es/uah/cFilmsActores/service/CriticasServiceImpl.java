package es.uah.cFilmsActores.service;

import es.uah.cFilmsActores.model.Critica;
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
public class CriticasServiceImpl implements ICriticasService {

    @Autowired
    RestTemplate template;


    String url = "http://localhost:8090/api/films/films";



    @Override
    public Page<Critica> findAll(Pageable pageable) {

        Critica [] criticas = template.getForObject(url, Critica[].class);
        assert criticas != null;
        List<Critica> criticasList = Arrays.asList(criticas);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Critica> list;
        if(criticasList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, criticasList.size());
            list = criticasList.subList(startItem, toIndex);
        }
        Page<Critica> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), criticasList.size());
        return page;
    }



    @Override
    public Page<Critica> findCriticaByIdFilm (Integer idFilm, Pageable pageable) {
        Critica[] criticas = template.getForObject(url + "/idFilm/" + idFilm,
                Critica[].class);
        List<Critica> lista = Arrays.asList(criticas);
        Page<Critica> page = new PageImpl<>(lista, pageable, lista.size());
        return page;
    }




    @Override
    public Critica findCriticaById(Integer idCritica) {
        Critica critica = template.getForObject(url + "/" +idCritica, Critica.class );
        return critica;
    }

    @Override
    public void saveCritica(Critica critica) {
        if (critica.getIdCritica() != null && critica.getIdCritica() > 0 ) {
            template.put(url, critica);
        }else {
            critica.setIdCritica(0);
            template.postForObject(url, critica, String.class);
        }

    }

    @Override
    public void deleteCritica(Integer idCritica) {
        template.delete(url + "/" + idCritica);

    }
}
