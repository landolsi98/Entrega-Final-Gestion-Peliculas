package es.uah.cFilmsActores.controller;


import es.uah.cFilmsActores.model.Film;
import es.uah.cFilmsActores.model.Rol;
import es.uah.cFilmsActores.model.User;
import es.uah.cFilmsActores.paginator.PageRender;
import es.uah.cFilmsActores.service.IRolesService;
import es.uah.cFilmsActores.service.IUsersService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    IUsersService usersService;


    @Autowired
    IRolesService rolesService;


@GetMapping (value = "/all")
public String usersList (Model model, Principal principal, @RequestParam(name = "page", defaultValue = "0") int page)   {
    if (principal != null) {
        String email = principal.getName();
        String Username = email.substring(0, email.indexOf("@"));
        model.addAttribute("Username", Username);
    }
    Pageable pageable = PageRequest.of(page, 6);
    Page<User> all = usersService.findAll(pageable);
    PageRender<User> pageRender = new PageRender<User>("/users/all", all);
    model.addAttribute ( "title", "listado de todos los usuarios");
    model.addAttribute("usersList", all);
    model.addAttribute("page", pageRender);
    return "users/usersList";
}

@GetMapping("/newUser")
    public String newUser (Model model, User user,Principal princial ) {
    if (princial != null) {
        String email = princial.getName();
        String Username = email.substring(0, email.indexOf("@"));
        model.addAttribute("Username", Username);
    }
    List<Rol> roles = rolesService.findAll();
    if (usersService.findUserByEmail(user.getEmail())!=null) {
        model.addAttribute("msga", "Error al guardar, ya existe el usuartio!");
        return "redirect:/login";
    } else {


    model.addAttribute("title", "nuevo usuario");
    model.addAttribute("allRoles", roles);
    User usuario = new User();
    model.addAttribute("user", usuario);
    return "users/formUsers";
} }


    @GetMapping ("/search")
    public String search (Model model) {
    return "users/searchUser";
}

            @GetMapping ("/iduser/{id}")
    public String findUserById (Model model, @PathVariable("id") Integer id) {
    User user = usersService.findUserById(id);
    model.addAttribute("user", user);
    return "users/formUsers";
            }

    @GetMapping("/username")
    public String findUserByUsername(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("username") String username) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<User> listado;
        if (username.equals("")) {
            listado = usersService.findAll(pageable);
        } else {
            listado = usersService.findUserByUsername(username, pageable);
        }
        PageRender<User> pageRender = new PageRender<User>("/all", listado);
        model.addAttribute("titulo", "Listado de usuarios por username ");
        model.addAttribute("listadoUsuarios", listado);
        model.addAttribute("page", pageRender);
        return "home";
    }

    @GetMapping ("/email")
    public String findUserByEmail (Model model, @RequestParam("email") String email) {
        User user = usersService.findUserByEmail(email);
        model.addAttribute("email", "listado de usuarios por email");
        model.addAttribute("user", user);
        return "users/usersList";

    }

    @PostMapping("/save")
    public String saveUser(Model model, User user,   RedirectAttributes attributes)  {
    List<Rol> roles = rolesService.findAll();
    model.addAttribute("allRoles" , roles);
        if (usersService.findUserByEmail(user.getEmail())!=null) {
            attributes.addFlashAttribute("msga", "Error al guardar, ya existe el correo!");
            return "redirect:/users/formUsers";
        }
    usersService.saveUser(user);
    model.addAttribute("title", "nuevo usuario");
    attributes.addFlashAttribute("msg", "los datos del usuario fueron guardados !");
    return "redirect:/users/all";
    }

    @GetMapping("/registrarme")
    public String nuevoRegistro(Model model) {
        model.addAttribute("title", "Nuevo registro");
        User user = new User();
        model.addAttribute("user", user);
        return "/registro";
    }
    @PostMapping("/registrar")
    public String registro(Model model, User user, RedirectAttributes attributes) {
        if (usersService.findUserByEmail(user.getEmail())!=null) {
            attributes.addFlashAttribute("msga", "Error al guardar, ya existe el correo!");
            return "redirect:/login";
        }
        user.setEnable(true);
        Rol rol = rolesService.findRolById(1);
        user.setRol(rol);
        usersService.saveUser(user);
        attributes.addFlashAttribute("msgRegistro", "Los datos del registro fueron guardados!");
        return "redirect:/login";
    }



    @GetMapping("edit/{id}")
    public String editUser(Model model, Principal principal, @PathVariable("id") Integer id) {
        if (principal != null) {
            String email = principal.getName();
            String Username = email.substring(0, email.indexOf("@"));
            model.addAttribute("Username", Username);
        }
    User user = usersService.findUserById(id);
    model.addAttribute("title", "Editar Usuario");
    model.addAttribute("user", user);
    List<Rol> roles = rolesService.findAll();
    model.addAttribute("allRoles", roles);
    return "users/formUsers";
    }

    @GetMapping ("/delete/{id}")
    public String deleteUser(Model model, @PathVariable("id")Integer id, RedirectAttributes attributes){
/*
    User user = usersService.findUserById(id);
    if ( user != null) {*/
    usersService.deleteUser(id);
    attributes.addFlashAttribute("msg", "los datos del usuario fueron borrados!");

    return "redirect:/users/all";
    }





}
