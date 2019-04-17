package com.example.mahesh.stackoverflowapplication.model;

import java.util.List;

public class ListOfQuestions {

    private List<Questions> items;

    public List<Questions> getItems() {
        return items;
    }

    public void setItems(List<Questions> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ListOfQuestions{" +
                "items=" + items +
                '}';
    }
}
