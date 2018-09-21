package com.buscame.oncor.buscame.Profile.Rest;

import com.buscame.oncor.buscame.Profile.Model.Account;

import java.io.Serializable;

public class AccountUpdateRequest implements Serializable {

    private Account account;

    public AccountUpdateRequest() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
