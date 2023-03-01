package com.global.security.Config;

import com.global.security.Entity.AppUser;
import com.global.security.Entity.Role;
import com.global.security.Repository.RoleRepo;
import com.global.security.Service.RoleService;
import com.global.security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AppStart implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {

         System.out.println("application run success");
         List<AppUser> users=userService.findAll();
         List<Role> roles=roleService.findAll();

         if(users.isEmpty() || roles.isEmpty()){
             Set<Role> rolesset1=new HashSet<>();
             rolesset1.add(new Role(null,"Admin"));
             Set<Role> rolesset2=new HashSet<>();
             rolesset2.add(new Role(null,"User"));
             Set<Role> rolesset3=new HashSet<>();
             rolesset3.add(new Role(null,"Employee"));
             roleService.inserAll(rolesset1);
             roleService.inserAll(rolesset2);
             roleService.inserAll(rolesset3);


             AppUser user1=new AppUser(1,"nagaty","123","mohamed","nagaty",rolesset1);
             AppUser user2=new AppUser(2,"mostafa","245","mostaf","anagaty",rolesset2);
             AppUser user3=new AppUser(3,"Ali","777","mohamed","ali",rolesset3);
             userService.insert(user1);
             userService.insert(user2);
             userService.insert(user3);


         }
    }
}
