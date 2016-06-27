package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by michaeldelli-gatti on 6/27/16.
 */
@RestController
public class AnonFileRestController {
    @Autowired
    AnonFileRepository files;

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public Iterable<AnonFile> getFiles(){
        if (files.count() > 10){
            files.delete(files.findFirstByOrderById());
        }
        return files.findAll();
    }

//    @RequestMapping(path = "/files/{id}", method = RequestMethod.DELETE)
//    public void deleteUser(@PathVariable("id") int id){
//        files.delete(id);
//    }
}
