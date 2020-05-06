package com.pearamone.didyouknow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ColorWheel colorWheel = new ColorWheel();
    private TextView factTextView;
    private TextView textViewId;
    private TextView textViewTableName;
    ImageView tableImageView;
    AdView adView;
    ImageView shareImageView;
    ImageView favImageView;
    float x1,x2,y1,y2;
    int i = 1;
    int adCounter = 0;
    private RelativeLayout relativeLayout;
    String fact;
    ArrayList<Fact> favList = new ArrayList<>();
    private InterstitialAd mInterstitial;
    AdRequest interAdRequest;
    int favId;
    String favTable;
    Typeface typeface;

    private DatabaseFacts db;
    GradientDrawable shape;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseFacts(this);
        db.copyDbIfNotExists();
        shape =  new GradientDrawable();
        shape.setCornerRadius(40);

//        MobileAds.initialize(this, "ca-app-pub-2469721886989416~7390658870");
//        adView = findViewById(R.id.adView);
//        AdRequest bannerAdRequest = new AdRequest.Builder().build();
//        adView.loadAd(bannerAdRequest);

        interAdRequest = new AdRequest.Builder().build();
        mInterstitial = new InterstitialAd(this);
        mInterstitial.setAdUnitId("ca-app-pub-2469721886989416/9441000894");
        mInterstitial.loadAd(interAdRequest);

        factTextView = findViewById(R.id.factTextView);
        relativeLayout = findViewById(R.id.container_main);
        textViewId = findViewById(R.id.activity_main_text_view_id);
        favImageView = findViewById(R.id.activity_main_fav_button);
        shareImageView = findViewById(R.id.activity_main_image_view);
        textViewTableName = findViewById(R.id.activity_main_did_u_know);
        tableImageView = findViewById(R.id.img_user);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            typeface = getResources().getFont(R.font.sourceserifproregular);
        }
        factTextView.setTypeface(typeface);
        Intent intent  = getIntent();
        final int choice = intent.getIntExtra("choice",0);

        switch (choice) {
            case 1:
                tableImageView.setImageResource(R.drawable.facts_circle);
                textViewTableName.setText("General Facts");
                factTextView.setText(db.getFact((readFromShared("defaultKey") - 1), DatabaseFacts.TABLE_GENERAL_NAME).getFact());
                textViewId.setText("Fact " + (readFromShared("defaultKey") - 1) + " of " + db.getFactsCount(DatabaseFacts.TABLE_GENERAL_NAME));

                if (db.getFact((readFromShared("defaultKey") - 1), DatabaseFacts.TABLE_GENERAL_NAME).isFavorite() == 0) {
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener((v, event) -> {
                    onSwipeMethod(event, "defaultKey", DatabaseFacts.TABLE_GENERAL_NAME);
                    showAd();
                    return true;
                });

                favImageView.setOnClickListener(v -> handleFavorites("defaultKey", DatabaseFacts.TABLE_GENERAL_NAME));
                break;

            case 2:
                tableImageView.setImageResource(R.drawable.animal_circle);
                textViewTableName.setText("Animal Facts");
                factTextView.setText(db.getFact((readFromShared("animalKey") - 1), DatabaseFacts.TABLE_ANIMAL_NAME).getFact());
                textViewId.setText("Fact " + (readFromShared("animalKey") - 1) + " of " + db.getFactsCount(DatabaseFacts.TABLE_ANIMAL_NAME));
                if (db.getFact((readFromShared("animalKey") - 1), DatabaseFacts.TABLE_ANIMAL_NAME).isFavorite() == 0) {
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener((v, event) -> {
                    showAd();
                    onSwipeMethod(event, "animalKey", DatabaseFacts.TABLE_ANIMAL_NAME);
                    return true;
                });

                favImageView.setOnClickListener(v -> handleFavorites("animalKey", DatabaseFacts.TABLE_ANIMAL_NAME));


                break;

            case 3:
                tableImageView.setImageResource(R.drawable.computer_circle);
                textViewTableName.setText("Computer Facts");
                factTextView.setText(db.getFact((readFromShared("computerKey") - 1), DatabaseFacts.TABLE_COMPUTER_NAME).getFact());
                textViewId.setText("Fact " + (readFromShared("computerKey") - 1) + " of " + db.getFactsCount(DatabaseFacts.TABLE_COMPUTER_NAME));

                if (db.getFact((readFromShared("computerKey") - 1), DatabaseFacts.TABLE_COMPUTER_NAME).isFavorite() == 0) {
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener((v, event) -> {
                    showAd();
                    onSwipeMethod(event, "computerKey", DatabaseFacts.TABLE_COMPUTER_NAME);
                    return true;
                });

                favImageView.setOnClickListener(v -> handleFavorites("computerKey", DatabaseFacts.TABLE_COMPUTER_NAME));
                break;

            case 4:
                tableImageView.setImageResource(R.drawable.country_circle);
                textViewTableName.setText("Countries Facts");
                factTextView.setText(db.getFact((readFromShared("countriesKey") - 1), DatabaseFacts.TABLE_COUNTRIES_NAME).getFact());
                textViewId.setText("Fact " + (readFromShared("countriesKey") - 1) + " of " + db.getFactsCount(DatabaseFacts.TABLE_COUNTRIES_NAME));

                if (db.getFact((readFromShared("countriesKey") - 1), DatabaseFacts.TABLE_COUNTRIES_NAME).isFavorite() == 0) {
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener((v, event) -> {
                    showAd();
                    onSwipeMethod(event, "countriesKey", DatabaseFacts.TABLE_COUNTRIES_NAME);
                    return true;
                });

                favImageView.setOnClickListener(v -> handleFavorites("countriesKey", DatabaseFacts.TABLE_COUNTRIES_NAME));
                break;

            case 5:
                tableImageView.setImageResource(R.drawable.food_circle);
                textViewTableName.setText("Food Facts");
                factTextView.setText(db.getFact((readFromShared("foodKey") - 1), DatabaseFacts.TABLE_FOOD_NAME).getFact());
                textViewId.setText("Fact " + (readFromShared("foodKey") - 1) + " of " + db.getFactsCount(DatabaseFacts.TABLE_FOOD_NAME));

                if (db.getFact((readFromShared("foodKey") - 1), DatabaseFacts.TABLE_FOOD_NAME).isFavorite() == 0) {
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener((v, event) -> {
                    showAd();
                    onSwipeMethod(event, "foodKey", DatabaseFacts.TABLE_FOOD_NAME);
                    return true;
                });


                favImageView.setOnClickListener(v -> handleFavorites("foodKey", DatabaseFacts.TABLE_FOOD_NAME));
                break;

            case 6:
                tableImageView.setImageResource(R.drawable.humanbody_circle);
                textViewTableName.setText("Human Body Facts");
                factTextView.setText(db.getFact((readFromShared("humanBodyKey") - 1), DatabaseFacts.TABLE_HUMANBODY_NAME).getFact());
                textViewId.setText("Fact " + (readFromShared("humanBodyKey") - 1) + " of " + db.getFactsCount(DatabaseFacts.TABLE_HUMANBODY_NAME));
                if (db.getFact((readFromShared("humanBodyKey") - 1), DatabaseFacts.TABLE_HUMANBODY_NAME).isFavorite() == 0) {
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener((v, event) -> {
                    showAd();
                    onSwipeMethod(event, "humanBodyKey", DatabaseFacts.TABLE_HUMANBODY_NAME);
                    return true;
                });

                favImageView.setOnClickListener(v -> handleFavorites("humanBodyKey", DatabaseFacts.TABLE_HUMANBODY_NAME));
                break;

            case 7:
                tableImageView.setImageResource(R.drawable.people_circle);
                textViewTableName.setText("People Facts");
                factTextView.setText(db.getFact((readFromShared("peopleKey") - 1), DatabaseFacts.TABLE_PEOPLE_NAME).getFact());
                textViewId.setText("Fact " + (readFromShared("peopleKey") - 1) + " of " + db.getFactsCount(DatabaseFacts.TABLE_PEOPLE_NAME));
                if (db.getFact((readFromShared("peopleKey") - 1), DatabaseFacts.TABLE_PEOPLE_NAME).isFavorite() == 0) {
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener((v, event) -> {
                    showAd();
                    onSwipeMethod(event, "peopleKey", DatabaseFacts.TABLE_PEOPLE_NAME);
                    return true;
                });

                favImageView.setOnClickListener(v -> handleFavorites("peopleKey", DatabaseFacts.TABLE_PEOPLE_NAME));
                break;

            case 8:
                tableImageView.setImageResource(R.drawable.psychology_circle);
                textViewTableName.setText("Psychology Facts");
                factTextView.setText(db.getFact((readFromShared("psychologyKey") - 1), DatabaseFacts.TABLE_PSYCHOLOGY_NAME).getFact());
                textViewId.setText("Fact " + (readFromShared("psychologyKey") - 1) + " of " + db.getFactsCount(DatabaseFacts.TABLE_PSYCHOLOGY_NAME));
                if (db.getFact((readFromShared("psychologyKey") - 1), DatabaseFacts.TABLE_PSYCHOLOGY_NAME).isFavorite() == 0) {
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener((v, event) -> {
                    showAd();
                    onSwipeMethod(event, "psychologyKey", DatabaseFacts.TABLE_PSYCHOLOGY_NAME);
                    return true;
                });
                favImageView.setOnClickListener(v -> handleFavorites("psychologyKey", DatabaseFacts.TABLE_PSYCHOLOGY_NAME));
                break;

            case 9:
                tableImageView.setImageResource(R.drawable.chemistry_circle);
                textViewTableName.setText("Science Facts");
                factTextView.setText(db.getFact((readFromShared("scienceKey") - 1), DatabaseFacts.TABLE_SCIENCE_NAME).getFact());
                textViewId.setText("Fact " + (readFromShared("scienceKey") - 1) + " of " + db.getFactsCount(DatabaseFacts.TABLE_SCIENCE_NAME));

                if (db.getFact((readFromShared("scienceKey") - 1), DatabaseFacts.TABLE_SCIENCE_NAME).isFavorite() == 0) {
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener((v, event) -> {
                    showAd();
                    onSwipeMethod(event, "scienceKey", DatabaseFacts.TABLE_SCIENCE_NAME);
                    return true;
                });

                favImageView.setOnClickListener(v -> handleFavorites("scienceKey", DatabaseFacts.TABLE_SCIENCE_NAME));
                break;

            case 10:
                tableImageView.setImageResource(R.drawable.universe_circle);
                textViewTableName.setText("Universe Facts");
                factTextView.setText(db.getFact((readFromShared("universeKey") - 1), DatabaseFacts.TABLE_UNIVERSE_NAME).getFact());
                textViewId.setText("Fact " + (readFromShared("universeKey") - 1) + " of " + db.getFactsCount(DatabaseFacts.TABLE_UNIVERSE_NAME));
                if (db.getFact((readFromShared("universeKey") - 1), DatabaseFacts.TABLE_UNIVERSE_NAME).isFavorite() == 0) {
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener((v, event) -> {
                    showAd();
                    onSwipeMethod(event, "universeKey", DatabaseFacts.TABLE_UNIVERSE_NAME);
                    return true;
                });
                favImageView.setOnClickListener(v -> handleFavorites("universeKey", DatabaseFacts.TABLE_UNIVERSE_NAME));
                break;

            case 11:
                tableImageView.setImageResource(R.drawable.weather_circle);
                textViewTableName.setText("Weather Facts");
                factTextView.setText(db.getFact((readFromShared("weatherKey") - 1), DatabaseFacts.TABLE_WEATHER_NAME).getFact());
                textViewId.setText("Fact " + (readFromShared("weatherKey") - 1) + " of " + db.getFactsCount(DatabaseFacts.TABLE_WEATHER_NAME));
                if (db.getFact((readFromShared("weatherKey") - 1), DatabaseFacts.TABLE_WEATHER_NAME).isFavorite() == 0) {
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener((v, event) -> {
                    showAd();
                    onSwipeMethod(event, "weatherKey", DatabaseFacts.TABLE_WEATHER_NAME);
                    return true;
                });
                favImageView.setOnClickListener(v -> handleFavorites("weatherKey", DatabaseFacts.TABLE_WEATHER_NAME));
                break;

            case 12:
                tableImageView.setImageResource(R.drawable.lifehacks_circle);
                textViewTableName.setText("Life Hacks");
                factTextView.setText(db.getFact((readFromShared("HacksKey") - 1), DatabaseFacts.TABLE_HACKS_NAME).getFact());
                textViewId.setText("Fact " + (readFromShared("HacksKey") - 1) + " of " + db.getFactsCount(DatabaseFacts.TABLE_HACKS_NAME));
                if (db.getFact((readFromShared("HacksKey") - 1), DatabaseFacts.TABLE_HACKS_NAME).isFavorite() == 0) {
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener((v, event) -> {
                    showAd();
                    onSwipeMethod(event, "HacksKey", DatabaseFacts.TABLE_HACKS_NAME);
                    return true;
                });
                favImageView.setOnClickListener(v -> handleFavorites("HacksKey", DatabaseFacts.TABLE_HACKS_NAME));
                break;
        }


        shareImageView.setOnClickListener(v -> {
            Intent intent1 = new Intent(Intent.ACTION_SEND);
            intent1.setType("text/plain");
            String shareBody = factTextView.getText().toString();
            String shareSub = "Another fact!";
            intent1.putExtra(intent1.EXTRA_TEXT,shareBody);
            intent1.putExtra(intent1.EXTRA_SUBJECT,shareSub);
            startActivity(intent1.createChooser(intent1,"Share your fact."));
        });

    }

    private void createShared(int counter, String key){
        SharedPreferences preferences = getSharedPreferences("MyShared", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
                counter+=1;
                editor.putInt(key, counter);
                editor.commit();
        }


    private int readFromShared(String key) {
        SharedPreferences preferences = getSharedPreferences("MyShared", MODE_PRIVATE);
        i = preferences.getInt(key , 2);
        return i;
    }


    void onSwipeMethod(MotionEvent event, String key, String tableName){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                if (x1 < x2) {//LEFT
                    readFromShared(key);
                    if (i > 2) {
                        i -= 2;

                        fact = db.getFact(i, tableName).getFact();
                        factTextView.setText(fact);
                        int color = colorWheel.getColor();
                        shape.setColor(color);
                        relativeLayout.setBackground(shape);
                        textViewId.setText("Fact " + i + " of " + db.getFactsCount(tableName));
                        if(db.getFact(i, tableName).isFavorite() == 0){
                            favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        }else {
                            favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                        }
                        createShared(i, key);
                    } else i = 1;
                } else if (x2 < x1) { //RIGHT
                    readFromShared(key);
                    if (i <= db.getFactsCount(tableName)) {
                        fact = db.getFact(i, tableName).getFact();
                        factTextView.setText(fact);
                        int color = colorWheel.getColor();
                        shape.setColor(color);
                        relativeLayout.setBackground(shape);
                        textViewId.setText("Fact " + i + " of " + db.getFactsCount(tableName));
                        if(db.getFact(i, tableName).isFavorite() == 0){
                            favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        }else {
                            favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                        }
                        createShared(i, key);
                        i++;
                    }
                }else if (x2 == x1) {
                    readFromShared(key);
                    if (i <= db.getFactsCount(tableName)) {
                        fact = db.getFact(i, tableName).getFact();
                        factTextView.setText(fact);
                        int color = colorWheel.getColor();
                        shape.setColor(color);
                        relativeLayout.setBackground(shape);
                        textViewId.setText("Fact " + i + " of " + db.getFactsCount(tableName));
                        if (db.getFact(i, tableName).isFavorite() == 0) {
                            favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        } else {
                            favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                        }
                        createShared(i, key);
                        i++;
                    } else i = db.getFactsCount(tableName);
                }
                break;
        }
    }

    void handleFavorites(String key, String tableName){
        int y = readFromShared(key)-1;
        if(db.getFact(y, tableName).isFavorite() == 0){
            favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
            db.updateFact(y,true,tableName);
        }else {
            favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            db.updateFact(y,false,tableName);
        }

        db.close();
    }

//    void onSwipeFavMethod(MotionEvent event,ArrayList<Fact> factArrayList, String key) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                x1 = event.getX();
//                y1 = event.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = event.getX();
//                y2 = event.getY();
//                if (x1 < x2) {//LEFT
//                    readFromShared(key);
//                    if (i >= 2) {
//                        i -= 2;
//                        fact = factArrayList.get(i).getFact();
//                        factTextView.setText(fact);
//                        int color = colorWheel.getColor();
//                        shape.setColor(color);
//                        relativeLayout.setBackground(shape);
//                        textViewId.setText("Fact " + (i+1) + " of " + factArrayList.size());
//                        createShared(i, key);
//                    } else i = 0;
//                } else if (x2 < x1) { //RIGHT
//                    readFromShared(key);
//                    if (i < factArrayList.size()) {
//                        fact = factArrayList.get(i).getFact();
//                        factTextView.setText(fact);
//                        int color = colorWheel.getColor();
//                        shape.setColor(color);
//                        relativeLayout.setBackground(shape);
//                        textViewId.setText("Fact " + (i+1) + " of " + factArrayList.size());
//                        createShared(i, key);
//                        i++;
//                    }else i=(factArrayList.size()-1);
//                } else if (x2 == x1) {
//                    readFromShared(key);
//                    if (i < factArrayList.size()) {
//                    fact = factArrayList.get(i).getFact();
//                    factTextView.setText(fact);
//                    int color = colorWheel.getColor();
//                    shape.setColor(color);
//                    relativeLayout.setBackground(shape);
//                    textViewId.setText("Fact " + (i+1) + " of " + factArrayList.size());
//                    createShared(i, key);
//                    i++;
//                    }else i=(factArrayList.size()-1);
//                }
//                break;
//        }
//    }

    private void showAd(){
        if(adCounter <= 39){
            adCounter++;
        }else{
            mInterstitial.loadAd(interAdRequest);
            if(mInterstitial.isLoaded()) {
                adCounter = 0;
                mInterstitial.show();
            }
        }
    }
    }
