package de.fbeutel.reactivetodos.todo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.fbeutel.reactivetodos.todo.model.Todo;

public interface TodoRepository extends MongoRepository<Todo, String> {

  List<Todo> findAllByOrderByDueDate();
}
