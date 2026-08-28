package com.springbootdemo.springbootlearn.service;

import com.springbootdemo.springbootlearn.model.Task;
import com.springbootdemo.springbootlearn.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String title, String description, String username) {
        String id = UUID.randomUUID().toString();
        Task task = new Task(id, title, description, false, username);
        return taskRepository.save(task);
    }

    public Task completeTask(String id, String username) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent() && taskOpt.get().getOwnerUsername().equals(username)) {
            Task task = taskOpt.get();
            task.setCompleted(true);
            return taskRepository.save(task);
        }
        throw new IllegalArgumentException("Task not found or you don't have permission");
    }

    public List<Task> getAllTasks(String username) {
        return taskRepository.findByOwnerUsername(username);
    }

    public Task getTask(String id, String username) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent() && taskOpt.get().getOwnerUsername().equals(username)) {
            return taskOpt.get();
        }
        return null;
    }

    public Map<String, Object> getTaskStats(String username) {
        long totalTasks = taskRepository.countByOwnerUsername(username);
        long completedTasks = taskRepository.countByOwnerUsernameAndCompleted(username, true);
        return Map.of("totalTasks", totalTasks, "completedTasks", completedTasks);
    }
}
