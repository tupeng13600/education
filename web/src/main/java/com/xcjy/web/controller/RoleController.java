package com.xcjy.web.controller;

import com.xcjy.web.Service.RoleService;
import com.xcjy.web.controller.req.RoleCreateReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 22670 on 2017/7/22.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public void create(@RequestBody RoleCreateReq req) {
        roleService.create(req);
    }

}
