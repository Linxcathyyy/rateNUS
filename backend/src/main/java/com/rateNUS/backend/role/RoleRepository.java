package com.rateNUS.backend.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rateNUS.backend.security.ApplicationUserRole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ApplicationUserRole name);
}
