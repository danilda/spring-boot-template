package com.spring.boot.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dach1016 on 17.07.2017.
 *
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
            model.addAttribute("name", name);
        return "test";
    }
}
