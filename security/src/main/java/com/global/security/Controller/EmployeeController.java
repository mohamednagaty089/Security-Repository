package com.global.security.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${allowed.start}/employee")
public class EmployeeController {

      @PreAuthorize("hasRole('ADMIN')")// and spring concatenation Role_Admin
      @GetMapping("/{name}")
      public String getName(@PathVariable String name){
    return "welcome"+name;
}

      @PreAuthorize("hasRole('User')")
      @GetMapping("")
       public String getAllEmployee(){
          return "mohamed nagaty + magdy";
       }

    @PreAuthorize("hasRole('User') or hasRole('Admin')")
    @GetMapping("/firstname")
    public String getFirstName(){
        return "first name mohamed";
    }

}
