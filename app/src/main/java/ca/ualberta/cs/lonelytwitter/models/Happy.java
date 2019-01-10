package ca.ualberta.cs.lonelytwitter.models;

import java.util.Date;

public class Happy extends Moods {

    private String mood;

    Happy(){
        super();
        this.mood = "happy";
    }
    Happy(Date date){
        super(date);
        this.mood = "happy";
    }

    public String getMood() {
        return mood;
    }

}
