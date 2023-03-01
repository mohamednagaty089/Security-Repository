package com.global.security.Service;

import com.global.security.Entity.Role;
import com.global.security.Repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public List<Role> findAll(){
        return roleRepo.findAll();
    }

    public Role findById(Integer Id){
        return roleRepo.findById(Id).get();
    }
    public void insert(Role role){
         roleRepo.save(role);
    }
    public void inserAll(Set<Role> role){
        roleRepo.saveAll(role);
    }
}
