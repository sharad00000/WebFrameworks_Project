package com.example.toystore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.toystore.model.User;
import com.example.toystore.repository.UserRepo;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User create(User obj) {
        return userRepo.save(obj);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User getById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    public User update(int userId, User user) {
        User model = userRepo.findById(userId).orElse(null);
        if (model != null) {
            model.setUserId(user.getUserId());
            model.setUserName(user.getUserName());
            userRepo.save(model);
            return model;
        } else {
            return user;
        }
    }

    public String delete(int userId) {
        userRepo.deleteById(userId);
        return "Deleted Successfully";
    }

    public List<User> getByPaginateSort(int offset, int pageSize, String field) {
        Page<User> pg = userRepo.findAll(PageRequest.of(offset, pageSize, Sort.by(field)));
        return pg.getContent();
    }
}