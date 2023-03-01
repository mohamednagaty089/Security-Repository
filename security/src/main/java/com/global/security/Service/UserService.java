package com.global.security.Service;

import com.global.security.Entity.AppUser;
import com.global.security.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {
    @Autowired
    private UserRepo userRepo;

    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };
    public List<AppUser> findAll(){
        return userRepo.findAll();
    }
    public AppUser findById(Integer Id){
        return userRepo.findById(Id).get();
    }

    public AppUser insert(AppUser appUser){
        appUser.setPassword(passwordEncoder().encode(appUser.getPassword()));
        return userRepo.save(appUser);
    }
}
