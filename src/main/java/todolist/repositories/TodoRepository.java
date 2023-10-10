package todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import todolist.entities.Todo;


public interface TodoRepository extends JpaRepository<Todo, Long>{
}
