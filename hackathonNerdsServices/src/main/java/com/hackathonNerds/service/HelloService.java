package com.hackathonNerds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathonNerds.entity.User;
import com.hackathonNerds.repository.UserRepository;

@Service
public class HelloService {
    private String name;

    @Autowired
    private UserRepository userRepository;

    public void setName(String name) {
        this.name = name;
    }

    public String printHello() {
        return "Hello " + name;
    }

    public User getUser(int id) {
        return userRepository.getOne(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
