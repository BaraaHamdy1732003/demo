package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import  com.example.demo.dto.UserForm;
import  com.example.demo.models.User;
import  com.example.demo.repositores.UsersRepository;

@Component
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public void addUser(UserForm form) {
        User user = User.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .build();
        System.out.println(form.getEmail());
    usersRepository.save(user);
    }
}
