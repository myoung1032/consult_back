package sustech.learn.consult.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api
@RestController
@RequestMapping
public class HelloController {



    @GetMapping("/")
    public String index(){
        return "v2.9.8 of learningc backend \n ";
    }
}
