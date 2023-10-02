package com.taskmanagement;

import java.util.Date;

class Task {
    private String title;
    private String description;
    private boolean isComplete;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.isComplete = false;
    }

    public void markComplete() {
        this.isComplete = true;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nDescription: " + description + "\nStatus: " + (isComplete ? "Complete" : "Incomplete");
    }
}
