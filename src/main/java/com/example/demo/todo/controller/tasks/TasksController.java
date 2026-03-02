package com.example.demo.todo.controller.tasks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TasksController {
    @GetMapping("/tasks")
    public String list(Model model) {
        var task = new TaskDTO(
            1L,
            "Spring Bootを学ぶ" ,
            "TODOアプリケーションを作ってみる" ,
            "TODO"
        );
        model.addAttribute("task", task);
        return "tasks/list";
    }
}
