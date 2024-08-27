package com.surveydb.Survey.domain.entity;

import java.sql.Timestamp;

public class Survey {
    private int id;
    private Timestamp created_at;
    private Timestamp updated_at;
    private String description;
    private String name;

    public Survey() {
    }

    public Survey(int id, Timestamp created_at, Timestamp updated_at, String description, String name) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.description = description;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
