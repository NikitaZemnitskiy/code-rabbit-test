package com.zemnitskiy.coderabbittest.taskapi.controller;

import com.zemnitskiy.coderabbittest.taskapi.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class TaskController {

    private final Map<Long, Task> taskStore = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping("/")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskStore.values());
        return "tasks";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam("description") String description) {
        long id = idGenerator.incrementAndGet();
        Task task = new Task(id, description);
        taskStore.put(id, task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable String id) {
        taskStore.remove(Long.parseLong(id));
        return "redirect:/";
    }
}