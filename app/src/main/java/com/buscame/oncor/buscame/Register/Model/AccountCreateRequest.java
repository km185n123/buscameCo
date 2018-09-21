package com.buscame.oncor.buscame.Register.Model;

import com.buscame.oncor.buscame.Profile.Model.Account;

import java.io.Serializable;


public class AccountCreateRequest implements Serializable {

    private Account account;

    public AccountCreateRequest() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
