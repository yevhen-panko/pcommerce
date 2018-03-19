package com.yevhenpanko.pcommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class HelloWorldController {

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String sayHelloWorld() {
        return "Hello";
    }
}
