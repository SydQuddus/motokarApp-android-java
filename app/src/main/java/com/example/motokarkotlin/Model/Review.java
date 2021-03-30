package com.example.motokarkotlin.Model;

public class Review {

    String reviewId;
    String reviewContent;
    String reviewRate;
    String username;
    String vehicleId;

    public Review() {
    }

    public Review(String reviewId, String reviewContent, String reviewRate, String username, String vehicleId) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.reviewRate = reviewRate;
        this.username = username;
        this.vehicleId = vehicleId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(String reviewRate) {
        this.reviewRate = reviewRate;
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
}
