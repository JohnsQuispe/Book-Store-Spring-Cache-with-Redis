package br.com.johnsquispe.springcacheredis.app;

import br.com.johnsquispe.springcacheredis.UserDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    private String openLoginPage(@AuthenticationPrincipal UserDetail userDetail) {

        if (userDetail != null) {
            return "redirect:/app";
        }

        return HtmlTemplate.LOGIN_PAGE;
    }


}
