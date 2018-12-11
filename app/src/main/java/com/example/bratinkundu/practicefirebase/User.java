package com.example.bratinkundu.practicefirebase;

public class User {
    public String name;
    public String password;
    public  User(){

    }
    public User(String name, String password) {
        this.name = name;
        this.password=password;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;


    }


}
