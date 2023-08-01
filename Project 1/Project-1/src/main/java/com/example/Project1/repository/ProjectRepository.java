package com.example.Project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project1.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
