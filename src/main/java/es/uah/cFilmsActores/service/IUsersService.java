package es.uah.cFilmsActores.service;


import es.uah.cFilmsActores.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsersService {

    Page<User> findAll(Pageable pageable);

    User findUserById (Integer idUser);

    Page<User> findUserByUsername(String username, Pageable pageable);

    User findUserByEmail (String email);

    User login (String email, String password);


    void saveUser(User user);


    void deleteUser(Integer idUser);


    void deleteCritica(Integer idUser, Integer idCritica);



}
