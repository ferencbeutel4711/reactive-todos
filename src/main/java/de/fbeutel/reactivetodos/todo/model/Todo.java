package de.fbeutel.reactivetodos.todo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Todo {

  @Id
  private final String id;

  private final String name;
  private final Date dueDate;
  private final Boolean done;
}
