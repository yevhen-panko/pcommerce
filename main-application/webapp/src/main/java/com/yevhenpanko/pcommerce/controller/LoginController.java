package com.yevhenpanko.pcommerce.controller;

import com.yevhenpanko.pcommerce.model.ResponseValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @ResponseBody
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseValue<Boolean> authenticate(@RequestBody UserDetails userDetails){
        return new ResponseValue<>(userDetails.getPassword() != null);
    }
}
