package com.dossancto.todo.dossancto.repository;

import com.dossancto.todo.dossancto.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
