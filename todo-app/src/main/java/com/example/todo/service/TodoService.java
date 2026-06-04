package com.example.todo.service;

import com.example.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {

    // In-memory list — no database needed
    private final List<Todo> todos = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo addTodo(String title) {
        Todo todo = new Todo(idCounter.getAndIncrement(), title, false);
        todos.add(todo);
        return todo;
    }

    public Todo toggleTodo(Long id) {
        for (Todo todo : todos) {
            if (todo.getId().equals(id)) {
                todo.setCompleted(!todo.isCompleted());
                return todo;
            }
        }
        return null;
    }

    public boolean deleteTodo(Long id) {
        return todos.removeIf(todo -> todo.getId().equals(id));
    }
}
