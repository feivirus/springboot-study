package com.feivirus.demo.controller;

import com.feivirus.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author feivirus
 */
@Controller
public class HelloWorldController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/helloWorld")
    @ResponseBody
    public String helloWorld() {
        String result = "helloWorld";
        System.out.println(result);
        return result;
    }

    @GetMapping("/addEmployee")
    @ResponseBody
    public String addEmployee(String name) {
        String result = "helloWorld";

        employeeService.addEmployee(name);
        return result;
    }
}
