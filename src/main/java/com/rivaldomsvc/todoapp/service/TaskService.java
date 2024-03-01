package com.rivaldomsvc.todoapp.service;

import com.rivaldomsvc.todoapp.exceptions.ToDoExceptions;
import com.rivaldomsvc.todoapp.mapper.TaskInDTOToTask;
import com.rivaldomsvc.todoapp.persistence.entity.Task;
import com.rivaldomsvc.todoapp.persistence.entity.TaskStatus;
import com.rivaldomsvc.todoapp.persistence.repository.TaskRepository;
import com.rivaldomsvc.todoapp.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO) { //crea la tarea
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }

    public List<Task> findAll(){ //obtenemos todas las tareas
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status) { // Obtenemos las tareas por estado
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskFinished(Long id) { // marcar la tarea como finalizada con ID
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        this.repository.markTaskFinished((id));
    }

    public void deleteById(Long id) { // tarea finalizada
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        this.repository.deleteById(id);
    }

}
