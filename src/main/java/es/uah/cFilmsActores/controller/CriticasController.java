package es.uah.cFilmsActores.controller;


import es.uah.cFilmsActores.model.Critica;
import es.uah.cFilmsActores.model.Film;
import es.uah.cFilmsActores.model.User;
import es.uah.cFilmsActores.paginator.PageRender;
import es.uah.cFilmsActores.service.ICriticasService;
import es.uah.cFilmsActores.service.IFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/criticas")
public class CriticasController {

    @Autowired
    ICriticasService criticasService;

    @Autowired
    IFilmsService filmsService;

    @GetMapping("/all")
    public String criticasList(Model model, @RequestParam(name="page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page,6);
        Page<Critica> all = criticasService.findAll(pageable);
        PageRender<Critica> pageRender = new PageRender<Critica>("/criticas/all", all);
        model.addAttribute("title", "Listado de todas las criticas");
        model.addAttribute("criticasList", all);
        model.addAttribute("page", pageRender);
        return "criticas/criticasList";

}

    @GetMapping ("/search")
    public String search (Model model) {
        return "criticas/searchCriticas";
    }

    @GetMapping ("/idcritica/{id}")
    public String findCriticaById (Model model, @PathVariable("id") Integer id) {
        Critica critica = criticasService.findCriticaById(id);
        model.addAttribute("critica", critica);
        return "formCritica";
    }
    /*
        @GetMapping("/film/{idfilm}")
       public String findCriticaByIdFilm(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(value = "idfilm" , required = false) Integer idFilm) {
           Pageable pageable = PageRequest.of(page, 30);
           Page<Critica> all;
           if (idFilm != null) {
               all = criticasService.findAll(pageable);
           } else {
               all = criticasService.findCriticaByIdFilm(idFilm, pageable);
           }
           PageRender<Critica> pageRender = new PageRender<Critica>("/all", all);
           model.addAttribute("title", "Listado de criticas por idFilm");
           model.addAttribute("FilmCriticas", all);
           model.addAttribute("film" , all);
           model.addAttribute("page", pageRender);
           return "criticas/CriticasDetails";
       }

   */

    @GetMapping("/film/{idfilm}")
    public String findCriticasByIdFilm(Model model, @PathVariable("idfilm" ) int idFilm) {
        List<Critica> all = criticasService.findCriticasByIdFilm(idFilm);
        Film film = filmsService.buscarFilmPorId(idFilm);
        float x = 0 ;
        int y = 0;

        for (Critica critica : all) {
          x =  x +  critica.getNota();
          y = y+1 ;
        }
        x = x/y;
        String formattedValue = String.format("%.2f", x);
        model.addAttribute("film", film);
        model.addAttribute("notaMedia", formattedValue);
        model.addAttribute("title", "Criticas ");
        model.addAttribute("FilmCriticas", all);
        return "criticas/CriticasDetails";
    }





    @GetMapping ("/newCritica")
       public String newCritica(Model model) {
        model.addAttribute("title", "nueva critica" );
        Critica critica = new Critica();
        model.addAttribute ("critica", critica);
        return "criticas/formCritica";

}
    @PostMapping("/save")
    public String saveCritica(Model model, Critica critica, RedirectAttributes attributes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        critica.setIdUser(user.getIdUser());
        criticasService.saveCritica(critica);
        model.addAttribute("title", "Nueva critica");
        attributes.addFlashAttribute("msg", "Los datos de la critica fueron guardados!");
        return "redirect:FilmDetails";
    }


    @GetMapping("/edit/{id}")
    public String editCritica(Model model, @PathVariable("id") Integer id) {
        Critica critica = criticasService.findCriticaById(id);
        model.addAttribute("title", "Editar critica");
        model.addAttribute("critica", critica);
        return "criticas/criticasList";
    }

    @GetMapping("/delete/{id}")
    public String deleteCritica(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        criticasService.deleteCritica(id);
        attributes.addFlashAttribute("msg", "Los datos de la critica fueron borrados!");
        return "redirect:/criticas/all";
    }

}