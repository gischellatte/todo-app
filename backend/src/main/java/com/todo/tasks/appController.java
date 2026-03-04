package com.todo.tasks;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


//when we start out app, it looks for a controller. If it finds a controller, it creates an instance of the controller and maps all the routes
@RestController //a must have in a controller
@RequestMapping ("/")
public class appController {

    @GetMapping()
    //controllers have methods
    public String mainPath() {
        return "Welcome to the app";
    }
    @PostMapping
    public String postMethodName() {
        return "Showing a post request";
    }
    
}
