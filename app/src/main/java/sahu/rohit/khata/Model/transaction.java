package sahu.rohit.khata.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class transaction implements Serializable {
    private int id;
    private String username;
    private String amount;
    private String desc;
    private String date;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAmount() {
        return amount;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public transaction(int id, String username, String amount, String desc, String date) {
        this.id = id;
        this.username = username;
        this.amount = amount;
        this.desc = desc;
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return username + "                   " + amount;
    }
}
