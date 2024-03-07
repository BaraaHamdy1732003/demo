package com.example.demo.services;

import com.example.demo.dto.UserDto;
import com.example.demo.repositores.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.demo.dto.UserDto.userList;

@Component
public class UserServiceImp implements UserService{
    @Autowired
    private UsersRepository usersRepository;
   @Override
    public List<UserDto>getAllUsers(){
        return userList(usersRepository.findAll());
    }
}
