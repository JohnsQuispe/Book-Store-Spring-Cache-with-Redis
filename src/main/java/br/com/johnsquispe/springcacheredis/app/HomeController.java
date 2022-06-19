package br.com.johnsquispe.springcacheredis.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/app")
    public String getHomeTemplate() {
        return "listagem";
    }


}
