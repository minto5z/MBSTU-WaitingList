package com.blogspot.larn4android.materialtabs.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by minthu on 1/20/2017.
 */

public class Choose implements Parcelable {

    String applicant_name,father_name;
    int merit_position,ad_exam_roll;

    public Choose(String applicant_name, String father_name, int merit_position, int ad_exam_roll) {
        this.applicant_name = applicant_name;
        this.father_name = father_name;
        this.merit_position = merit_position;
        this.ad_exam_roll = ad_exam_roll;
    }

    public Choose() {
    }
    public Choose(Parcel in) {

        this.applicant_name = in.readString();
        this.father_name = in.readString();
        in.readInt();
        in.readInt();
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


    public int getMerit_position() {
        return merit_position;
    }

    public void setMerit_position(int merit_position) {
        this.merit_position = merit_position;
    }

    public int getAd_exam_roll() {
        return ad_exam_roll;
    }

    public void setAd_exam_roll(int ad_exam_roll) {
        this.ad_exam_roll = ad_exam_roll;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.applicant_name);
        dest.writeString(this.father_name);
        dest.writeInt(this.merit_position);
        dest.writeInt(this.ad_exam_roll);
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
