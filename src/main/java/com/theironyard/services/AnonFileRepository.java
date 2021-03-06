package com.theironyard.services;

import com.theironyard.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by michaeldelli-gatti on 6/27/16.
 */
public interface AnonFileRepository extends CrudRepository<AnonFile, Integer> {
    ArrayList<AnonFile> findByIsPermFalseOrderById();
    AnonFile findById(Integer id);
}
