package es.uah.cFilmsActores.controller;


import es.uah.cFilmsActores.model.Actor;
import es.uah.cFilmsActores.model.Critica;
import es.uah.cFilmsActores.model.Film;
import es.uah.cFilmsActores.model.User;
import es.uah.cFilmsActores.paginator.PageRender;
import es.uah.cFilmsActores.service.ICriticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/criticas")
public class CriticasController {

    @Autowired
    ICriticasService criticasService;


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

    @GetMapping("/film/{idfilm}")
    public String findCriticaByIdFilm(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("idFilm") Integer idFilm) {
        Pageable pageable = PageRequest.of(page, 30);
        Page<Critica> all;
        if (idFilm.equals("")) {
            all = criticasService.findAll(pageable);
        } else {
            all = criticasService.findCriticaByIdFilm(idFilm, pageable);
        }
        PageRender<Critica> pageRender = new PageRender<Critica>("/all", all);
        model.addAttribute("title", "Listado de criticas por idFilm");
        model.addAttribute("all criticas", all);
        model.addAttribute("page", pageRender);
        return "criticas/criticasList";
    }




    @GetMapping ("/newCritica")
       public String newCritica(Model model) {
        model.addAttribute("title", "nueva critica" );
        Critica critica = new Critica();
        model.addAttribute ("critica", critica);
        return "criticas/formCritica";

}

    @PostMapping("/save")
    public String saveCritica(Model model, Critica critica, RedirectAttributes attributes) {
        criticasService.saveCritica(critica);
        model.addAttribute("title", "Nueva critica");
        attributes.addFlashAttribute("msg", "Los datos de la critica  fueron guardados!");
        return "redirect:/criticas/all";
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