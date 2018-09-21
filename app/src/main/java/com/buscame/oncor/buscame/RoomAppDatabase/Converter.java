package com.buscame.oncor.buscame.RoomAppDatabase;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 4/12/17.
 */

public class Converter {
/*
    public static String strSeparator = "__,__";

    @TypeConverter
    public static String convertListToString(List<passenger> passenger) {
        passenger[] passengerArray = new passenger[passenger.size()];
        for (int i = 0; i <= passenger.size()-1; i++) {
            passengerArray[i] = passenger.get(i);

        }
        String str = "";
        Gson gson = new Gson();
        for (int i = 0; i < passengerArray.length; i++) {
            String jsonString = gson.toJson(passengerArray[i]);
            str = str + jsonString;
            if (i < passengerArray.length - 1) {
                str = str + strSeparator;
            }
        }
        return str;
    }

    @TypeConverter
    public static List<passenger> convertStringToList(String videoString) {
        String[] videoArray = videoString.split(strSeparator);
        List<passenger> videos = new ArrayList<passenger>();
        Gson gson = new Gson();
        for (int i=0;i<videoArray.length-1;i++){
            videos.add(gson.fromJson(videoArray[i] , passenger.class));
        }
        return videos;
    }
    */
}