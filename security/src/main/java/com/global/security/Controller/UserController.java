package com.global.security.Controller;

import com.global.security.Entity.CustomUser;
import com.global.security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<String> getUserData(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser=(CustomUser) authentication.getPrincipal();//get authenticated user
        String fullname=null;
        if(customUser.getFirstname()!=null||customUser.getFirstname()!=null){
            fullname= customUser.getFirstname()+customUser.getLastname();
        }
        return new ResponseEntity<String>(fullname, HttpStatus.OK);
    }
}
