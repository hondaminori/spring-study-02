package com.example.demo.todo.controller.tasks;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.todo.service.tasks.TaskService;

@Controller
public class TasksController {
    private final TaskService taskService = new TaskService();

    @GetMapping("/tasks")
    public String list(Model model) {
        model.addAttribute("taskList", taskService.find());
        return "tasks/list";
    }
}
