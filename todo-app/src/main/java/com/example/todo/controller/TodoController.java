package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // GET /api/todos → return all todos
    @GetMapping
    public List<Todo> getAll() {
        return todoService.getAllTodos();
    }

    // POST /api/todos → add a new todo
    @PostMapping
    public Todo create(@RequestBody Map<String, String> body) {
        String title = body.get("title");
        return todoService.addTodo(title);
    }

    // PATCH /api/todos/{id}/toggle → mark complete/incomplete
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Todo> toggle(@PathVariable Long id) {
        Todo updated = todoService.toggleTodo(id);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/todos/{id} → remove a todo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = todoService.deleteTodo(id);
        if (!removed) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
