package de.fbeutel.reactivetodos.todo.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.bson.types.ObjectId;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import de.fbeutel.reactivetodos.todo.common.util.DateUtil;
import de.fbeutel.reactivetodos.todo.model.Todo;
import de.fbeutel.reactivetodos.todo.repository.TodoRepository;

@Slf4j
@Service
public class TodoService {

  private static final Integer AMOUNT_OF_MOCK_TODOS = 10;
  private static final Double CHANCE_OF_COMPLETION = 0.5;
  private static final Integer MAX_DAYS_IN_PAST_DUE_DATE = 1;
  private static final Integer MAX_DAYS_IN_FUTURE_DUE_DATE = 2;
  private static final String MOCK_DATA_FILE = "data/todoNames.json";

  private final TodoRepository todoRepository;
  private final Random randomGenerator = new Random();

  public TodoService(final TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public List<Todo> findAllOrderedByDueDate() {
    return todoRepository.findAllByOrderByDueDate();
  }

  public void createTodo(final Todo todo) {
    todoRepository.save(todo.toBuilder()
      .id(new ObjectId().toString())
      .done(false)
      .build());
  }

  @EventListener(ApplicationReadyEvent.class)
  public void startup() throws IOException {
    todoRepository.deleteAll();

    final List<String> mockData = new ObjectMapper().readValue(
      new FileInputStream(new ClassPathResource(MOCK_DATA_FILE).getFile()), new TypeReference<List<String>>() {
      });

    IntStream.range(0, AMOUNT_OF_MOCK_TODOS)
      .mapToDouble(i -> randomGenerator.nextDouble())
      .forEach(seed -> todoRepository.save(Todo.builder()
        .id(new ObjectId().toString())
        .name(mockData.get((int) Math.ceil(mockData.size() * seed) - 1))
        .done(seed <= CHANCE_OF_COMPLETION)
        .dueDate(randomDate(seed))
        .build()));

    log.info("mock data import done");
  }

  private Date randomDate(final double seed) {
    if (seed <= 0.5) {
      return DateUtil.toDate(LocalDateTime.now().minusMinutes(Math.round(MAX_DAYS_IN_PAST_DUE_DATE * seed * 24 * 60)));
    }
    return DateUtil.toDate(LocalDateTime.now().plusMinutes(Math.round(MAX_DAYS_IN_FUTURE_DUE_DATE * seed * 24 * 60)));
  }
}
