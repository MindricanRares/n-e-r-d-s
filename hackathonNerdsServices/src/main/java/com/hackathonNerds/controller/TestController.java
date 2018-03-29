package com.hackathonNerds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hackathonNerds.entity.User;
import com.hackathonNerds.service.HelloService;

@RestController
@RequestMapping(value = "/testCntrl")
public class TestController extends BaseController {

    @Autowired
    HelloService hs;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        User u = new User();
        u.setName("testUser");
        u.setId(1);
        hs.addUser(u);
        hs.setName(hs.getUser(1).getName());
        return hs.printHello();

    }
}