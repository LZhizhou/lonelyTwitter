
package ca.ualberta.cs.lonelytwitter.models;


import java.lang.reflect.Modifier;
import java.util.Date;

public abstract class Moods {
    private Date date;

    Moods(){
        this.date= new Date();

    }
    Moods(Date date){
        this.date= date;

    }

    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


}
