package com.atikur.amaderdoctor;

public class Doctor_List {
    private String appId;
    private String designation;
    private String district;
    private String docName;
    private int id;

    public Doctor_List(int id2, String docName2, String designation2, String district2, String appId2) {
        this.id = id2;
        this.docName = docName2;
        this.designation = designation2;
        this.district = district2;
        this.appId = appId2;
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
}
