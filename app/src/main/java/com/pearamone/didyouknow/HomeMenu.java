package com.pearamone.didyouknow;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeMenu extends AppCompatActivity {
    Button generalButton;
    Button animalButton;
    Button computerButton;
    Button countriesButton;
    Button foodButton;
    Button humanBodyButton;
    Button peopleButton;
    Button psychologyButton;
    Button scienceButton;
    Button universeButton;
    Button weatherButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        generalButton = findViewById(R.id.button_general);
        generalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice", 1);
                startActivity(intent);
            }
        });

        animalButton = findViewById(R.id.button_animals);
        animalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice",2);
                startActivity(intent);
            }
        });

        computerButton = findViewById(R.id.button_computer);
        computerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice",3);
                startActivity(intent);
            }
        });

        countriesButton = findViewById(R.id.button_countries);
        countriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice",4);
                startActivity(intent);
            }
        });

        foodButton = findViewById(R.id.button_food);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice",5);
                startActivity(intent);
            }
        });

        humanBodyButton = findViewById(R.id.button_human_body);
        humanBodyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice",6);
                startActivity(intent);
            }
        });

        peopleButton = findViewById(R.id.button_people);
        peopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice",7);
                startActivity(intent);
            }
        });

        psychologyButton = findViewById(R.id.button_psychology);
        psychologyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice",8);
                startActivity(intent);
            }
        });

        scienceButton = findViewById(R.id.button_science);
        scienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice",9);
                startActivity(intent);
            }
        });

        universeButton = findViewById(R.id.button_universe);
        universeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice",10);
                startActivity(intent);
            }
        });

        weatherButton = findViewById(R.id.button_weather);
        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice",11);
                startActivity(intent);
            }
        });
    }

}
