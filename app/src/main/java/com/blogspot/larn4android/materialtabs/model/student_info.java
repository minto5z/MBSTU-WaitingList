package com.blogspot.larn4android.materialtabs.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by minthu on 1/17/2017.
 */

public class student_info implements Parcelable {


    private String applicant_name,father_name,hall_name,department_name,student_id,serial_no,merit_position,ad_exam_roll_no;

    //int serial_no,merit_position,ad_exam_roll_no;


    public student_info() {
    }

//    public student_info(String applicant_name, String father_name, String hall_name, String department_name, String student_id, int serial_no, int merit_position, int ad_exam_roll_no) {
//        this.applicant_name = applicant_name;
//        this.father_name = father_name;
//        this.hall_name = hall_name;
//        this.department_name = department_name;
//        this.student_id = student_id;
//        this.serial_no = serial_no;
//        this.merit_position = merit_position;
//        this.ad_exam_roll_no = ad_exam_roll_no;
//    }

    public student_info(String applicant_name, String father_name, String hall_name, String department_name, String student_id, String serial_no, String merit_position, String ad_exam_roll_no) {
        this.applicant_name = applicant_name;
        this.father_name = father_name;
        this.hall_name = hall_name;
        this.department_name = department_name;
        this.student_id = student_id;
        this.serial_no = serial_no;
        this.merit_position = merit_position;
        this.ad_exam_roll_no = ad_exam_roll_no;
    }

    public student_info(Parcel in) {



//        in.readInt();
//        in.readInt();
//        in.readInt();


        this.applicant_name = in.readString();
        this.father_name = in.readString();
        this.hall_name = in.readString();
        this.department_name  = in.readString();
        this.student_id  = in.readString();
        this.serial_no = in.readString();
        this.merit_position  = in.readString();
        this.ad_exam_roll_no  = in.readString();
    }

    public String getApplicant_name() {
        return applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getHall_name() {
        return hall_name;
    }

    public void setHall_name(String hall_name) {
        this.hall_name = hall_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getMerit_position() {
        return merit_position;
    }

    public void setMerit_position(String merit_position) {
        this.merit_position = merit_position;
    }

    public String getAd_exam_roll_no() {
        return ad_exam_roll_no;
    }

    public void setAd_exam_roll_no(String ad_exam_roll_no) {
        this.ad_exam_roll_no = ad_exam_roll_no;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


//        dest.writeInt(this.serial_no);
//        dest.writeInt(this.merit_position);
//        dest.writeInt(this.ad_exam_roll_no);
        dest.writeString(this.applicant_name);
        dest.writeString(this.father_name);
        dest.writeString(this.hall_name);
        dest.writeString(this.department_name);
        dest.writeString(this.student_id);
        dest.writeString(this.serial_no);
        dest.writeString(this.merit_position);
        dest.writeString(this.ad_exam_roll_no);


    }
    public static final Creator CREATOR = new Creator() {
        public student_info createFromParcel(Parcel in) {
            return new student_info(in);
        }

        public student_info[] newArray(int size) {
            return new student_info[size];
        }
    };
}
