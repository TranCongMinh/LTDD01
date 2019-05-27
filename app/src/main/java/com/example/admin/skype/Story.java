package com.example.admin.skype;

import java.io.Serializable;

public class Story implements Serializable {
    private String name;
    private String author;
    private String content;

    public Story()  {

    }

    public Story(String name, String author, String content) {
        this.name= name;
        this.author= author;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() { return content; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

}