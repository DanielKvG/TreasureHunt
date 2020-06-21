package com.example.treasurehunt;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NameListAdapter {

    public static final String PLAYERNAME = "Listinfo.dat";

    public static void writeData(ArrayList<String> names, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(PLAYERNAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(names);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<String> readData(Context context){
        ArrayList<String> LVPlayers = null;
        try {
            FileInputStream fis = context.openFileInput(PLAYERNAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            LVPlayers = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {

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
