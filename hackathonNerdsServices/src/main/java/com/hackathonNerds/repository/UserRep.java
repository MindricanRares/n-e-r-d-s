package com.hackathonNerds.repository;

import java.util.List;

import com.hackathonNerds.entity.User;

public interface UserRep {
    List<User> getUsers(String firstParameter);
}
