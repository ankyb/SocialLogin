package com.example.android.sociallogin;

public class UserInfo {
    public String nFullName;
    public String nAge;
    public String nEmail;

    public UserInfo() {

    }

    public UserInfo(String nFullName, String nAge, String nEmail) {
        this.nFullName = nFullName;
        this.nAge = nAge;
        this.nEmail = nEmail;
    }

    public String getnFullName() {
        return nFullName;
    }

    public void setnFullName(String nFullName) {
        this.nFullName = nFullName;
    }

    public String getnAge() {
        return nAge;
    }

    public void setnAge(String nAge) {
        this.nAge = nAge;
    }

    public String getnEmail() {
        return nEmail;
    }

    public void setnEmail(String nEmail) {
        this.nEmail = nEmail;
    }




}

