package com.example.yahyagungorur.newapp;

/**
 * Created by sadievrenseker on 3/29/17.
 */

public class User {
    int skor;
    String name;

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return "Skor :"+skor + " name : "+name;
    }
}
