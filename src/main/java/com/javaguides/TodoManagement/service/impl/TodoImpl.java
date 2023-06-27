package com.javaguides.TodoManagement.service.impl;

import com.javaguides.TodoManagement.dto.TodoDto;
import com.javaguides.TodoManagement.exception.ResourceNotFound;
import com.javaguides.TodoManagement.model.TodoModel;
import com.javaguides.TodoManagement.repository.TodoRepository;
import com.javaguides.TodoManagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TodoImpl implements TodoService {
    private ModelMapper modelMapper;

    private TodoRepository todoRepository;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        //convert TodoDto to Todo entity

       // TodoModel todo = new TodoModel();
       // todo.setTitle(todoDto.getTitle());
       // todo.setDescription(todoDto.getDescription());
       // todo.setCompleted(todoDto.isCompleted());
        TodoModel todo=modelMapper.map(todoDto,TodoModel.class);


        // save to database the entity
        TodoModel savedEntity= todoRepository.save(todo);

        //convert Jpa entity to dto
       // TodoDto todoDto1 =new TodoDto();
        //todoDto1.setId(savedEntity.getId());
        //todoDto1.setTitle(savedEntity.getTitle());
        //todoDto1.setDescription(savedEntity.getDescription());
        //todoDto1.setCompleted(savedEntity.isCompleted());
        TodoDto saveddto= modelMapper.map(savedEntity,TodoDto.class);


        return saveddto;
    }

    @Override
    public TodoDto findTodoById(Long id) {
       TodoModel todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("The Todo not found with id " + id));
       TodoDto todoDto = modelMapper.map(todo,TodoDto.class);
        return todoDto;
    }

    @Override
    public List<TodoDto> findAll() {
       List<TodoModel> todos = todoRepository.findAll();
        return todos.stream().map((todo)-> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        TodoModel todos1 =todoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Todo not found with id "+ id));
        todos1.setTitle(todoDto.getTitle());
        todos1.setDescription(todoDto.getDescription());
        todos1.setCompleted(todoDto.isCompleted());

       TodoModel updatedTodo= todoRepository.save(todos1);


        return  modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteById(Long id) {
            TodoModel existingUser=todoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Todo not found with id " + id));
            todoRepository.deleteById(id);

    }

    @Override
    public TodoDto completeDto(Long id) {
        TodoModel todo =todoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("The tod not found with id: "+ id));
        todo.setCompleted(Boolean.TRUE);
        TodoModel updatedTodo = todoRepository.save(todo);

        return  modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public TodoDto inComplete(Long id) {
        TodoModel todo =todoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("The tod not found with id: "+ id));
        todo.setCompleted(Boolean.FALSE);
        TodoModel updatedTodo = todoRepository.save(todo);

        return  modelMapper.map(updatedTodo,TodoDto.class);

    }


}
