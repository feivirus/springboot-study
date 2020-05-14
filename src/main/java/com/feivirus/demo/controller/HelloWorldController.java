package com.feivirus.demo.controller;

import com.feivirus.common.cache.impl.StockReader;
import com.feivirus.demo.service.EmployeeService;
import com.feivirus.demo.service.HelloWorldService;
import com.feivirus.redpacket.helper.RedisHelper;
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
    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private HelloWorldService helloWorldService;
    @Autowired
    private StockReader stockReader;


    @GetMapping("/helloWorld")
    @ResponseBody
    public String helloWorld() {
        System.out.println("helloWorld controller...");
        return  helloWorldService.helloWorld();
    }

    @GetMapping("/addEmployee")
    @ResponseBody
    public String addEmployee(String name) {
        String result = "helloWorld";

        employeeService.addEmployee(name);
        return result;
    }

    @GetMapping("/addKey")
    @ResponseBody
    public String addKey(String key, String value) {
        String result = redisHelper.set(key, value);
        return result;
    }

    @GetMapping("/getValue")
    @ResponseBody
    public String getValue(String key) {
        String result = redisHelper.get(key);
        return result;
    }

    @GetMapping("/getStockReader")
    @ResponseBody
    public String getStockReader() {
        return stockReader.getStock().toString();
    }
}
