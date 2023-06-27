package com.javaguides.TodoManagement.controller;

import com.javaguides.TodoManagement.dto.TodoDto;
import com.javaguides.TodoManagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/todo")
public class TodoController {
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);


    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> findById(@PathVariable Long id) {
        TodoDto todo = todoService.findTodoById(id);
        return new ResponseEntity<>(todo, HttpStatus.FOUND);

    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> findAll(TodoDto todoDto) {
        List<TodoDto> todos = todoService.findAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        TodoDto todoDto1 = todoService.updateTodo(id, todoDto);
        return ResponseEntity.ok(todoDto1);

    }

    @DeleteMapping("{id}")

    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        todoService.deleteById(id);
        return new ResponseEntity<>("Todo deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> isComplete(@PathVariable Long id) {
        TodoDto isComplete = todoService.completeDto(id);
        return ResponseEntity.ok(isComplete);
    }

    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> inComplete(@PathVariable Long id) {
        TodoDto isComplete = todoService.inComplete(id);
        return ResponseEntity.ok(isComplete);
    }
}