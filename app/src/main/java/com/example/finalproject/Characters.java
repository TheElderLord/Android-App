package com.example.finalproject;

import java.util.ArrayList;
import java.util.List;

public class Characters {
    private int char_id;
    private String name;
    private String img;
    private String status;
    private String birthday;
    private ArrayList<String> occupation;


    public ArrayList<String> getOccupation() {
        return occupation;
    }

    public Characters(List<Characters> body) {
        this.img = body.get(0).img;
        this.name = body.get(0).name;
        this.birthday = body.get(0).birthday;
        this.status = body.get(0).status;
        this.occupation = body.get(0).occupation;
    }

    public int getChar_id() {
        return char_id;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getStatus() {
        return status;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
