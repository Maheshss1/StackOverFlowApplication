package com.example.mahesh.stackoverflowapplication.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tags_table")
public class Tags {
    //  "has_synonyms": true,
    //      "is_moderator_only": false,
    //      "is_required": false,
    //      "count": 1793133,
    //      "name": "javascript"


    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("has_synonyms")
    private boolean hasSynonyms;
    @SerializedName("is_moderator_only")
    private boolean isModeratorOnly;
    @SerializedName("is_required")
    private boolean isRequired;

    private int count;
    private String name;
    @Ignore
    private int position;

    public Tags(boolean hasSynonyms, boolean isModeratorOnly, boolean isRequired, int count, String name) {
        this.hasSynonyms = hasSynonyms;
        this.isModeratorOnly = isModeratorOnly;
        this.isRequired = isRequired;
        this.count = count;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isHasSynonyms() {
        return hasSynonyms;
    }

    public void setHasSynonyms(boolean hasSynonyms) {
        this.hasSynonyms = hasSynonyms;
    }

    public boolean isModeratorOnly() {
        return isModeratorOnly;
    }

    public void setModeratorOnly(boolean moderatorOnly) {
        isModeratorOnly = moderatorOnly;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "name='" + name + '\'' +
                '}';
    }
}
