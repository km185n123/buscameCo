package com.buscame.oncor.buscame.DashBoard;


public interface DashBoard {

    interface View{
        void notification(String message);
        void notificationError(String error);
    }

    interface Presenter{
        void notification(String message);
        void notificationError(String error);
        void commandDevice(int commandId,String imei,double lat,double lng , String accessToken);

    }

    interface Model{
        void commandDevice(int commandId,String imei,double lat,double lng , String accessToken);


    }
}
