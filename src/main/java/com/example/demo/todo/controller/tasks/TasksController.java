package com.example.demo.todo.controller.tasks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.todo.service.tasks.TaskService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TasksController {
    private final TaskService taskService;

    @GetMapping
    public String list(TaskSearchForm searchForm, Model model) {

        var taskList = taskService.find(searchForm.toEntity())
            .stream()
            .map(TaskDTO::toDTO)
            .toList();

        model.addAttribute("taskList", taskList);
        model.addAttribute("searchDTO", searchForm.toDTO());
        return "tasks/list";
    }

    @GetMapping("/{id}")
    public String showDetail(@PathVariable("id") long taskId, Model model) {
        var taskDTO = taskService.findById(taskId)
            .map(TaskDTO::toDTO)
            .orElseThrow(TaskNotFoundException::new);
        model.addAttribute("task", taskDTO);
        return "tasks/detail";
    }

    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute TaskForm form, Model model) {
        model.addAttribute("mode", "CREATE");
        return "tasks/form";
    }

    @PostMapping
    public String create(@Validated TaskForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(form, model);
        }
        form.toEntity();
        taskService.create(form.toEntity());
        return "redirect:tasks";
    }

    @GetMapping("/{id}/editForm")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        var form = taskService.findById(id)
            .map(TaskForm::fromEntity)
            .orElseThrow(TaskNotFoundException::new);
        model.addAttribute("taskForm", form);
        model.addAttribute("mode", "EDIT");
        return "tasks/form";
    }

    @PutMapping("{id}")
    public String update(
        @PathVariable("id") long id, 
        @Validated @ModelAttribute TaskForm form, 
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "EDIT");
            return "tasks/form";
        }
        var entity = form.toEntity(id);
        taskService.update(entity);
        return "redirect:/tasks/{id}";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") long id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }
}

/**
 * Memo:
 * 
 * Udemy ＴＯＤＯ アプリを作ることを通して、Web アプリケーションの基礎を学びましょう。
 * [33. TaskEntity と TaskDTO をマッピングしよう] より。
 * 
 * (1) 普通にTaskEntityからTaskDTO に変換するだけ
 * 
 * public String list(Model model) {
 * var taskList = taskService.find()
 * .stream()
 * .map(entity -> new TaskDTO(
 * entity.id(),
 * entity.summary(),
 * entity.description(),
 * entity.taskStatus().name()))
 * .toList();
 * model.addAttribute("taskList", taskList);
 * return "tasks/list";
 * }
 * 
 * (2) TaskDTOに変換メソッドを追加
 * 
 * toDTOメソッド
 * 
 * public static TaskDTO toDTO(TaskEntity entity) {
 * return new TaskDTO(
 * entity.id(),
 * entity.summary(),
 * entity.description(),
 * entity.taskStatus().name()
 * );
 * }
 * 
 * TaskController
 * 
 * @GetMapping("/tasks")
 * public String list(Model model) {
 * var taskList = taskService.find()
 * .stream()
 * .map(entity -> TaskDTO.toDTO(entity))
 * .toList();
 * 
 * model.addAttribute("taskList", taskList);
 * return "tasks/list";
 * }
 * 
 * 
 * (2)TaskControllerでメソッド参照を使うように変更。
 * 
 * @GetMapping("/tasks")
 * public String list(Model model) {
 * var taskList = taskService.find()
 * .stream()
 * .map(TaskDTO::toDTO)
 * .toList();
 * 
 * model.addAttribute("taskList", taskList);
 * return "tasks/list";
 * }
 * 
 */
