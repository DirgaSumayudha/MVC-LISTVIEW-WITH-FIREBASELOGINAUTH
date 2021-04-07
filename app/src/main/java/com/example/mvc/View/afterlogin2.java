package com.example.mvc.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mvc.Model.GameEngine;
import com.example.mvc.R;
import com.example.mvc.View.EngineAdapter;
import com.example.mvc.View.afterlogin3;

import java.util.ArrayList;

public class afterlogin2 extends AppCompatActivity {
    private String[] dataName;
    private String [] dataDescription;
    private TypedArray dataPhoto;
    private EngineAdapter adapter;
    private ArrayList<GameEngine> gameEngines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin2);
        adapter = new EngineAdapter(this);
        ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(adapter);
        prepare();
        addItem();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(afterlogin2.this, "Anda Pilih " + gameEngines.get(i).getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), afterlogin3.class);
                intent.putExtra("photo", dataPhoto.getResourceId(i, -1));
                intent.putExtra("judul", dataName[i]);
                intent.putExtra("description", dataDescription[i]);
                startActivity(intent);
            }
        });
    }


    public void addItem(){
        gameEngines = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++){
            GameEngine gameEngine = new GameEngine();
            gameEngine.setPhoto(dataPhoto.getResourceId(i, -1));
            gameEngine.setName(dataName[i]);
            gameEngine.setDescription(dataDescription[i]);
            gameEngines.add(gameEngine);
        }
        adapter.setGameEngines(gameEngines);
    }

    public void prepare(){
        dataName = getResources().getStringArray(R.array.data_name);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }
}