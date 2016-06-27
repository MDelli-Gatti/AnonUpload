package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import com.theironyard.utils.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by michaeldelli-gatti on 6/27/16.
 */
@Controller
public class AnonFileController {
    @Autowired
    AnonFileRepository files;


    @PostConstruct
    public void init() throws SQLException {
        Server.createWebServer().start();
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, String comment, boolean isPerm, String password) throws IOException, PasswordStorage.CannotPerformOperationException {
        File dir = new File("public/files");
        dir.mkdirs();

        File uploadedFile = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(uploadedFile);
        fos.write(file.getBytes());

            AnonFile anonFile = new AnonFile(file.getOriginalFilename(), uploadedFile.getName(), comment, isPerm, PasswordStorage.createHash(password));
            files.save(anonFile);


        return "redirect:/";
    }
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String deleteFile(Integer id, String password) throws Exception {
        if (password != null){

            AnonFile af = files.findById(id);
            if (PasswordStorage.verifyPassword(password, af.getPassword())) {
                File f = new File("public/files/" + af.getRealFilename());
                f.delete();
                files.delete(af);
            }
        }
        else {
            throw new Exception("invalid password");
        }
        return "redirect:/";
    }
}
