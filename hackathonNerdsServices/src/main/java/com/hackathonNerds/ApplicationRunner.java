package com.hackathonNerds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.hackathonNerds.repository.HospitalRepository;

@Component
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private HospitalRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
       userRepository.count();
    }

}
