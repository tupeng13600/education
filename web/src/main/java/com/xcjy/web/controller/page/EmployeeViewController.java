package com.xcjy.web.controller.page;

import com.xcjy.web.Service.EmployeeService;
import com.xcjy.web.bean.Employee;
import com.xcjy.web.common.XcjyThreadLocal;
import com.xcjy.web.controller.req.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by tupeng on 2017/7/23.
 */
@Controller
@RequestMapping("/view")
public class EmployeeViewController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employee/list")
    public ModelAndView show(@ModelAttribute Page page){
        List<Employee> employeeList = employeeService.list(page);
        return new ModelAndView("user/employee").addObject("employeeList", employeeList).addObject("totalCount", XcjyThreadLocal.getTotalCount());
    }

}
