package com.example.ar.kbu_duyuru;

public class DuyuruModel {
    private String title;
    private String tarih;


    public DuyuruModel(String title, String tarih) {
        this.title = title;
        this.tarih = tarih;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
    @Override
    public String toString() {
        return title + tarih;
    }
}


