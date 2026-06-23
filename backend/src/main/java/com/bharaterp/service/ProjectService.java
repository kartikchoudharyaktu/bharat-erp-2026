package com.bharaterp.service;

import com.bharaterp.model.Project;
import com.bharaterp.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }
    
    public Project createProject(Project project) {
        project.setProjectCode("PRJ-" + System.currentTimeMillis());
        return projectRepository.save(project);
    }
    
    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectRepository.findById(id).orElseThrow();
        project.setProjectName(projectDetails.getProjectName());
        project.setDescription(projectDetails.getDescription());
        project.setStartDate(projectDetails.getStartDate());
        project.setEndDate(projectDetails.getEndDate());
        project.setStatus(projectDetails.getStatus());
        project.setBudget(projectDetails.getBudget());
        return projectRepository.save(project);
    }
    
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
    
    public List<Project> getProjectsByStatus(String status) {
        return projectRepository.findAll();
    }
}
