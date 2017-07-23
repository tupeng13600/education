package com.xcjy.web.controller.upc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tupeng on 2017/7/19.
 */
@Controller
public class UpcValidController {

    @GetMapping("/upc/login")
    public ModelAndView valid(){
        return new ModelAndView("index");
    }

}
