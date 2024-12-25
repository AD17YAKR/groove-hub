package com.groovehub.groovehub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloWorldController {

    @GetMapping({"/test/","/test"})
    public List<Map<String, String>> sayHello() {
        List<Map<String, String>> res = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Hello", "World");
        res.add(map);
        return res;
    }

}
