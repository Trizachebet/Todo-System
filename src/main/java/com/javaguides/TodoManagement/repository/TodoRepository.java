package com.javaguides.TodoManagement.repository;

import com.javaguides.TodoManagement.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoModel,Long> {
}
