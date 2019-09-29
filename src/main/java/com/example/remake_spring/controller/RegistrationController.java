package com.example.remake_spring.controller;

import com.example.remake_spring.domain.UserEntity;
import com.example.remake_spring.domain.UserRole;
import com.example.remake_spring.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
       return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UserEntity user, Map<String, Object> model){
        UserEntity userFromDB = userRepo.findByUsername(user.getUsername());

        if(userFromDB !=null){
            model.put("message","User exists!");
            return "registration";
        }

        user.setRoles(Collections.singleton(UserRole.USER));
        user.setActive(true);
        userRepo.save(user);


        return "redirect:/login";
    }
}
