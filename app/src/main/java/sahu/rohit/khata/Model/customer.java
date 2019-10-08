package sahu.rohit.khata.Model;

import java.io.Serializable;

public class customer implements Serializable {
    private String first_name;
    private String last_name;
    private String gender;
    private String mobile;
    private String whatsapp;
    private String address;

    public customer(String first_name, String last_name, String gender, String mobile, String whatsapp, String address, byte[] id_image) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.mobile = mobile;
        this.whatsapp = whatsapp;
        this.address = address;
        this.id_image = id_image;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getId_image() {
        return id_image;
    }

    public void setId_image(byte[] id_image) {
        this.id_image = id_image;
    }

    private byte[] id_image;
}
