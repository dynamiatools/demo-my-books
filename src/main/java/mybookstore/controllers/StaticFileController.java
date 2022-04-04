package mybookstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import tools.dynamia.integration.sterotypes.Controller;

@Controller
public class StaticFileController {

    @GetMapping("/front")
    public String file(){
        System.out.println("Loading static /file");
        return "static/file.html";
    }
}
