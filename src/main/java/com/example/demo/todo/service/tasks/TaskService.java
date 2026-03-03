package com.example.demo.todo.service.tasks;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.todo.repository.tasks.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<TaskEntity> find() {
        return taskRepository.select();
    }

    public Optional<TaskEntity> findById(long taskId) {
        return taskRepository.selectById(taskId);
    }
}
