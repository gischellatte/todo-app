package com.todo.tasks;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping ("/")
public class appController {

    @GetMapping()
    public String mainPath() {
        return "Welcome to the app";
    }
    @PostMapping
    public String postMethodName() {
        return "Showing a post request";
    }
    
}

