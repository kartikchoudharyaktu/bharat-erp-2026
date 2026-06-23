package com.bharaterp.service;

import com.bharaterp.model.Task;
import com.bharaterp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTaskName(taskDetails.getTaskName());
        task.setDescription(taskDetails.getDescription());
        task.setAssignedTo(taskDetails.getAssignedTo());
        task.setStartDate(taskDetails.getStartDate());
        task.setDueDate(taskDetails.getDueDate());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        task.setEstimatedHours(taskDetails.getEstimatedHours());
        return taskRepository.save(task);
    }
    
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    
    public List<Task> getTasksByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }
    
    public List<Task> getTasksByEmployee(Long employeeId) {
        return taskRepository.findByAssignedTo(employeeId);
    }
    
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }
}
