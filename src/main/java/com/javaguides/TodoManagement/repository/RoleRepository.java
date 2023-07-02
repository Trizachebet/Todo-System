package com.javaguides.TodoManagement.repository;

import com.javaguides.TodoManagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
