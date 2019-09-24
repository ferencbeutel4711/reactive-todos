package de.fbeutel.reactivetodos.todo.web;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/todo", produces = TEXT_HTML_VALUE)
public class TodoHtmlController {

  @GetMapping
  public ModelAndView getIndexPage() {
    return new ModelAndView("index", new ConcurrentHashMap<>());
  }
}
