package com.tinhngo.databaseandroid.repository.local.model;

/**
 * Created by tinhngo on 9/21/17.
 */

public class StudentModel {

    public String id;
    public String name;
    public String address;
    public String birthday;
    public String urlImage;

    public StudentModel(String id, String name, String address, String birthday, String urlImage) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.urlImage = urlImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
