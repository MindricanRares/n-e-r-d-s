package com.hackathonNerds.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String printHello() {
        return "Hello " + name;
    }
}
