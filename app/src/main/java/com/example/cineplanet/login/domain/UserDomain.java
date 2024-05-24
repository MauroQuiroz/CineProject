package com.example.cineplanet.login.domain;

import android.text.Editable;

import java.util.Date;

public class UserDomain {
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String email;
    private String celar;
    private String password;
    private String dni;
    private String department;
    private String province;
    private String district;
    private String gender;
    private String birthday;
    private int favoriteCinema;
    private int id;

    public UserDomain(String name, String lastNameP, String lastNameM, String email, String celar, String password, String dni, String department, String province, String district, String gender, String birthday, int favoriteCinema, int id) {
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.email = email;
        this.celar = celar;
        this.password = password;
        this.dni = dni;
        this.department = department;
        this.province = province;
        this.district = district;
        this.gender = gender;
        this.birthday = birthday;
        this.favoriteCinema = favoriteCinema;
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelar() {
        return celar;
    }

    public void setCelar(String celar) {
        this.celar = celar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getFavoriteCinema() {
        return favoriteCinema;
    }

    public void setFavoriteCinema(int favoriteCinema) {
        this.favoriteCinema = favoriteCinema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
