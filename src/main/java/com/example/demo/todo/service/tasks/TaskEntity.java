package com.example.demo.todo.service.tasks;

public record TaskEntity (
    Long id,
    String summary,
    String description,
    TaskStatus status
)
{}
