package ca.ualberta.cs.lonelytwitter;


import java.util.Date;

/**
 *
 * @author zhizhou
 * @version 1.0
 * @see LonelyTwitterActivity
 */
public class Tweet {

    String message;
    Date date;

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
}