package com.global.security.JWT.config;

import com.global.security.JWT.Entity.Role;
import com.global.security.JWT.Entity.Users;
import com.global.security.JWT.enums.RoleNameEnum;
import com.global.security.JWT.services.MyUserDetailsService;
import com.global.security.JWT.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class AppStart implements CommandLineRunner {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        if(userDetailsService.count()==100){
//            Users users=new Users();
//
//            users.setUserName("mostafa");
//            users.setPassword("123");
//            users.setFirstName("mohamed");
//            users.setLastName("nagaty");
//            ArrayList<Users> userRole=new ArrayList<>();
//            userRole.add(users);
//            users.setEnabled(1);
            Role role=new Role();

            role.setRoleName(RoleNameEnum.ROLE_ADMIN);
//            role.setUsersList(userRole);
            Role role1=new Role();

            role1.setRoleName(RoleNameEnum.ROLE_EMPLOYEE);
            Role role2=new Role();

            role2.setRoleName(RoleNameEnum.ROLE_USER);
//
//            ArrayList<Role> roles=new ArrayList<>();
//            roles.add(role);
//            roles.add(role2);
//            roles.add(role1);
         //   userDetailsService.save(users);
            roleService.save(role1);
            roleService.save(role);
//            roleService.save(role2);



        }

    }
}
