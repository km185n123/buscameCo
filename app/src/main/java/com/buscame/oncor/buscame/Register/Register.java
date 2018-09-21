package com.buscame.oncor.buscame.Register;

public interface Register {

    interface View{

        void showMessageError(String result);
        void goToNewDevice();
    }

    interface Presenter{

        void showMessageError(String result);
        void goToNewDevice();
        void createNewUser(String name, String phone, String email, String adress, String userName, String password);

    }

    interface Model{

        void createNewUser(String name, String phone, String email, String adress, String userName, String password);


    }
}
