package com.example.motokarkotlin;

import android.widget.ImageView;
import de.hdodenhof.circleimageview.CircleImageView;

public class Accounts {
    public String username;
    public String firstName;
    public String lastName;
    public String userBirthdate;
    public String emailAddress;
    public String phoneNumber;
    public String userPassword;
    public String profilePicture;
    public String govID;
    public String vehLicense;
    public String mkWalletAmount;
    public String userReferCode;

    public Accounts(){

    }

    public Accounts(String username, String firstName, String lastName, String userBirthdate,
                    String emailAddress, String phoneNumber, String userPassword, String profilePicture, String govID, String vehLicense, String mkWalletAmount,
                    String userReferCode){

        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userBirthdate = userBirthdate;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.userPassword = userPassword;
        this.profilePicture = profilePicture;
        this.govID = govID;
        this.vehLicense = vehLicense;
        this.mkWalletAmount = mkWalletAmount;
        this.userReferCode = userReferCode;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUserBirthdate() {
        return userBirthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }


    public String getProfilePicture() {
        return profilePicture;
    }

    public String getMkWalletAmount() {
        return mkWalletAmount;
    }

    public String getGovID() {
        return govID;
    }

    public String getVehLicense() {
        return vehLicense;
    }

    public String getUserReferCode() {
        return userReferCode;
    }
}
