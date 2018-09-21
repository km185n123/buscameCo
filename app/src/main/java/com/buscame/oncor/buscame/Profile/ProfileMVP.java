package com.buscame.oncor.buscame.Profile;

import com.buscame.oncor.buscame.Profile.Model.Account;

public interface ProfileMVP {

    interface View{

        void notification(String message);
        void notificationError(String error);
        void updateDataAccount(Account account);
    }

    interface Presenter{

        void notification(String message);
        void notificationError(String error);
        void updateDataAccount(Account account);

        void editDataProfile(String accessToken, Account account);
    }

    interface Model{

        void editDataProfile(String accessToken, Account account);
    }
}
