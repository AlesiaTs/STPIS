package com.example.alesiaproj.service;

import com.example.alesiaproj.entity.Task;
import com.example.alesiaproj.repository.FirebaseTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final FirebaseTaskRepository taskRepository;

    public List<Task> getTasksByUserEmail(String email) {
        return taskRepository.getByUserEmail(email);
    }

    public void updateTask(Task task) {
        try {
            taskRepository.update(task);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public void createTask(Task task) {
        try {
            taskRepository.create(task);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public Optional<Task> getById(String id) {
        try {
            return taskRepository.getById(id);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }

    }

    public String deleteById(String id) {
        return taskRepository.deleteById(id);
    }

    public List<Task> getAll() {
        try {
            return taskRepository.getAll();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException();
        }

    }

}
