
/*
 * Developed by Zhizhou Lu on $Today.
 * Last modified 1/23/19 10:08 PM.
 * Copyright (c) 2019. All right reserved.
 */

package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class LonelyTwitterActivity extends Activity {

    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    private ListView oldTweetsList;
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    /**
     * Called when the activity is first created.
     * @see     Activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("record");




        bodyText = (EditText) findViewById(R.id.body);
        Button saveButton = (Button) findViewById(R.id.save);
        oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();
                saveToCloud(text);
                saveInFile(text, new Date(System.currentTimeMillis()));
                refresh();
                bodyText.getText().clear();

            }
        });
        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clearFile(FILENAME);
                refresh();
            }
        });

    }
    /**
     * Called when the activity is Started.
     * @see     Activity
     */
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        ArrayList<String> tweets = loadFromFile();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, tweets);
        oldTweetsList.setAdapter(adapter);
    }
    /**
     * load the text in file file.sav
     * @return the lines of the texts in file.sav
     */
    private ArrayList<String> loadFromFile() {
        ArrayList<String> tweets = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                tweets.add(line);
                line = in.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tweets;
    }
/**
     * load a string of  text in file file.sav
     * @param text: the string what to save
     * @param date: the time of saving
     */
    private void saveInFile(String text, Date date) {


        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_APPEND);
            fos.write(new String(date.toString() + " | " + text)
                    .getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    /**
     * clear the text in file
     * @param filename: the name of file want to clear
     */
    private void saveToCloud(String text){
        Tweet tweet = new Tweet(text);
        myRef = database.getReference(tweet.getId());
        myRef.child("tweet").setValue(tweet.getMessage());

        myRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String tweet = (String) dataSnapshot.child("tweet").getValue();
                if (tweet.substring(0,1).equals("U")) {
                    tweet = tweet.substring(1).toUpperCase();
                } else if (tweet.substring(0,1).equals("L")) {
                    tweet = tweet.substring(1).toLowerCase();
                }
                myRef.child("tweet").setValue(tweet);
            }

            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.");
            }
        });



    }
    private void clearFile(String filename) {
        File dir = getFilesDir();
        File file = new File(dir, filename);
        boolean deleted = file.delete();
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_APPEND);
            fos.write("".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * refresh the listview
     */
    private void refresh() {
        ArrayList<String> tweets = loadFromFile();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, tweets);
        oldTweetsList.setAdapter(adapter);
    }
}