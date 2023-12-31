package com.example.mySpring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String image;

    public Image(){}

    public Image(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
