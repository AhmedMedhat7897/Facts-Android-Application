package com.pearamone.didyouknow;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeMenu extends AppCompatActivity {
    private DatabaseFacts db;

    CardView generalButton;
    CardView animalButton;
    CardView computerButton;
    CardView countriesButton;
    CardView foodButton;
    CardView humanBodyButton;
    CardView peopleButton;
    CardView psychologyButton;
    CardView scienceButton;
    CardView universeButton;
    CardView weatherButton;
    CardView hacksButton;

    TextView generalTextNumber;
    TextView animalTextNumber;
    TextView computerTextNumber;
    TextView countriesTextNumber;
    TextView foodTextNumber;
    TextView humanBodyTextNumber;
    TextView peopleTextNumber;
    TextView psychologyTextNumber;
    TextView scienceTextNumber;
    TextView universeTextNumber;
    TextView weatherTextNumber;
    TextView hacksTextNumber;

    ImageView countriesLock;
    ImageView foodLock;
    ImageView humanBodyLock;
    ImageView peopleLock;
    ImageView psychologyLock;
    ImageView scienceLock;
    ImageView lifeHacksLock;


    int vipStatus;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        db = new DatabaseFacts(this);
        vipStatus = readFromSharedVIP();

        countriesLock = findViewById(R.id.button_countries_lock);
        foodLock = findViewById(R.id.button_food_lock);
        humanBodyLock = findViewById(R.id.button_human_body_lock);
        peopleLock = findViewById(R.id.button_people_lock);
        psychologyLock = findViewById(R.id.button_psychology_lock);
        scienceLock = findViewById(R.id.button_science_lock);
        lifeHacksLock = findViewById(R.id.button_lifehacks_lock);

        generalTextNumber = findViewById(R.id.button_general_number);
        animalTextNumber = findViewById(R.id.button_animals_number);
        computerTextNumber = findViewById(R.id.button_computer_number);
        countriesTextNumber = findViewById(R.id.button_countries_number);
        foodTextNumber = findViewById(R.id.button_food_number);
        humanBodyTextNumber = findViewById(R.id.button_human_body_number);
        peopleTextNumber = findViewById(R.id.button_people_number);
        psychologyTextNumber = findViewById(R.id.button_psychology_number);
        scienceTextNumber = findViewById(R.id.button_science_number);
        universeTextNumber = findViewById(R.id.button_universe_number);
        weatherTextNumber = findViewById(R.id.button_weather_number);
        hacksTextNumber = findViewById(R.id.button_lifehacks_number);

        generalTextNumber.setText(readFromShared("defaultKey")-1+"/"+db.getFactsCount(DatabaseFacts.TABLE_GENERAL_NAME));
        animalTextNumber.setText(readFromShared("animalKey")-1+"/"+db.getFactsCount(DatabaseFacts.TABLE_ANIMAL_NAME));
        computerTextNumber.setText(readFromShared("computerKey")-1+"/"+db.getFactsCount(DatabaseFacts.TABLE_COMPUTER_NAME));
        countriesTextNumber.setText(readFromShared("countriesKey")-1+"/"+db.getFactsCount(DatabaseFacts.TABLE_COUNTRIES_NAME));
        foodTextNumber.setText(readFromShared("foodKey")-1+"/"+db.getFactsCount(DatabaseFacts.TABLE_FOOD_NAME));
        humanBodyTextNumber.setText(readFromShared("humanBodyKey")-1+"/"+db.getFactsCount(DatabaseFacts.TABLE_HUMANBODY_NAME));
        peopleTextNumber.setText(readFromShared("peopleKey")-1+"/"+db.getFactsCount(DatabaseFacts.TABLE_PEOPLE_NAME));
        psychologyTextNumber.setText(readFromShared("psychologyKey")-1+"/"+db.getFactsCount(DatabaseFacts.TABLE_PSYCHOLOGY_NAME));
        scienceTextNumber.setText(readFromShared("scienceKey")-1+"/"+db.getFactsCount(DatabaseFacts.TABLE_SCIENCE_NAME));
        universeTextNumber.setText(readFromShared("universeKey")-1+"/"+db.getFactsCount(DatabaseFacts.TABLE_UNIVERSE_NAME));
        weatherTextNumber.setText(readFromShared("weatherKey")-1+"/"+db.getFactsCount(DatabaseFacts.TABLE_WEATHER_NAME));
        hacksTextNumber.setText(readFromShared("hacksKey")-1+"/"+db.getFactsCount(DatabaseFacts.TABLE_HACKS_NAME));


        if(vipStatus == 0){
            countriesTextNumber.setVisibility(View.GONE);
        }else{
            countriesLock.setVisibility(View.GONE);
        }

        generalButton = findViewById(R.id.button_general);
        generalButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeMenu.this, MainActivity.class);
            intent.putExtra("choice", 1);
            startActivity(intent);
        });

        animalButton = findViewById(R.id.button_animals);
        animalButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeMenu.this, MainActivity.class);
            intent.putExtra("choice",2);
            startActivity(intent);
        });

        computerButton = findViewById(R.id.button_computer);
        computerButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeMenu.this, MainActivity.class);
            intent.putExtra("choice",3);
            startActivity(intent);
        });

        countriesButton = findViewById(R.id.button_countries);
        if(vipStatus == 0){
            countriesTextNumber.setVisibility(View.GONE);
            countriesButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, PremiumActivity.class);
                startActivity(intent);
            });
        }else{
            countriesLock.setVisibility(View.GONE);
            countriesButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeMenu.this, MainActivity.class);
            intent.putExtra("choice",4);
            startActivity(intent);
        });
        }

        foodButton = findViewById(R.id.button_food);
        if(vipStatus == 0) {
            foodTextNumber.setVisibility(View.GONE);
            foodButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, PremiumActivity.class);
                startActivity(intent);
            });
        }else{
            foodLock.setVisibility(View.GONE);
            foodButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice", 5);
                startActivity(intent);
            });
        }

        humanBodyButton = findViewById(R.id.button_human_body);
        if(vipStatus == 0) {
            humanBodyTextNumber.setVisibility(View.GONE);
            humanBodyButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, PremiumActivity.class);
                startActivity(intent);
            });
        }else{
            humanBodyLock.setVisibility(View.GONE);
            humanBodyButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice", 6);
                startActivity(intent);
            });
        }
        peopleButton = findViewById(R.id.button_people);
        if(vipStatus == 0) {
            peopleTextNumber.setVisibility(View.GONE);
            peopleButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, PremiumActivity.class);
                startActivity(intent);
            });
        }else{
            peopleLock.setVisibility(View.GONE);
            peopleButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice", 7);
                startActivity(intent);
            });
        }

        psychologyButton = findViewById(R.id.button_psychology);
        if(vipStatus == 0) {
            psychologyTextNumber.setVisibility(View.GONE);
            psychologyButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, PremiumActivity.class);
                startActivity(intent);
            });
        }else{
            psychologyLock.setVisibility(View.GONE);
            psychologyButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice", 8);
                startActivity(intent);
            });
        }

        scienceButton = findViewById(R.id.button_science);
        if(vipStatus == 0) {
            scienceTextNumber.setVisibility(View.GONE);
            scienceButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, PremiumActivity.class);
                startActivity(intent);
            });
        }else {
            scienceLock.setVisibility(View.GONE);
            scienceButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice", 9);
                startActivity(intent);
            });
        }

        universeButton = findViewById(R.id.button_universe);
        universeButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeMenu.this, MainActivity.class);
            intent.putExtra("choice",10);
            startActivity(intent);
        });

        weatherButton = findViewById(R.id.button_weather);
        weatherButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeMenu.this, MainActivity.class);
            intent.putExtra("choice",11);
            startActivity(intent);
        });

        hacksButton = findViewById(R.id.button_lifehacks);
        if(vipStatus == 0){
            hacksTextNumber.setVisibility(View.GONE);
            hacksButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, PremiumActivity.class);
                startActivity(intent);
            });
        }else {
            lifeHacksLock.setVisibility(View.GONE);
            hacksButton.setOnClickListener(v -> {
                Intent intent = new Intent(HomeMenu.this, MainActivity.class);
                intent.putExtra("choice", 12);
                startActivity(intent);
            });
        }
    }

    private int readFromShared(String key) {
        SharedPreferences preferences = getSharedPreferences("MyShared", MODE_PRIVATE);
        int i = preferences.getInt(key , 2);
        return i;
    }

    private int readFromSharedVIP() {
        SharedPreferences preferences = getSharedPreferences("MyShared", MODE_PRIVATE);
        return preferences.getInt("VIPStatus", 0);
    }

}
