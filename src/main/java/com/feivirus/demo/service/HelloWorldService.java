package com.feivirus.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author feivirus
 */
@Service
public class HelloWorldService {
    public String helloWorld() {
        String result = "in helloWorld service";
        System.out.println(result);
        return result;
    }
}
