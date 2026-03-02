package com.example.demo.todo.controller.tasks;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TasksController {
    @GetMapping("/tasks")
    public String list(Model model) {
        var task1 = new TaskDTO(
            1L,
            "Spring Bootを学ぶ" ,
            "TODOアプリケーションを作ってみる" ,
            "TODO"
        );
        var task2 = new TaskDTO(
            2L,
            "Spring Securtyを学ぶ" ,
            "ログインを作ってみる" ,
            "TODO"
        );
        var taskList = List.of(task1, task2);
        model.addAttribute("taskList", taskList);
        return "tasks/list";
    }
}
