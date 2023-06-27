package com.javaguides.TodoManagement.service;

import com.javaguides.TodoManagement.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);
    TodoDto findTodoById(Long id);
    List<TodoDto> findAll();
    TodoDto updateTodo(Long id, TodoDto todoDto);
    void deleteById(Long id);
    TodoDto completeDto(Long id);
    TodoDto inComplete(Long id);


}
