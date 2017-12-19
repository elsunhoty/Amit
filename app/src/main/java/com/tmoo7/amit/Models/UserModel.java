package com.tmoo7.amit.Models;

/**
 * Created by othello on 12/19/2017.
 */

public class UserModel {
    private  int _id,userFK;
    private String address,userNumber;
    private double latitude,langtitude;

    public UserModel() {
    }

    public UserModel(int _id, int userFK, String address, String userNumber, double latitude, double langtitude) {

        this._id = _id;
        this.userFK = userFK;
        this.address = address;
        this.userNumber = userNumber;
        this.latitude = latitude;
        this.langtitude = langtitude;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUserFK() {
        return userFK;
    }

    public void setUserFK(int userFK) {
        this.userFK = userFK;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLangtitude() {
        return langtitude;
    }

    public void setLangtitude(double langtitude) {
        this.langtitude = langtitude;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "_id=" + _id +
                ", userFK=" + userFK +
                ", address='" + address + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", latitude=" + latitude +
                ", langtitude=" + langtitude +
                '}';
    }

}
