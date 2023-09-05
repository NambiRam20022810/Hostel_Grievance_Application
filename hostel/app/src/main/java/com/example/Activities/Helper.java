package com.example.hoste;

public class Helper {
    String hostelname,residentfloor,residentissue,residentroomnumber,otherissuesoftheresident,residentregisternumber;

    public Helper(String hostelname, String residentfloor, String residentissue, String residentroomnumber, String otherissuesoftheresident, String residentregisternumber) {
        this.hostelname = hostelname;
        this.residentfloor = residentfloor;
        this.residentissue = residentissue;
        this.residentroomnumber = residentroomnumber;
        this.otherissuesoftheresident = otherissuesoftheresident;
        this.residentregisternumber = residentregisternumber;
    }

    public void setHostelname(String hostelname) {
        this.hostelname = hostelname;
    }

    public void setResidentfloor(String residentfloor) {
        this.residentfloor = residentfloor;
    }

    public void setResidentissue(String residentissue) {
        this.residentissue = residentissue;
    }

    public void setResidentroomnumber(String residentroomnumber) {
        this.residentroomnumber = residentroomnumber;
    }

    public void setOtherissuesoftheresident(String otherissuesoftheresident) {
        this.otherissuesoftheresident = otherissuesoftheresident;
    }

    public void setResidentregisternumber(String residentregisternumber) {
        this.residentregisternumber = residentregisternumber;
    }

    public String getHostelname() {
        return hostelname;
    }

    public String getResidentfloor() {
        return residentfloor;
    }

    public String getResidentissue() {
        return residentissue;
    }

    public String getResidentroomnumber() {
        return residentroomnumber;
    }

    public String getOtherissuesoftheresident() {
        return otherissuesoftheresident;
    }

    public String getResidentregisternumber() {
        return residentregisternumber;
    }
}
