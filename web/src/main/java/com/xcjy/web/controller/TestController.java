package com.xcjy.web.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 22670 on 2017/7/19.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    @RequiresRoles({"ceshi"})
    public Map<String, Object> get(){
        return new HashMap<>();
    }

    @GetMapping("/get1")
    @RequiresRoles({"ceshi2"})
    public Map<String, Object> get1(){
        return new HashMap<>();
    }

    @GetMapping("/get2")
    public Map<String, Object> get2(){
        return new HashMap<>();
    }

}
