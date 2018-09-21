package com.buscame.oncor.buscame.Device.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "tbl_device")
public class RoomDevice {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "create_date")
    private String createDate;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "update_date")
    private String updateDate;
    @ColumnInfo(name = "alias")
    private String alias;
    @ColumnInfo(name = "code")
    private String code;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "account_id")
    private String account_id;
    @ColumnInfo(name = "category_id")
    private String category_id;
    @ColumnInfo(name = "medel_id")
    private String medel_id;


 public int getId() {
  return id;
 }

 public String getCreateDate() {
  return createDate;
 }

 public void setCreateDate(String createDate) {
  this.createDate = createDate;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getUpdateDate() {
  return updateDate;
 }

 public void setUpdateDate(String updateDate) {
  this.updateDate = updateDate;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getAlias() {
  return alias;
 }

 public void setAlias(String alias) {
  this.alias = alias;
 }

 public String getCode() {
  return code;
 }

 public void setCode(String code) {
  this.code = code;
 }

 public String getDescription() {
  return description;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public String getAccount_id() {
  return account_id;
 }

 public void setAccount_id(String account_id) {
  this.account_id = account_id;
 }

 public String getCategory_id() {
  return category_id;
 }

 public void setCategory_id(String category_id) {
  this.category_id = category_id;
 }

 public String getMedel_id() {
  return medel_id;
 }

 public void setMedel_id(String medel_id) {
  this.medel_id = medel_id;
 }
}
