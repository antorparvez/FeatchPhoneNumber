package com.antorparvez.featchphonenumber;

public class Call_User_Model {
    private String username;
    private String userUid;
    private String imageURL;
    private String email;
    private String status;
    private String search;
    private String phone;
    private String country;
    private String flag;

    public Call_User_Model() {
    }

    public Call_User_Model(String username, String userUid, String imageURL, String email, String status, String search, String phone, String country, String flag) {
        this.username = username;
        this.userUid = userUid;
        this.imageURL = imageURL;
        this.email = email;
        this.status = status;
        this.search = search;
        this.phone = phone;
        this.country = country;
        this.flag = flag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
