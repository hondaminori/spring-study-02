package com.example.demo.todo.controller.tasks;

import java.util.List;
import java.util.Optional;

import com.example.demo.todo.service.tasks.TaskSearchEntity;
import com.example.demo.todo.service.tasks.TaskStatus;

public record TaskSearchForm(
        String summary,
        List<String> status) {
    public TaskSearchEntity toEntity() {
        var statusEntityList = Optional.ofNullable(status())
                .map(statusList -> statusList.stream().map(TaskStatus::valueOf).toList())
                .orElse(List.of());

        return new TaskSearchEntity(summary(), statusEntityList);
    }

    public TaskSearchDTO toDTO() {
        return new TaskSearchDTO(summary(), status());
    }
}
