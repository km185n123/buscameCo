package com.buscame.oncor.buscame.Device.Model;



import com.buscame.oncor.buscame.Profile.Model.Account;
import com.buscame.oncor.buscame.Profile.Model.Category;
import com.buscame.oncor.buscame.Profile.Model.Generic;
import com.buscame.oncor.buscame.Profile.Model.Model;

public class Device extends Generic {



    private String code;
    private String description;
    private String alias;
    private String image;
    private Category category;
    private Model model;
    private Account account;
    private String numberSimcard;
    private String color;
    private String brand;
    private String plate;
    private String feature;

    public String getNumberSimcard() {
        return numberSimcard;
    }

    public void setNumberSimcard(String numberSimcard) {
        this.numberSimcard = numberSimcard;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Device() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
