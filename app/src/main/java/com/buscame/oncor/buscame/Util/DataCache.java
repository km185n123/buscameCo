package com.buscame.oncor.buscame.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.buscame.oncor.buscame.Device.Model.Device;
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse;
import com.buscame.oncor.buscame.Profile.Model.Account;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public  class DataCache {

   static SharedPreferences.Editor editor;

    public static void writeData(Context context,String key,String value){
        // MY_PREFS_NAME - a static String variable like:
        //public static final String MY_PREFS_NAME = "MyPrefsFile";
         editor = context.getSharedPreferences("cache", MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();

    }

    public static void clear(Context context){
        // MY_PREFS_NAME - a static String variable like:
        //public static final String MY_PREFS_NAME = "MyPrefsFile";
         editor = context.getSharedPreferences("cache", MODE_PRIVATE).edit();

        editor.clear();

    }

    public static String readData(Context context,String key){


        SharedPreferences prefs = context.getSharedPreferences("cache", MODE_PRIVATE);


        return prefs.getString(key, "");
    }


    public static Device getDevice(String code,Context context){


        DeviceFindAllByUserResponse data = getDevices(context);


        for (Device device: data.getDevices()) {

            if (device.getCode().equals(code)){

                return device;
            }

        }

        return null;


    }


    public static void addDevice(Context context,Device device){

        DeviceFindAllByUserResponse data = new DeviceFindAllByUserResponse();

        if (getDevices(context) != null ){

            data = getDevices(context);

            data.getDevices().add(device);
        }else{
            List<Device> list = new ArrayList<>();
            list.add(device);

            data.setDevices(list);
        }



            setDevices(data,context);

    }

    public static void updateDevice(Context context,Device newDevice){


        DeviceFindAllByUserResponse data = getDevices(context);


        for (Device device: data.getDevices()) {

            if (device.getCode().equals(newDevice.getCode())){

               int position = data.getDevices().indexOf(device);
               data.getDevices().set(position,newDevice);
               setDevices(data,context);

            }

        }



    }


    public static DeviceFindAllByUserResponse getDevices( Context context){

        String dataDevicesJson = DataCache.readData(context,"dataDevicesJson");

        DeviceFindAllByUserResponse data = new Gson().fromJson(dataDevicesJson, DeviceFindAllByUserResponse.class);


        return data;
    }

    public static Account getAccount(Context context){

        String accountJson = DataCache.readData(context,"Account");

        Account account = new Gson().fromJson(accountJson, Account.class);


        return account;
    }

    public static void setAccount(Account account, Context context ){

        String accountJson = new Gson().toJson(account);

        DataCache.writeData(context,"Account",accountJson);



    }


    public static void setDevices(DeviceFindAllByUserResponse data ,Context context){

        String dataJson = new Gson().toJson(data);

         DataCache.writeData(context,"dataDevicesJson",dataJson);
    }


}
