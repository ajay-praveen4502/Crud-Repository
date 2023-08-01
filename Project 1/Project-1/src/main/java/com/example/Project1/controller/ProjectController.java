package com.example.Project1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project1.model.Project;
import com.example.Project1.repository.ProjectRepository;


@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    //to show all datas in the table
    @GetMapping("/show_all")
    public List<Project> getAllProjects(){
        return (List<Project>) projectRepository.findAll();    
    }

    //to create or insert the data into table
    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        Project _project = projectRepository
            .save(new Project(project.getFirstname(), project.getLastname(), project.getPassword()));
        return new ResponseEntity<>(_project, HttpStatus.CREATED);
    }

    //to delete all datas in the table
    @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus> deleteAllProject(){
        projectRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //to update the table datas using id
    @PutMapping("/insert/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") long id, @RequestBody Project project){
            Optional<Project> __project = projectRepository.findById(id);
        if(__project.isPresent()){
            Project _project = __project.get();
            _project.setFirstname(project.getFirstname());
            _project.setLastname(project.getLastname());
            _project.setPassword(project.getPassword());

            return new ResponseEntity<>(projectRepository.save(_project), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}