package ca.ualberta.cs.lonelytwitter;


import android.icu.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author zhizhou
 * @version 1.0
 * @see LonelyTwitterActivity
 */
public class Tweet {

    private UUID id;
    private String message;
    private Calendar time;

    Tweet(){
    }


    /**
     *
     * @param message, string type
     * @since 1.0
     */

//Overloading: so that we can specify the tweet cont
    Tweet(String message){
        this.message = message;
        this.id = UUID.randomUUID();
        this.time = Calendar.getInstance();

    }

    /**
     *
     * @param message, string type
     * @since 1.0
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString(){
        return message;
    }

    public String getTime() {
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
        return dateformat.format(time.getTime());
    }

    public String getId() {
        return id.toString();
    }

}