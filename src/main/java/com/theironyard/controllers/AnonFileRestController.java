package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by michaeldelli-gatti on 6/27/16.
 */
@RestController
public class AnonFileRestController {
    @Autowired
    AnonFileRepository files;

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public Iterable<AnonFile> getFiles(){

        ArrayList<AnonFile> nonPermFiles = files.findByIsPermFalseOrderById();
        if (nonPermFiles.size() > 10){
            AnonFile delete = nonPermFiles.get(0);
            File f = new File("public/files/" + delete.getRealFilename());
            f.delete();
            files.delete(delete);
        }

        return files.findAll();
    }


}
