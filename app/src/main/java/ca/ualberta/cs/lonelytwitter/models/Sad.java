package ca.ualberta.cs.lonelytwitter.models;

import java.util.Date;

public class Sad extends Moods {

    private String mood;

    Sad(){
        super();
        this.mood = "sad";
    }
    Sad(Date date){
        super(date);
        this.mood = "sad";
    }

    public String getMood() {
        return mood;
    }

}