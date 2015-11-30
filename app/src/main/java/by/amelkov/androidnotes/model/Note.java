package by.amelkov.androidnotes.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Serializable{
    int id;
    String text;
    Date timeCreate;

    public Note(String text) {
        this.text = text;
        timeCreate = new Date();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public String getTime() {
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM hh:mm");
        return ft.format(timeCreate);
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public void setTimeCreate() {
        this.timeCreate = new Date();
    }

    @Override
    public String toString() {
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM hh:mm");
        return ft.format(timeCreate) + ": " + text;
    }
}
