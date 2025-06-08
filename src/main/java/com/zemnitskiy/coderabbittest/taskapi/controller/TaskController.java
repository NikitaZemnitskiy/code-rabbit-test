package com.zemnitskiy.coderabbittest.taskapi.controller;

import com.zemnitskiy.coderabbittest.taskapi.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final Map<Long, Task> taskStore = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        long id = idGenerator.incrementAndGet();
        task.setId(id);
        taskStore.put(id, task);
        return task;
    }

    @GetMapping
    public Collection<Task> getTasks() {
        return taskStore.values();
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskStore.remove(id);
    }
}