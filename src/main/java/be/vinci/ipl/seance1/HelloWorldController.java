package be.vinci.ipl.seance1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {


    @GetMapping("/hello/{who}")
    public String hello(@PathVariable final String who) {
        return String.format("Coucou %s", who);
    }
}
