package com.xcjy.web.controller.upc;

import com.xcjy.upc.util.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tupeng on 2017/7/19.
 */
@Controller
public class UpcValidController {

    @RequestMapping("/upc/login")
    public String valid(){
        return "redirect:/index";
    }

    @RequestMapping("/upc/logout")
    public ModelAndView logout(){
        UserUtil.logout();
        return new ModelAndView("login");
    }

}
