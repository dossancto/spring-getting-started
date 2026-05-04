package com.dossancto.todo.dossancto.controller;

import com.dossancto.todo.dossancto.dto.TodoRequest;
import com.dossancto.todo.dossancto.models.Todo;
import com.dossancto.todo.dossancto.repository.TodoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo", description = "Todo management API")
public class TodoController {

	private final TodoRepository todoRepository;

	public TodoController(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@GetMapping
	@Operation(summary = "Get all todos")
	public ResponseEntity<List<Todo>> getAllTodos() {
		return ResponseEntity.ok(todoRepository.findAll());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get todo by ID")
	public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
		return todoRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@Operation(summary = "Create new todo")
	public ResponseEntity<Todo> createTodo(@RequestBody TodoRequest request) {
		Todo todo = new Todo();
		todo.setTitle(request.title());
		todo.setDescription(request.description());
		todo.setCompleted(request.completed());
		Todo saved = todoRepository.save(todo);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update todo")
	public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody TodoRequest request) {
		return todoRepository.findById(id).map(todo -> {
			todo.setTitle(request.title());
			todo.setDescription(request.description());
			todo.setCompleted(request.completed());
			return ResponseEntity.ok(todoRepository.save(todo));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete todo")
	public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
		if (todoRepository.existsById(id)) {
			todoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
