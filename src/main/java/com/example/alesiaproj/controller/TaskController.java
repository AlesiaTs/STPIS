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
        if (task.title != null && task.dateToComplete != null && task.description != null && task.worker != null && task.workHourCount != 0 && task.status != null) {
            taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403 Forbidden message 'can not created' ");//build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Task task) {
        if (task.title != null && task.dateToComplete != null && task.description != null && task.worker != null && task.workHourCount != 0 && task.status != null) {
            taskService.updateTask(task);
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403 Forbidden message 'can not created' ");
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
