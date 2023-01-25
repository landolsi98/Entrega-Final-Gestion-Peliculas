package es.uah.cFilmsActores.service;

import es.uah.cFilmsActores.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RolesServiceImpl implements IRolesService{

    @Autowired
    RestTemplate template;


    String url = "http://localhost:8090/api/criticas/roles";

    @Override
    public List<Rol> findAll() {
        Rol[] roles = template.getForObject(url, Rol[].class);
        return Arrays.asList(roles);
    }

    @Override
    public Rol findRolById(Integer idRol) {

        Rol rol = template.getForObject(url + "/"+idRol, Rol.class);
        return rol;
    }

    @Override
    public void saveRol(Rol rol) {
        if(rol.getIdRol() != null && rol.getIdRol()> 0) {
            template.put(url , rol);
        } else {
            rol.setIdRol(0);
            template.postForObject(url, rol, String.class);
        }

    }

    @Override
    public void deleteRol(Integer idRol) {
        template.delete(url + "/" + idRol);

    }
}
