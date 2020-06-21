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

public class AddNames extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText eTNames;
    private Button btn_Add;
    private Button btn_StartGame;
    private ListView LVPlayers;
    public String Seeker1;
    public String Hiders;

    public ArrayList<String> names;
    private ArrayAdapter<String> NameAdapter;

    SettingsPopupActivity SPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_names);

        eTNames = findViewById(R.id.eTNames);
        btn_Add = findViewById(R.id.btn_Add);
        btn_StartGame = findViewById(R.id.btn_StartGame);
        LVPlayers = findViewById(R.id.LVPlayers);

        names = NameListAdapter.readData(this);


        NameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        LVPlayers.setAdapter(NameAdapter);

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

                Intent intent = new Intent(AddNames.this, PlayerRoles.class);

                intent.putExtra("seeker_key", seekers);
                intent.putExtra("hider_key", hiders);
                startActivity(intent);
                }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Add:
                String NameAdded = eTNames.getText().toString();
                NameAdapter.add(NameAdded);
                eTNames.setText("");

                NameListAdapter.writeData(names, this);

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

    public void btn_Undo(View view) {
        if (names.size() > 0) {
            Toast.makeText(this, "Removed "+names.get(names.size() - 1), Toast.LENGTH_SHORT).show();
            names.remove(names.size() - 1);
        }
        NameAdapter.notifyDataSetChanged();
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        names.clear();
        NameAdapter.notifyDataSetChanged();
    }

    public void btn_GameSettings(View view) {
        startActivity(new Intent(AddNames.this, SettingsPopupActivity.class));
    }
}