package com.example.Fitness_Studio.store_data;

public class storing_data {


    String username;
    String email;
    String password;
    String email_data;
    String name_data;
    String gender_data;
    String dob_data;
    String height_data;
    String weight_data;


    public storing_data(String username,String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }



    public storing_data(String email_data, String name_data, String gender_data, String dob_data, String height_data, String weight_data) {
        this.email_data = email_data;
        this.name_data = name_data;
        this.gender_data = gender_data;
        this.dob_data = dob_data;
        this.height_data = height_data;
        this.weight_data = weight_data;
    }

    public String getEmail_data() {
        return email_data;
    }

    public void setEmail_data(String email_data) {
        this.email_data = email_data;
    }

    public String getName_data() {
        return name_data;
    }

    public void setName_data(String name_data) {
        this.name_data = name_data;
    }

    public String getGender_data() {
        return gender_data;
    }

    public void setGender_data(String gender_data) {
        this.gender_data = gender_data;
    }

    public String getDob_data() {
        return dob_data;
    }

    public void setDob_data(String dob_data) {
        this.dob_data = dob_data;
    }

    public String getHeight_data() {
        return height_data;
    }

    public void setHeight_data(String height_data) {
        this.height_data = height_data;
    }

    public String getWeight_data() {
        return weight_data;
    }

    public void setWeight_data(String weight_data) {
        this.weight_data = weight_data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
