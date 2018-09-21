package com.buscame.oncor.buscame.Profile.Model;


import java.util.*;

public class Account extends Generic  {

    private String phone;
    private String address;
    private String username;
    private String password;
    private Set<Role> roles;
    private String image;
    private String mail;

    public Account() {
    }

    public Account(Account account) {
        this.phone = account.phone;
        this.address = account.address;
        this.username = account.username;
        this.password = account.password;
        this.roles = account.roles;
        this.mail = account.mail;

     }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getUsername() {
        return username;
    }

    
    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
