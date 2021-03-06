package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by michaeldelli-gatti on 6/27/16.
 */
@Entity
@Table(name = "files")
public class AnonFile {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String originalFilename;

    @Column(nullable = false)
    String realFilename;

    @Column(nullable = false)
    String comment;

    @Column(nullable = false)
    boolean isPerm;

    @Column
    String password;


    public AnonFile() {
    }

    public AnonFile(String originalFilename, String realFilename, String comment, boolean isPerm, String password) {
        this.originalFilename = originalFilename;
        this.realFilename = realFilename;
        this.comment = comment;
        this.isPerm = isPerm;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getRealFilename() {
        return realFilename;
    }

    public void setRealFilename(String realFilename) {
        this.realFilename = realFilename;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPerm() {
        return isPerm;
    }

    public void setPerm(boolean perm) {
        isPerm = perm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
