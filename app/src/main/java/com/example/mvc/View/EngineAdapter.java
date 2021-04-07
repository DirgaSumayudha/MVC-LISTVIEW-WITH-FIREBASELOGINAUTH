package com.example.mvc.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvc.Model.GameEngine;
import com.example.mvc.R;

import java.util.ArrayList;

public class    EngineAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<GameEngine> gameEngines;

    void setGameEngines(ArrayList<GameEngine> gameEngines) {
        this.gameEngines = gameEngines;
    }

    EngineAdapter(Context context) {
        this.context = context;
        gameEngines = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return gameEngines.size();
    }

    @Override
    public Object getItem(int i) {
        return gameEngines.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_engine,viewGroup,false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        GameEngine gameEngine = (GameEngine) getItem(i);
        viewHolder.bind(gameEngine);
        return view;
    }
    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;

        ViewHolder(View view){
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            imgPhoto = view.findViewById(R.id.img_photo);
        }
        void bind(GameEngine gameEngine){
            txtName.setText(gameEngine.getName());
            txtDescription.setText(gameEngine.getDescription());
            imgPhoto.setImageResource(gameEngine.getPhoto());
        }
    }

}
