package com.example.ar.kbu_duyuru;

public class DuyuruModel {
    private String title;


    public DuyuruModel(String title, String address, String phone) {
        this.title = title;

    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    @Override
    public String toString() {

        return title;
    }
}


