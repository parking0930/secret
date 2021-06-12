package com.inhatc.secret;

public class SchoolVO {
    String id;
    String school;
    String latitude;
    String longtitude;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongtitude() {
        return longtitude;
    }
    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }
    @Override
    public String toString() {
        return "SchoolVO [id=" + id + ", school=" + school + ", latitude=" + latitude + ", longtitude=" + longtitude
                + "]";
    }
}