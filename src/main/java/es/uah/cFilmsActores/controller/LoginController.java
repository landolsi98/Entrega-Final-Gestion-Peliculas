package es.uah.cFilmsActores.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class LoginController {

   /* @GetMapping("/")
    public String home(Principal principal) {
        if (principal != null) {
            return "redirect:/cfilms";
        } else {
            return "";
        }
    }
*/
@GetMapping ("/login")
    public String login (@RequestParam (value = "error", required = false) String error , Model model, Principal principal1) {

    if (principal1 != null) {
        UserDetails userDetails = (UserDetails) ((Authentication) principal1).getPrincipal();
        model.addAttribute("username", userDetails.getUsername());


        return "redirect:/cfilms";
    }

    if (error != null) {
        model.addAttribute("error" , "Error al iniciar sesion : Correo o contraseña incorrecta , por favor vuelva a intentarlo!");
    }

    return "login";

}

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

}
