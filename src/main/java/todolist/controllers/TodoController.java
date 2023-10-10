package todolist.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import todolist.entities.Todo;
import todolist.services.TodoService;

@RestController
@RequestMapping(path = "/api/v1/todos")
public class TodoController {

  private TodoService todoService;

  TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping
  public List<Todo> listAll() {
    return todoService.findAll();
  }

  @PostMapping
  public List<Todo> add(@RequestBody @Valid Todo todo) {

    return todoService.add(new Todo(todo.getNome(), todo.getDescricao(), todo.isRealizado(), todo.getPrioridade()));
  }

  @PutMapping
  public List<Todo> update(@RequestBody @Valid Todo todo) {
    return todoService.add(new Todo(todo.getNome(), todo.getDescricao(), todo.isRealizado(), todo.getPrioridade()));
  }

  @DeleteMapping(path = "/{id}")
  public List<Todo> delete(@PathVariable Long id) {
    todoService.delete(id);

    return todoService.findAll();
  }

}
