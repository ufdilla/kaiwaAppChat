package com.example.easysoft.kaiwaapp;
import java.io.Serializable;

public class myModel implements Serializable{
    String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}