package com.example.demo.todo.service.tasks;

import java.util.List;

public class TaskService {
    public List<TaskEntity> find() {
        return List.of (
            new TaskEntity(
                1L,
                "Spring Bootを学ぶ" ,
                "TODOアプリケーションを作ってみる" ,
                TaskStatus.TODO
            ),
            new TaskEntity(
                2L,
                "Spring Securtyを学ぶ" ,
                "ログインを作ってみる" ,
                TaskStatus.DOING
            )
        );
    }
}
