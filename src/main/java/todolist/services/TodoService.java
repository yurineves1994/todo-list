package todolist.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import todolist.entities.Todo;
import todolist.repositories.TodoRepository;

@Service
public class TodoService {
  private TodoRepository repository;

  public TodoService(TodoRepository repository) {
    this.repository = repository;
  }

  public List<Todo> add(Todo todo) {
    repository.save(new Todo(todo.getNome(), todo.getDescricao(), todo.isRealizado(), todo.getPrioridade()));

    return this.findAll();
  }

  public List<Todo> findAll() {
    Sort sort = Sort.by("prioridade").descending().and(
        Sort.by("nome").ascending());

    return repository.findAll(sort);
  }

  public List<Todo> update(Todo todo) {

    repository.save(new Todo(todo.getNome(), todo.getDescricao(), todo.isRealizado(), todo.getPrioridade()));

    return this.findAll();
  }

  public List<Todo> delete(Long id) {
    repository.deleteById(id);

    return this.findAll();
  }

}
