package com.example.gestionnairenote;

import java.util.Date;

public class Note {
    private int _id;
    private String title;
    private String content;


    // Constructeur
    public Note(int id, String title, String content) {
        _id=id;
        this.title = title;
        this.content = content;

    }

    @Override
    public String toString() {
        return "note " + _id +") : "+
                 title +"";
    }

    public Note() {

    }

    // Getters et Setters

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
