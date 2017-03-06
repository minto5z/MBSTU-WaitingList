package com.blogspot.larn4android.materialtabs.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by minthu on 1/15/2017.
 */

public class cse {
    private String applicants_name,fathers_name,hall_name,migration,applicants_image;

    int serial_no,merit_position,ad_exam_roll_no;

    public cse(String applicants_name, String fathers_name, String hall_name,String migration,String applicants_image,int serial_no,int merit_position,int ad_exam_roll_no) {
        this.applicants_name = applicants_name;
        this.fathers_name = fathers_name;
        this.hall_name = hall_name;
        this.migration = migration;
        this.applicants_image = applicants_image;
        this.serial_no = serial_no;
        this.merit_position = merit_position;
        this.ad_exam_roll_no = ad_exam_roll_no;
    }

    public cse() {
    }

    public String getapplicants_name() {
        return applicants_name;
    }

    public String getMigration() {
        return migration;
    }

    public void setMigration(String migration) {
        this.migration = migration;
    }

    public String getApplicants_image() {
        return applicants_image;
    }

    public void setApplicants_image(String applicants_image) {
        this.applicants_image = applicants_image;
    }

    public int getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(int serial_no) {
        this.serial_no = serial_no;
    }

    public int getMerit_position() {
        return merit_position;
    }

    public void setMerit_position(int merit_position) {
        this.merit_position = merit_position;
    }

    public int getAd_exam_roll_no() {
        return ad_exam_roll_no;
    }

    public void setAd_exam_roll_no(int ad_exam_roll_no) {
        this.ad_exam_roll_no = ad_exam_roll_no;
    }

    public void setapplicants_name(String applicants_name) {
        this.applicants_name = applicants_name;
    }

    public String getfathers_name() {
        return fathers_name;
    }

    public void setfathers_name(String fathers_name) {
        this.fathers_name = fathers_name;
    }

    public String gethall_name() {
        return hall_name;
    }

    public void sethall_name(String hall_name) {
        this.hall_name = hall_name;
    }
}
