package com.buscame.oncor.buscame.Services;

import com.buscame.oncor.buscame.Device.Model.Device;

import java.util.List;

public interface Services {

    interface ServicesType{

        interface View{
            void notification(String messages);
            void notificationError(String error);
            void loadDevices(List<Device> device);
        }

        interface Presenter{
            void loadDevices(List<Device> device);
            void notification(String messages);
            void notificationError(String error);
            void listServices(String accessToken);

        }

        interface Model{
            void listServices(String accessToken);
        }

    }





}
