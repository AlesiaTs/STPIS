package com.example.alesiaproj.controller;

import com.example.alesiaproj.entity.Task;
import com.example.alesiaproj.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{email}")
    public ResponseEntity<List<Task>> getTasksByUserEmail(@PathVariable String email) {
        return ResponseEntity.ok(taskService.getTasksByUserEmail(email));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Task task) {
        taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
}

    @PutMapping
    public ResponseEntity update(@RequestBody Task task) {
        taskService.updateTask(task);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Task> getById(@PathVariable String id) {
        Optional<Task> taskOptional = taskService.getById(id);
        if (taskOptional.isPresent()) {
            return ResponseEntity.ok(taskOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        return ResponseEntity.ok(taskService.deleteById(id));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }
}
