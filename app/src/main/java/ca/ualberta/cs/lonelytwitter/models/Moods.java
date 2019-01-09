
package ca.ualberta.cs.lonelytwitter.models;


import java.lang.reflect.Modifier;
import java.util.Date;

public abstract class Moods {
    private String mood;
    private Date date;

    Moods(){
        this.date= new Date();

    }

    public Date getDate() {
        return date;
    }

    public String getMood() {
        return mood;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
