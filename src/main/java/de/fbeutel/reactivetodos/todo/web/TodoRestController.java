package de.fbeutel.reactivetodos.todo.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fbeutel.reactivetodos.todo.model.Todo;
import de.fbeutel.reactivetodos.todo.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoRestController {

  private final TodoService todoService;

  public TodoRestController(final TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping
  public ResponseEntity<List<Todo>> getAllTodos() {
    return ResponseEntity.ok(todoService.findAllOrderedByDueDate());
  }

  @PostMapping
  public ResponseEntity<Void> createNewTodo(@RequestBody final Todo todoToCreate) {
    todoService.createTodo(todoToCreate);

    return ResponseEntity.ok().build();
  }
}
