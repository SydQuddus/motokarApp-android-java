package com.example.motokarkotlin.Model;

public class Vehicle {

    public String vehicleName;
    public String vehicleRate;

    public Vehicle() {
    }

    public Vehicle(String vehicleName, String vehicleRate) {
        this.vehicleName = vehicleName;
        this.vehicleRate = vehicleRate;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleRate() {
        return vehicleRate;
    }

    public void setVehicleRate(String vehicleRate) {
        this.vehicleRate = vehicleRate;
    }
}
