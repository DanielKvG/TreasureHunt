package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author DanielKvG
 * In this action the user is able to add and remove names
 */

public class AddNames extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText eTNames;
    private Button btn_Add;
    private Button btn_StartGame;
    private ListView LVPlayers;

    public ArrayList<String> names;
    private ArrayAdapter<String> NameAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_names);

        //initialize fields
        eTNames = findViewById(R.id.eTNames);
        btn_Add = findViewById(R.id.btn_Add);
        btn_StartGame = findViewById(R.id.btn_StartGame);
        LVPlayers = findViewById(R.id.LVPlayers);

        //initialize the list for names
        names = NameListAdapter.readData(this);

        //make a new array adapter and connect it to the List view where the players are shown
        NameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        LVPlayers.setAdapter(NameAdapter);

        //create onClickListeners so that an action is performed when clicked
        btn_Add.setOnClickListener(this);
        LVPlayers.setOnItemClickListener(this);
        btn_StartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int NumbPlayers = names.size();
                String Players = names.toString();
                int NumbHide = getIntent().getIntExtra("NumbHide_key", 0);
                List<String> Hiders = names.subList(0, NumbHide);
                List<String> Seekers = names.subList(NumbHide, names.size());
                //String hiders = Players.substring(0, NumbHide);
                //String seekers = Players.substring(NumbHide + 1, 4);
                String hiders = Hiders.toString();
                String seekers = Seekers.toString();

                //make a new intent for going to the next activity
                Intent intent = new Intent(AddNames.this, PlayerRoles.class);

                //add the string for the seekers and hiders to the intent, these can be called with the key in the next activity
                intent.putExtra("seeker_key", seekers);
                intent.putExtra("hider_key", hiders);
                //start the activity
                startActivity(intent);
                }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //when the button "add" clicked
            case R.id.btn_Add:
                //convert the edible text in eTNames to a string
                String NameAdded = eTNames.getText().toString();
                //add the string to the array adapter
                NameAdapter.add(NameAdded);
                //empty the edit text
                eTNames.setText("");

                //add the string to the array list "names"
                NameListAdapter.writeData(names, this);

                //give notification that the name is added
                Toast.makeText(this, "Name Added", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        names.remove(position);
        NameAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
    }

    //remove last added name when button undo is clicked
    public void btn_Undo(View view) {
        if (names.size() > 0) {
            //show in a notification which name is deleted
            Toast.makeText(this, "Removed "+names.get(names.size() - 1), Toast.LENGTH_SHORT).show();
            //remove the name from the list
            names.remove(names.size() - 1);
        }
        //update the name list
        NameAdapter.notifyDataSetChanged();
        return;
    }

    //remove names when the application is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        names.clear();
        NameAdapter.notifyDataSetChanged();
    }

    //start activity "settings" when the button is clicked
    public void btn_GameSettings(View view) {
        startActivity(new Intent(AddNames.this, SettingsPopupActivity.class));
    }
}