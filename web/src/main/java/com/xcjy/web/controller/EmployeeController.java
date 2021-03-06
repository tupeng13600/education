package com.xcjy.web.controller;

import com.xcjy.web.Service.EmployeeService;
import com.xcjy.web.bean.Employee;
import com.xcjy.web.controller.req.EmployeeCreateBatchReq;
import com.xcjy.web.controller.req.EmployeeCreateReq;
import com.xcjy.web.controller.req.EmployeeUpdateReq;
import com.xcjy.web.controller.req.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tupeng on 2017/7/22.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public void create(@RequestBody EmployeeCreateReq req){
        employeeService.create(req);
    }

    @PostMapping("/batch")
    public void createBatch(@RequestBody EmployeeCreateBatchReq req){
        for(EmployeeCreateReq emp : req.getEmployeeList()) {
            employeeService.create(emp);
        }
    }

    @PutMapping
    public void update(@RequestBody EmployeeUpdateReq req) {
        employeeService.update(req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        employeeService.deleted(id);
    }

    @GetMapping
    public List<Employee> list(@RequestBody Page page){
        return employeeService.list(page);
    }

}
