package com.global.security.JWT.services;

import com.global.security.JWT.Entity.Role;
import com.global.security.JWT.daos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public Role save(Role role){
        return roleRepo.save(role);
    }
}
