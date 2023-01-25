package es.uah.cFilmsActores.service;

import es.uah.cFilmsActores.model.Rol;

import java.util.List;

public interface IRolesService {

    List<Rol> findAll();

    Rol findRolById(Integer idRol);

    void saveRol(Rol rol);

    void deleteRol(Integer idRol);

}
