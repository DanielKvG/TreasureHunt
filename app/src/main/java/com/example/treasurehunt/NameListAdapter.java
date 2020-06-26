package com.example.treasurehunt;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author Unknown
 * Pre made code for adding names to the listview using the list adapter, comments are made by group 4
 */

public class NameListAdapter {

    public static final String PLAYERNAME = "Listinfo.dat";

    public static void writeData(ArrayList<String> names, Context context) {
        try {
            //add the string for player names to the file output system
            FileOutputStream fos = context.openFileOutput(PLAYERNAME, Context.MODE_PRIVATE);
            //convert the fos to the object output stream
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //write the names in the oos
            oos.writeObject(names);
            oos.close();
        }
        //if during the process an exception has happened, log it.
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<String> readData(Context context){
        ArrayList<String> LVPlayers = null;
        try {
            //add the name to the file input system
            FileInputStream fis = context.openFileInput(PLAYERNAME);
            //convert the fis to object input stream
            ObjectInputStream ois = new ObjectInputStream(fis);
            //make the objects in the ois part of the list view
            LVPlayers = (ArrayList<String>) ois.readObject();
        }
        //if during the process an exception has happened, log it.
        catch (FileNotFoundException e) {
            LVPlayers = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return LVPlayers;

    }



}
