package com.ss.todo.controller;

import com.ss.todo.domain.Todo;
import com.ss.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TodoController {
    private final TodoRepository repository;

    @Autowired
    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/todos", method = { RequestMethod.GET },
    produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.TEXT_XML_VALUE
    })
    public ResponseEntity<Iterable<Todo>> getList(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<Iterable<Todo>>(repository.findAll(), headers, HttpStatus.OK);
    }
}
