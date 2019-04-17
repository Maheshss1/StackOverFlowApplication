package com.example.mahesh.stackoverflowapplication.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Questions implements Parcelable{

    @PrimaryKey
    @SerializedName("question_id")
    @NonNull
    private String questionId;
    private String link;
    private String title;

    public Questions() {
    }

    protected Questions(Parcel in) {
        questionId = in.readString();
        link = in.readString();
        title = in.readString();
        List list = new ArrayList<Questions>();
        in.readList(list, Questions.class.getClassLoader());

    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "questionId='" + questionId + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(questionId);
        parcel.writeString(link);
        parcel.writeString(title);
    }
}
