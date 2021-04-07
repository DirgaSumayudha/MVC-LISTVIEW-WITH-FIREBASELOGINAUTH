package com.example.mvc.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvc.R;

public class afterlogin3 extends AppCompatActivity {

    ImageView imageViewInfoEngine;
    TextView textViewNamaEngine, textViewInfoEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin3);
        imageViewInfoEngine = findViewById(R.id.imageViewFotoEngine);
        textViewNamaEngine = findViewById(R.id.textViewNamaEngine);
        textViewInfoEngine = findViewById(R.id.textViewInfoEngine);

        getIncomingExtra();
    }

    private void getIncomingExtra(){
        if(getIntent().hasExtra("photo") && getIntent().hasExtra("judul") && getIntent().hasExtra("description")){
            int photoengine = getIntent().getIntExtra("photo", 0);
            String judulengine = getIntent().getStringExtra("judul");
            String descriptions = getIntent().getStringExtra("description");

            setDataActivity(photoengine, judulengine, descriptions);
        }
    }

    private void setDataActivity(int photoengine, String judulengine, String descriptions){
        Glide.with(this).asBitmap().load(photoengine).into(imageViewInfoEngine);
        textViewNamaEngine.setText(judulengine);
        textViewInfoEngine.setText(descriptions);
    }
}