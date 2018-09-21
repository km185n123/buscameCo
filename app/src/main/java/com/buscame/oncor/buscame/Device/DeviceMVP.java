package com.buscame.oncor.buscame.Device;

import android.content.Context;

import com.buscame.oncor.buscame.Device.Model.Device;
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse;


public interface DeviceMVP {

    interface View{

        void message(String message);
        void newDevice(Device device);
        void updateRenderDevice(Device device);
        void removeDevice(int position);
    }

    interface Presenter{

        void message(String message);
        void removeDevice(int position );
        void updateRenderDevice(Device device);
        void newDevice(Device device);
        void addDevice(String codeDevice,String accessToken,Context context);
        void removeDevice(String codeDevice,int position,String accessToken);
        void updateDataDevice(Device device,String accessToken);


    }
    interface Model{

        void addDevice(String codeDevice,String accessToken,Context context);
        void removeDevice(String codeDevice,int position,String accessToken);
        void updateDataDevice(Device device,String accessToken);

    }
}
