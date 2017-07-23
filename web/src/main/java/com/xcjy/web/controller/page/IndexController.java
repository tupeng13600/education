package com.xcjy.web.controller.page;

import com.xcjy.upc.util.UserUtil;
import com.xcjy.web.Service.UserService;
import com.xcjy.web.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tupeng on 2017/7/23.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        String userName = UserUtil.getCurrentUserName();
        User user = userService.getByUsername(userName);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}