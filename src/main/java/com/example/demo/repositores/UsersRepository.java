package com.example.demo.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import  com.example.demo.models.User;

public interface UsersRepository extends JpaRepository<User,Long> {

}
