package com.springbootdemo.springbootlearn.service;

import com.springbootdemo.springbootlearn.model.Task;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskService {
    private final Map<String, Task> tasks = new ConcurrentHashMap<>();

    public Task createTask(String title, String description, String username) {
        String id = UUID.randomUUID().toString();
        Task task = new Task(id, title, description, false, username);
        tasks.put(id, task);
        return task;
    }

    public Task completeTask(String id, String username) {
        Task task = tasks.get(id);
        if (task != null && task.getOwnerUsername().equals(username)) {
            task.setCompleted(true);
            return task;
        }
        throw new IllegalArgumentException("Task not found or you don't have permission");
    }
}
