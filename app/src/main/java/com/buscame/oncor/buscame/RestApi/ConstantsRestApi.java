package com.buscame.oncor.buscame.RestApi;


/**
 * Created by root on 24/11/17.
 */

public class ConstantsRestApi {

    public final static String CONTROLER_CUSTOMER = "cliente/";
    // MUDULES
    public final static String AUTH = "oauth/";
    public final static String REST = "rest/";
    // BASE URL
  public final static String ROOT_URL = "http://192.168.0.254:8081/buscame/";
    //public final static String ROOT_URL = "http://35.231.67.205:8081/buscame/";

//    http://192.168.0.254:8081/buscame/oauth/token
    public final static String END_TOKEN = "token";
    public final static String AUTHENTICATION = ROOT_URL + AUTH + END_TOKEN;

    //    http://192.168.0.254:8081/buscame/rest/oauth/logout
    public final static String END_LOGOUT = "oauth/logout";
    public final static String LOGOUT = ROOT_URL + REST + END_LOGOUT;

    //    http://192.168.0.254:8081/buscame/rest/city/findAll
    public final static String END_City = "oauth/logout";
    public final static String CITY = ROOT_URL + REST + END_City;


    //    http://192.168.0.254:8081/buscame/rest/account/update
    public final static String END_UPDATE_ACCOUNT = "account/update";
    public final static String UPDATE_ACCOUNT = ROOT_URL + REST + END_UPDATE_ACCOUNT;

    //    http://192.168.0.254:8081/buscame/rest/token
    public final static String END_CREATE_ACCOUNT = "account/create";
    public final static String CREATE_ACCOUNT = ROOT_URL + REST + END_CREATE_ACCOUNT;

    //    http://192.168.0.254:8081/buscame/rest/city/findAll
    public final static String END_CITY_FINDALL = "city/findAll";
    public final static String CITY_FINDALL = ROOT_URL + REST + END_CITY_FINDALL;

    //    http://192.168.0.254:8081/buscame/rest/serviceCenter/findAllByRadiusAndCity
    public final static String END_SERVICE_CENTER_FIND_ALL = "serviceCenter/findAllByRadiusAndCity";
    public final static String SERVICE_CENTER_FIND_ALL = ROOT_URL + REST + END_SERVICE_CENTER_FIND_ALL;

    //    http://192.168.0.254:8081/buscame/rest/device/asociate
    public final static String END_DEVICE_ASOCIATE = "device/asociate";
    public final static String DEVICE_ASOCIATE = ROOT_URL + REST + END_DEVICE_ASOCIATE;

    //    http://192.168.0.254:8081/buscame/rest/device/dissociate
    public final static String END_DEVICE_DISASSOCIATE = "device/disassociate";
    public final static String DEVICE_DISASSOCIATE = ROOT_URL + REST + END_DEVICE_DISASSOCIATE;

    //    http://192.168.0.254:8081/buscame/rest/device/findAllByAccount
    public final static String END_DEVICE_FIND_ALL = "device/findAllByAccount";
    public final static String DEVICE_FIND_ALL = ROOT_URL + REST + END_DEVICE_FIND_ALL;

    // http://192.168.0.254:8080/buscame/rest/device/command
    public final static String END_DEVICE_COMAND = "device/command";
    public final static String DEVICE_COMAND = ROOT_URL + REST + END_DEVICE_COMAND;

    // http://192.168.0.254:8080/buscame/rest/device/historic
    public final static String END_DEVICE_HISTORIC = "device/historic";
    public final static String DEVICE_HISTORIC = ROOT_URL + REST + END_DEVICE_HISTORIC;

    // http://192.168.0.254:8080/buscame/rest/device/updateAliasAndImage
    public final static String END_DEVICE = "device/updateAliasAndImage";
    public final static String DEVICE = ROOT_URL + REST + END_DEVICE;




}
