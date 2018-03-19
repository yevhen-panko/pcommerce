package com.yevhenpanko.pcommerce.controller;

import com.yevhenpanko.pcommerce.dto.UserShortDTO;
import com.yevhenpanko.pcommerce.model.ResponseValue;
import com.yevhenpanko.pcommerce.service.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/api/user")
public class UserRolesController {
    private final UserManagement userManagement;

    @Autowired
    public UserRolesController(UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseValue<UserShortDTO> findOne(@PathVariable long id) {
        final Optional<UserShortDTO> userShortDTO = userManagement.readById(id);

        return userShortDTO.map(ResponseValue::new).orElseGet(() -> new ResponseValue<>(null));
    }
}
