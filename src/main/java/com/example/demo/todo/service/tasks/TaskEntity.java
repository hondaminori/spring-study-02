package com.example.demo.todo.service.tasks;

public record TaskEntity (
    long id,
    String summary,
    String description,
    TaskStatus status
)
{}
