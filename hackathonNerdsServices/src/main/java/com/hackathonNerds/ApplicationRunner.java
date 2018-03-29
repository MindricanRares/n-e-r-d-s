package com.hackathonNerds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hackathonNerds.entity.User;
import com.hackathonNerds.repository.UserRepository;

@Component
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u = new User();
        u.setName("testUser");
        u.setId(1);

        userRepository.save(u);

        User u2 = new User();
        u2.setName("testUser2");
        u2.setId(2);

        userRepository.save(u2);
    }

}
