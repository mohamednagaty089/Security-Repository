package com.global.security.JWT.daos;

import com.global.security.JWT.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
}
