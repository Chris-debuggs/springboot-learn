package com.springbootdemo.springbootlearn.controller;

import com.springbootdemo.springbootlearn.model.Task;
import com.springbootdemo.springbootlearn.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Map<String, String> request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Task task = taskService.createTask(request.get("title"), request.get("description"), username);
        return ResponseEntity.ok(task);
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<?> completeTask(@PathVariable String id) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Task task = taskService.completeTask(id, username);
            return ResponseEntity.ok(task);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
