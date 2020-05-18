package com.atikur.amaderdoctor;

public class Doctor_List {
    private String appId;
    private String designation;
    private String district;
    private String docName, degree, specialist_on, mobile, email, chamber, division;
    private int id;

    public Doctor_List(int id, String docName, String degree, String designation, String specialist_on, String mobile, String email, String chamber, String division, String district, String appId) {
        this.id = id;
        this.docName = docName;
        this.degree = degree;
        this.designation = designation;
        this.specialist_on = specialist_on;
        this.mobile = mobile;
        this.email = email;
        this.chamber = chamber;
        this.division = division;
        this.district = district;
        this.appId = appId;
    }

    public String getDocName() {
        return this.docName;
    }

    public String getDesignation() {
        return this.designation;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getDegree() {
        return degree;
    }

    public String getSpecialist_on() {
        return specialist_on;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getChamber() {
        return chamber;
    }

    public String getDivision() {
        return division;
    }

    public int getId() {
        return id;
    }
}
