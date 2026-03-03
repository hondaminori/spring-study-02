package com.example.demo.todo.controller.tasks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.todo.service.tasks.TaskService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TasksController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public String list(Model model) {
        var taskList = taskService.find()
                .stream()
                .map(TaskDTO::toDTO)
                .toList();

        model.addAttribute("taskList", taskList);
        return "tasks/list";
    }
}

/** Memo:

Udemy ＴＯＤＯ アプリを作ることを通して、Web アプリケーションの基礎を学びましょう。
 [33. TaskEntity と TaskDTO をマッピングしよう]　より。

(1) 普通にTaskEntityからTaskDTO に変換するだけ

public String list(Model model) {
    var taskList = taskService.find()
        .stream()
        .map(entity -> new TaskDTO(
                entity.id(),
                entity.summary(),
                entity.description(),
                entity.taskStatus().name()))
        .toList();
    model.addAttribute("taskList", taskList);
    return "tasks/list";
}

(2) TaskDTOに変換メソッドを追加

toDTOメソッド

public static TaskDTO toDTO(TaskEntity entity) {
    return new TaskDTO(
            entity.id(),
            entity.summary(),
            entity.description(),
            entity.taskStatus().name()
    );
}

TaskController

@GetMapping("/tasks")
public String list(Model model) {
    var taskList = taskService.find()
            .stream()
            .map(entity -> TaskDTO.toDTO(entity))
            .toList();

    model.addAttribute("taskList", taskList);
    return "tasks/list";
}


(2)TaskControllerでメソッド参照を使うように変更。

@GetMapping("/tasks")
public String list(Model model) {
    var taskList = taskService.find()
            .stream()
            .map(TaskDTO::toDTO)
            .toList();

    model.addAttribute("taskList", taskList);
    return "tasks/list";
}

*/


