package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class UserService {

    static List<User> users = new ArrayList<User>();
    static long id = 0;

    public List<User> findAll() {
        return users;
    }

    public List<User> findByName(String name) {
        return users.stream().filter(user -> user.getName().contains(name)).toList();
    }

    public User findById(long id) {
        return users.stream().filter(user -> id == user.getId()).findAny().orElse(null);
    }

    public User save(User user) {
        // update user
        if (user.getId() != 0) {
            long _id = user.getId();

            for (int i = 0; i < users.size(); i++) {
                if (_id == users.get(i).getId()) {
                    users.set(i, user);
                    break;
                }
            }

            return user;
        }

        // add user
        user.setId(++id);
        users.add(user);
        return user;
    }

    public void deleteById(long id) {
        users.removeIf(user -> id == user.getId());
    }

}
