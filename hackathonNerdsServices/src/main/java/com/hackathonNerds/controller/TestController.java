package com.hackathonNerds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hackathonNerds.service.HelloService;

@RestController
@RequestMapping(value = "/testCntrl")
public class TestController extends BaseController {

    @Autowired
    HelloService hs;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        hs.setName("abc");
        return hs.printHello();

    }
}