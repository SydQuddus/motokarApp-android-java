package com.example.motokarkotlin;

public class Vehicle {

    public String username;
    public String vehicleConfig;
    public String vehicleDescription;
    public String vehicleName;
    public String vehiclePicture;
    public String vehicleRate;
    public String vehicleRateDay;
    public String vehicleRateMonth;
    public String vehicleRateWeek;
    public String vehicleReview;
    public String vehicleReviewCum;
    public String vehicleSeats;
    public String vehicleTransmission;
    public String vehicleYear;
    public String vehicleId;
    public String vehicleDoors;

    public Vehicle(){

    }

    public Vehicle(String vehicleName, String vehicleRate) {
        this.vehicleName = vehicleName;
        this.vehicleRate = vehicleRate;
    }

    public Vehicle(String username, String vehicleConfig, String vehicleDescription, String vehicleName, String vehiclePicture, String vehicleRate, String vehicleRateDay, String vehicleRateMonth, String vehicleRateWeek, String vehicleReview, String vehicleReviewCum, String vehicleSeats, String vehicleTransmission, String vehicleYear, String vehicleId, String vehicleDoors) {
        this.username = username;
        this.vehicleConfig = vehicleConfig;
        this.vehicleDescription = vehicleDescription;
        this.vehicleName = vehicleName;
        this.vehiclePicture = vehiclePicture;
        this.vehicleRate = vehicleRate;
        this.vehicleRateDay = vehicleRateDay;
        this.vehicleRateMonth = vehicleRateMonth;
        this.vehicleRateWeek = vehicleRateWeek;
        this.vehicleReview = vehicleReview;
        this.vehicleReviewCum = vehicleReviewCum;
        this.vehicleSeats = vehicleSeats;
        this.vehicleTransmission = vehicleTransmission;
        this.vehicleYear = vehicleYear;
        this.vehicleId = vehicleId;
        this.vehicleDoors = vehicleDoors;
    }

    public String getVehicleConfig() {
        return vehicleConfig;
    }

    public void setVehicleConfig(String vehicleConfig) {
        this.vehicleConfig = vehicleConfig;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehiclePicture() {
        return vehiclePicture;
    }

    public void setVehiclePicture(String vehiclePicture) {
        this.vehiclePicture = vehiclePicture;
    }

    public String getVehicleRate() {
        return vehicleRate;
    }

    public void setVehicleRate(String vehicleRate) {
        this.vehicleRate = vehicleRate;
    }

    public String getVehicleRateDay() {
        return vehicleRateDay;
    }

    public void setVehicleRateDay(String vehicleRateDay) {
        this.vehicleRateDay = vehicleRateDay;
    }

    public String getVehicleRateMonth() {
        return vehicleRateMonth;
    }

    public void setVehicleRateMonth(String vehicleRateMonth) {
        this.vehicleRateMonth = vehicleRateMonth;
    }

    public String getVehicleRateWeek() {
        return vehicleRateWeek;
    }

    public void setVehicleRateWeek(String vehicleRateWeek) {
        this.vehicleRateWeek = vehicleRateWeek;
    }

    public String getVehicleReview() {
        return vehicleReview;
    }

    public void setVehicleReview(String vehicleReview) {
        this.vehicleReview = vehicleReview;
    }

    public String getVehicleReviewCum() {
        return vehicleReviewCum;
    }

    public void setVehicleReviewCum(String vehicleReviewCum) {
        this.vehicleReviewCum = vehicleReviewCum;
    }

    public String getVehicleSeats() {
        return vehicleSeats;
    }

    public void setVehicleSeats(String vehicleSeats) {
        this.vehicleSeats = vehicleSeats;
    }

    public String getVehicleTransmission() {
        return vehicleTransmission;
    }

    public void setVehicleTransmission(String vehicleTransmission) {
        this.vehicleTransmission = vehicleTransmission;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleDoors() {
        return vehicleDoors;
    }

    public void setVehicleDoors(String vehicleDoors) {
        this.vehicleDoors = vehicleDoors;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
