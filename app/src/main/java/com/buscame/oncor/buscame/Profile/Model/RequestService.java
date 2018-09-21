package com.buscame.oncor.buscame.Profile.Model;



public class RequestService extends Generic{

    private Account account;
    private Service service;
   // private ServiceStatus serviceStatus;

    public RequestService() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

   /* public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }*/
}


