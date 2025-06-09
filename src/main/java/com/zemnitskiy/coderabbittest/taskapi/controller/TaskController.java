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

    /**
     * Handles GET requests to the root path and adds all tasks to the model for display.
     *
     * @param model the model to which the list of tasks will be added
     * @return the name of the view displaying the tasks
     */
    @GetMapping("/")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskStore.values());
        return "tasks";
    }

    /****
     * Handles POST requests to add a new task with the given description.
     *
     * @param description the description of the new task
     * @return a redirect to the root path after adding the task
     */
    @PostMapping("/add")
    public String addTask(@RequestParam("description") String description) {
        long id = idGenerator.incrementAndGet();
        Task task = new Task(id, description);
        taskStore.put(id, task);
        return "redirect:/";
    }

    /****
     * Removes the task with the specified ID from the task store and redirects to the task list view.
     *
     * @param id the ID of the task to delete, as a string path variable
     * @return a redirect instruction to the root path
     */
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable String id) {
        taskStore.remove(Long.parseLong(id));
        return "redirect:/";
    }
}