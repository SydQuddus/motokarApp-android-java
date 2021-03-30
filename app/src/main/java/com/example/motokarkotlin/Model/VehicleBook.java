package com.example.motokarkotlin.Model;

public class VehicleBook {

    public String bookId, bookStatus, endDate, startDate, totalPrice, username, vehicleId, message;

    public VehicleBook() {
    }

    public VehicleBook(String bookId, String bookStatus, String endDate, String startDate, String totalPrice, String username, String vehicleId, String message) {
        this.bookId = bookId;
        this.bookStatus = bookStatus;
        this.endDate = endDate;
        this.startDate = startDate;
        this.totalPrice = totalPrice;
        this.username = username;
        this.vehicleId = vehicleId;
        this.message = message;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
