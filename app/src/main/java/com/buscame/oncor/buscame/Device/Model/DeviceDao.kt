package com.buscame.oncor.buscame.Device.Model

import android.arch.persistence.room.*

@Dao
interface DeviceDao {

   @Query("SELECT * FROM tbl_device")
    fun getAll(): List<RoomDevice>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg device: RoomDevice)


    @Delete
    fun delete(device: RoomDevice)


    @Query("SELECT * FROM tbl_device WHERE id = (SELECT MAX(ID) FROM tbl_device); ")
    fun getLastId():Int

    @Query("SELECT * FROM tbl_device  ")
    fun getId():Int


    @Query("DELETE FROM tbl_device")
    fun nukeTable()

}