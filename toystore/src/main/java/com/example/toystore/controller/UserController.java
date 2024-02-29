package com.example.toystore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.toystore.model.User;
import com.example.toystore.service.UserService;

@RestController
public class UserController {
    private final UserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    @GetMapping("/api/User/{offset}/{pagesize}/{field}")
    public ResponseEntity<List<User>> getMethod(@PathVariable("offset") int offset,
            @PathVariable("pagesize") int pageSize, @PathVariable("field") String field) {
        List<User> ch = us.getByPaginateSort(offset, pageSize, field);
        if (ch.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ch, HttpStatus.OK);
    }

    @PostMapping("/api/User/add")
    public ResponseEntity<User> create(@RequestBody User obj) {
        return new ResponseEntity<>(us.create(obj), HttpStatus.CREATED);
    }

    @GetMapping("/api/User/get")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(us.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/User/get/{userId}")
    public ResponseEntity<User> getById(@PathVariable int userId) {
        User obj = us.getById(userId);
        if (obj == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping("/api/User/put/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user) {
        User updatedUser = us.update(userId, user);
        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/api/User/delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable int userId) {
        String result = us.delete(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}