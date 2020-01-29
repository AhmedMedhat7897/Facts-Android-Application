package com.pearamone.didyouknow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ColorWheel colorWheel = new ColorWheel();
    private TextView factTextView;
    private TextView textViewId;
    AdView adView;
    ImageView imageView;
    ImageView menuImageView;
    ImageView favImageView;
    float x1,x2,y1,y2;
    int i = 1;
    int adCounter = 0;
    private RelativeLayout relativeLayout;
    String fact;
    ArrayList<Fact> favList = new ArrayList<>();
    private InterstitialAd mInterstitial;
    AdRequest interAdRequest;


    private DatabaseFacts db;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseFacts(this);
        db.copyDbIfNotExists();


        MobileAds.initialize(this, "ca-app-pub-2469721886989416~7390658870");
        adView = findViewById(R.id.adView);
        AdRequest bannerAdRequest = new AdRequest.Builder().build();
        adView.loadAd(bannerAdRequest);

        interAdRequest = new AdRequest.Builder().build();
        mInterstitial = new InterstitialAd(this);
        mInterstitial.setAdUnitId("ca-app-pub-2469721886989416/9441000894");
        mInterstitial.loadAd(interAdRequest);

        factTextView = findViewById(R.id.factTextView);
        relativeLayout = findViewById(R.id.relativeLayout);
        textViewId = findViewById(R.id.activity_main_text_view_id);
        favImageView = findViewById(R.id.activity_main_fav_button);
        Intent intent  = getIntent();
        final int choice = intent.getIntExtra("choice",0);



        switch (choice){
            case 1:
                factTextView.setText(db.getFact((readFromShared("defaultKey")-1), Fact.TABLE_GENERAL_NAME, Fact.GENERAL_FACT_ID, Fact.GENERAL_FACT_NAME, Fact.GENERAL_FACT_FAV).getFact());
                textViewId.setText("Fact " + (readFromShared("defaultKey")-1) + " of " + db.getFactsCount(Fact.TABLE_GENERAL_NAME));

                if(db.getFact((readFromShared("defaultKey")-1), Fact.TABLE_GENERAL_NAME, Fact.GENERAL_FACT_ID, Fact.GENERAL_FACT_NAME, Fact.GENERAL_FACT_FAV).isFavorite() == 0){
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        onSwipeMethod(event,"defaultKey",Fact.TABLE_GENERAL_NAME, Fact.GENERAL_FACT_ID, Fact.GENERAL_FACT_NAME, Fact.GENERAL_FACT_FAV);
                        showAd();
                        return true;
                }});

                favImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleFavorites("defaultKey",Fact.TABLE_GENERAL_NAME, Fact.GENERAL_FACT_ID, Fact.GENERAL_FACT_NAME, Fact.GENERAL_FACT_FAV);
                    }
                });
                break;

            case 2:
                factTextView.setText(db.getFact((readFromShared("animalKey")-1), Fact.TABLE_ANIMAL_NAME, Fact.ANIMAL_FACT_ID, Fact.ANIMAL_FACT_NAME, Fact.ANIMAL_FACT_FAV).getFact());
                textViewId.setText("Fact " + (readFromShared("animalKey")-1) + " of " + db.getFactsCount(Fact.TABLE_ANIMAL_NAME));
                if(db.getFact((readFromShared("animalKey")-1), Fact.TABLE_ANIMAL_NAME, Fact.ANIMAL_FACT_ID, Fact.ANIMAL_FACT_NAME, Fact.ANIMAL_FACT_FAV).isFavorite() == 0){
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        showAd();
                        onSwipeMethod(event,"animalKey", Fact.TABLE_ANIMAL_NAME, Fact.ANIMAL_FACT_ID, Fact.ANIMAL_FACT_NAME,Fact.ANIMAL_FACT_FAV);
                        return true;
                    }});

                favImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleFavorites("animalKey",Fact.TABLE_ANIMAL_NAME, Fact.ANIMAL_FACT_ID, Fact.ANIMAL_FACT_NAME, Fact.ANIMAL_FACT_FAV);
                    }
                });


                break;

            case 3:
                factTextView.setText(db.getFact((readFromShared("computerKey")-1), Fact.TABLE_COMPUTER_NAME, Fact.COMPUTER_FACT_ID, Fact.COMPUTER_FACT_NAME,Fact.COMPUTER_FACT_FAV).getFact());
                textViewId.setText("Fact " + (readFromShared("computerKey")-1) + " of " + db.getFactsCount(Fact.TABLE_COMPUTER_NAME));

                if(db.getFact((readFromShared("computerKey")-1), Fact.TABLE_COMPUTER_NAME, Fact.COMPUTER_FACT_ID, Fact.COMPUTER_FACT_NAME, Fact.COMPUTER_FACT_FAV).isFavorite() == 0){
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        showAd();
                        onSwipeMethod(event,"computerKey", Fact.TABLE_COMPUTER_NAME, Fact.COMPUTER_FACT_ID, Fact.COMPUTER_FACT_NAME,Fact.COMPUTER_FACT_FAV);
                        return true;
                    }});

                favImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleFavorites("computerKey",Fact.TABLE_COMPUTER_NAME, Fact.COMPUTER_FACT_ID, Fact.COMPUTER_FACT_NAME, Fact.COMPUTER_FACT_FAV);
                    }
                });
                break;

            case 4:
                factTextView.setText(db.getFact((readFromShared("countriesKey")-1), Fact.TABLE_COUNTRIES_NAME, Fact.COUNTRIES_FACT_ID, Fact.COUNTRIES_FACT_NAME, Fact.COUNTRIES_FACT_FAV).getFact());
                textViewId.setText("Fact " + (readFromShared("countriesKey")-1) + " of " + db.getFactsCount(Fact.TABLE_COUNTRIES_NAME));

                if(db.getFact((readFromShared("countriesKey")-1), Fact.TABLE_COUNTRIES_NAME, Fact.COUNTRIES_FACT_ID, Fact.COUNTRIES_FACT_NAME, Fact.COUNTRIES_FACT_FAV).isFavorite() == 0){
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        showAd();
                        onSwipeMethod(event,"countriesKey", Fact.TABLE_COUNTRIES_NAME, Fact.COUNTRIES_FACT_ID, Fact.COUNTRIES_FACT_NAME, Fact.COUNTRIES_FACT_FAV);
                        return true;
                    }});

                favImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleFavorites("countriesKey",Fact.TABLE_COUNTRIES_NAME, Fact.COUNTRIES_FACT_ID, Fact.COUNTRIES_FACT_NAME, Fact.COUNTRIES_FACT_FAV);
                    }
                });
                break;

            case 5:
                factTextView.setText(db.getFact((readFromShared("foodKey")-1), Fact.TABLE_FOOD_NAME, Fact.FOOD_FACT_ID, Fact.FOOD_FACT_NAME, Fact.FOOD_FACT_FAV).getFact());
                textViewId.setText("Fact " + (readFromShared("foodKey")-1) + " of " + db.getFactsCount(Fact.TABLE_FOOD_NAME));

                if(db.getFact((readFromShared("foodKey")-1), Fact.TABLE_FOOD_NAME, Fact.FOOD_FACT_ID, Fact.FOOD_FACT_NAME, Fact.FOOD_FACT_FAV).isFavorite() == 0){
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        showAd();
                        onSwipeMethod(event,"foodKey", Fact.TABLE_FOOD_NAME, Fact.FOOD_FACT_ID, Fact.FOOD_FACT_NAME, Fact.FOOD_FACT_FAV);
                        return true;
                    }});


                favImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleFavorites("foodKey",Fact.TABLE_FOOD_NAME, Fact.FOOD_FACT_ID, Fact.FOOD_FACT_NAME, Fact.FOOD_FACT_FAV);
                    }
                });
                break;

            case 6:
                factTextView.setText(db.getFact((readFromShared("humanBodyKey")-1), Fact.TABLE_HUMANBODY_NAME, Fact.HUMANBODY_FACT_ID, Fact.HUMANBODY_FACT_NAME, Fact.HUMANBODY_FACT_FAV).getFact());
                textViewId.setText("Fact " + (readFromShared("humanBodyKey")-1) + " of " + db.getFactsCount(Fact.TABLE_HUMANBODY_NAME));
                if(db.getFact((readFromShared("humanBodyKey")-1), Fact.TABLE_HUMANBODY_NAME, Fact.HUMANBODY_FACT_ID, Fact.HUMANBODY_FACT_NAME, Fact.HUMANBODY_FACT_FAV).isFavorite() == 0){
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        showAd();
                        onSwipeMethod(event,"humanBodyKey", Fact.TABLE_HUMANBODY_NAME, Fact.HUMANBODY_FACT_ID, Fact.HUMANBODY_FACT_NAME, Fact.HUMANBODY_FACT_FAV);
                        return true;
                    }});

                favImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleFavorites("humanBodyKey",Fact.TABLE_HUMANBODY_NAME, Fact.HUMANBODY_FACT_ID, Fact.HUMANBODY_FACT_NAME, Fact.HUMANBODY_FACT_FAV);
                    }
                });
                break;

            case 7:
                factTextView.setText(db.getFact((readFromShared("peopleKey")-1), Fact.TABLE_PEOPLE_NAME, Fact.PEOPLE_FACT_ID, Fact.PEOPLE_FACT_NAME, Fact.PEOPLE_FACT_FAV).getFact());
                textViewId.setText("Fact " + (readFromShared("peopleKey")-1) + " of " + db.getFactsCount(Fact.TABLE_PEOPLE_NAME));
                if(db.getFact((readFromShared("peopleKey")-1), Fact.TABLE_PEOPLE_NAME, Fact.PEOPLE_FACT_ID, Fact.PEOPLE_FACT_NAME, Fact.PEOPLE_FACT_FAV).isFavorite() == 0){
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        showAd();
                        onSwipeMethod(event,"peopleKey", Fact.TABLE_PEOPLE_NAME, Fact.PEOPLE_FACT_ID, Fact.PEOPLE_FACT_NAME, Fact.PEOPLE_FACT_FAV);
                        return true;
                    }});

                favImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleFavorites("peopleKey",Fact.TABLE_PEOPLE_NAME, Fact.PEOPLE_FACT_ID, Fact.PEOPLE_FACT_NAME, Fact.PEOPLE_FACT_FAV);
                    }
                });
                break;

            case 8:
                factTextView.setText(db.getFact((readFromShared("psychologyKey")-1), Fact.TABLE_PSYCHOLOGY_NAME, Fact.PSYCHOLOGY_FACT_ID, Fact.PSYCHOLOGY_FACT_NAME, Fact.PSYCHOLOGY_FACT_FAV).getFact());
                textViewId.setText("Fact " + (readFromShared("psychologyKey")-1) + " of " + db.getFactsCount(Fact.TABLE_PSYCHOLOGY_NAME));
                if(db.getFact((readFromShared("psychologyKey")-1), Fact.TABLE_PSYCHOLOGY_NAME, Fact.PSYCHOLOGY_FACT_ID, Fact.PSYCHOLOGY_FACT_NAME, Fact.PSYCHOLOGY_FACT_FAV).isFavorite() == 0){
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        showAd();
                        onSwipeMethod(event,"psychologyKey", Fact.TABLE_PSYCHOLOGY_NAME, Fact.PSYCHOLOGY_FACT_ID, Fact.PSYCHOLOGY_FACT_NAME, Fact.PSYCHOLOGY_FACT_FAV);
                        return true;
                    }});
                favImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleFavorites("psychologyKey",Fact.TABLE_PSYCHOLOGY_NAME, Fact.PSYCHOLOGY_FACT_ID, Fact.PSYCHOLOGY_FACT_NAME, Fact.PSYCHOLOGY_FACT_FAV);
                    }
                });
                break;

            case 9:
                factTextView.setText(db.getFact((readFromShared("scienceKey")-1), Fact.TABLE_SCIENCE_NAME, Fact.SCIENCE_FACT_ID, Fact.SCIENCE_FACT_NAME, Fact.SCIENCE_FACT_FAV).getFact());
                textViewId.setText("Fact " + (readFromShared("scienceKey")-1) + " of " + db.getFactsCount(Fact.TABLE_SCIENCE_NAME));

                if(db.getFact((readFromShared("scienceKey")-1), Fact.TABLE_SCIENCE_NAME, Fact.SCIENCE_FACT_ID, Fact.SCIENCE_FACT_NAME, Fact.SCIENCE_FACT_FAV).isFavorite() == 0){
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        showAd();
                        onSwipeMethod(event,"scienceKey", Fact.TABLE_SCIENCE_NAME, Fact.SCIENCE_FACT_ID, Fact.SCIENCE_FACT_NAME, Fact.SCIENCE_FACT_FAV);
                        return true;
                    }});

                favImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleFavorites("scienceKey",Fact.TABLE_SCIENCE_NAME, Fact.SCIENCE_FACT_ID, Fact.SCIENCE_FACT_NAME, Fact.SCIENCE_FACT_FAV);
                    }
                });
                break;

            case 10:
                factTextView.setText(db.getFact((readFromShared("universeKey")-1), Fact.TABLE_UNIVERSE_NAME, Fact.UNIVERSE_FACT_ID, Fact.UNIVERSE_FACT_NAME, Fact.UNIVERSE_FACT_FAV).getFact());
                textViewId.setText("Fact " + (readFromShared("universeKey")-1) + " of " + db.getFactsCount(Fact.TABLE_UNIVERSE_NAME));
                if(db.getFact((readFromShared("universeKey")-1), Fact.TABLE_UNIVERSE_NAME, Fact.UNIVERSE_FACT_ID, Fact.UNIVERSE_FACT_NAME, Fact.UNIVERSE_FACT_FAV).isFavorite() == 0){
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        showAd();
                        onSwipeMethod(event,"universeKey", Fact.TABLE_UNIVERSE_NAME, Fact.UNIVERSE_FACT_ID, Fact.UNIVERSE_FACT_NAME, Fact.UNIVERSE_FACT_FAV);
                        return true;
                    }});
                favImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleFavorites("universeKey",Fact.TABLE_UNIVERSE_NAME, Fact.UNIVERSE_FACT_ID, Fact.UNIVERSE_FACT_NAME, Fact.UNIVERSE_FACT_FAV);
                    }
                });
                break;

            case 11:
                factTextView.setText(db.getFact((readFromShared("weatherKey")-1), Fact.TABLE_WEATHER_NAME, Fact.WEATHER_FACT_ID, Fact.WEATHER_FACT_NAME, Fact.WEATHER_FACT_FAV).getFact());
                textViewId.setText("Fact " + (readFromShared("weatherKey")-1) + " of " + db.getFactsCount(Fact.TABLE_WEATHER_NAME));
                if(db.getFact((readFromShared("weatherKey")-1), Fact.TABLE_WEATHER_NAME, Fact.WEATHER_FACT_ID, Fact.WEATHER_FACT_NAME, Fact.WEATHER_FACT_FAV).isFavorite() == 0){
                    favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

                relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        showAd();
                        onSwipeMethod(event,"weatherKey", Fact.TABLE_WEATHER_NAME, Fact.WEATHER_FACT_ID, Fact.WEATHER_FACT_NAME, Fact.WEATHER_FACT_FAV);
                        return true;
                    }});
                favImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleFavorites("weatherKey",Fact.TABLE_WEATHER_NAME, Fact.WEATHER_FACT_ID, Fact.WEATHER_FACT_NAME, Fact.WEATHER_FACT_FAV);
                    }
                });
                break;

            case 20:
                favList.addAll(db.getFavFacts(Fact.TABLE_GENERAL_NAME, Fact.GENERAL_FACT_ID, Fact.GENERAL_FACT_NAME, Fact.GENERAL_FACT_FAV));
                favList.addAll(db.getFavFacts(Fact.TABLE_ANIMAL_NAME, Fact.ANIMAL_FACT_ID, Fact.ANIMAL_FACT_NAME,Fact.ANIMAL_FACT_FAV));
                favList.addAll(db.getFavFacts(Fact.TABLE_COMPUTER_NAME, Fact.COMPUTER_FACT_ID, Fact.COMPUTER_FACT_NAME,Fact.COMPUTER_FACT_FAV));
                favList.addAll(db.getFavFacts(Fact.TABLE_COUNTRIES_NAME, Fact.COUNTRIES_FACT_ID, Fact.COUNTRIES_FACT_NAME, Fact.COUNTRIES_FACT_FAV));
                favList.addAll(db.getFavFacts(Fact.TABLE_FOOD_NAME, Fact.FOOD_FACT_ID, Fact.FOOD_FACT_NAME, Fact.FOOD_FACT_FAV));
                favList.addAll(db.getFavFacts(Fact.TABLE_HUMANBODY_NAME, Fact.HUMANBODY_FACT_ID, Fact.HUMANBODY_FACT_NAME, Fact.HUMANBODY_FACT_FAV));
                favList.addAll(db.getFavFacts(Fact.TABLE_PEOPLE_NAME, Fact.PEOPLE_FACT_ID, Fact.PEOPLE_FACT_NAME, Fact.PEOPLE_FACT_FAV));
                favList.addAll(db.getFavFacts(Fact.TABLE_PSYCHOLOGY_NAME, Fact.PSYCHOLOGY_FACT_ID, Fact.PSYCHOLOGY_FACT_NAME, Fact.PSYCHOLOGY_FACT_FAV));
                favList.addAll(db.getFavFacts(Fact.TABLE_SCIENCE_NAME, Fact.SCIENCE_FACT_ID, Fact.SCIENCE_FACT_NAME, Fact.SCIENCE_FACT_FAV));
                favList.addAll(db.getFavFacts(Fact.TABLE_UNIVERSE_NAME, Fact.UNIVERSE_FACT_ID, Fact.UNIVERSE_FACT_NAME, Fact.UNIVERSE_FACT_FAV));
                favList.addAll(db.getFavFacts(Fact.TABLE_WEATHER_NAME, Fact.WEATHER_FACT_ID, Fact.WEATHER_FACT_NAME, Fact.WEATHER_FACT_FAV));

                favImageView.setVisibility(View.GONE);
                if(favList.isEmpty()){
                    factTextView.setText("You have no favorite facts yet!");
                    textViewId.setText(null);
                } else if(favList.size() == 1){
                    factTextView.setText(favList.get(readFromShared("FavoriteKey")-2).getFact());
                    textViewId.setText("Fact "+ (readFromShared("FavoriteKey")-1)+ " of " + favList.size());
                }
                else {
                    factTextView.setText(favList.get(readFromShared("FavoriteKey")-1).getFact());
                    textViewId.setText("Fact "+ (readFromShared("FavoriteKey"))+ " of " + favList.size());

                    relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            onSwipeFavMethod(event,favList,"FavoriteKey");
                            return true;
                        }});
                    break;
                }
        }




        imageView = findViewById(R.id.activity_main_image_view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = factTextView.getText().toString();
                String shareSub = "Another fact!";
                intent.putExtra(intent.EXTRA_TEXT,shareBody);
                intent.putExtra(intent.EXTRA_SUBJECT,shareSub);
                startActivity(intent.createChooser(intent,"Share your fact."));
            }
        });
//
//        menuImageView = findViewById(R.id.menu);
//        menuImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showMenu(v);
//            }
//        });


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


    void onSwipeMethod(MotionEvent event, String key, String tableName, String factId, String factName, String factFav){
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

                        fact = db.getFact(i, tableName, factId, factName,factFav).getFact();
                        factTextView.setText(fact);
                        int color = colorWheel.getColor();
                        relativeLayout.setBackgroundColor(color);
                        textViewId.setText("Fact " + i + " of " + db.getFactsCount(tableName));
                        if(db.getFact(i, tableName, factId, factName, factFav).isFavorite() == 0){
                            favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        }else {
                            favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                        }
                        createShared(i, key);
                    } else i = 1;
                } else if (x2 < x1) { //RIGHT
                    readFromShared(key);
                    if (i <= db.getFactsCount(tableName)) {
                        fact = db.getFact(i, tableName, factId, factName,factFav).getFact();
                        factTextView.setText(fact);
                        int color = colorWheel.getColor();
                        relativeLayout.setBackgroundColor(color);
                        textViewId.setText("Fact " + i + " of " + db.getFactsCount(tableName));
                        if(db.getFact(i, tableName, factId, factName, factFav).isFavorite() == 0){
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
                        fact = db.getFact(i, tableName, factId, factName, factFav).getFact();
                        factTextView.setText(fact);
                        int color = colorWheel.getColor();
                        relativeLayout.setBackgroundColor(color);
                        textViewId.setText("Fact " + i + " of " + db.getFactsCount(tableName));
                        if (db.getFact(i, tableName, factId, factName, factFav).isFavorite() == 0) {
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

    void handleFavorites(String key, String tableName, String factId, String factName, String factFav){
        int y = readFromShared(key)-1;
        if(db.getFact(y, tableName, factId, factName, factFav).isFavorite() == 0){
            favImageView.setImageResource(R.drawable.ic_favorite_black_24dp);
            db.updateFact(y,factId,true, factFav,tableName,factName,factTextView.getText().toString());
        }else {
            favImageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            db.updateFact(y,factId,false, factFav,tableName,factName,factTextView.getText().toString());
        }

        db.close();
    }

    void onSwipeFavMethod(MotionEvent event,ArrayList<Fact> factArrayList, String key) {
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
                    if (i >= 2) {
                        i -= 2;
                        fact = factArrayList.get(i).getFact();
                        factTextView.setText(fact);
                        int color = colorWheel.getColor();
                        relativeLayout.setBackgroundColor(color);
                        textViewId.setText("Fact " + (i+1) + " of " + factArrayList.size());
                        createShared(i, key);
                    } else i = 0;
                } else if (x2 < x1) { //RIGHT
                    readFromShared(key);
                    if (i < factArrayList.size()) {
                        fact = factArrayList.get(i).getFact();
                        factTextView.setText(fact);
                        int color = colorWheel.getColor();
                        relativeLayout.setBackgroundColor(color);
                        textViewId.setText("Fact " + (i+1) + " of " + factArrayList.size());
                        createShared(i, key);
                        i++;
                    }else i=(factArrayList.size()-1);
                } else if (x2 == x1) {
                    readFromShared(key);
                    if (i < factArrayList.size()) {
                    fact = factArrayList.get(i).getFact();
                    factTextView.setText(fact);
                    int color = colorWheel.getColor();
                    relativeLayout.setBackgroundColor(color);
                    textViewId.setText("Fact " + (i+1) + " of " + factArrayList.size());
                    createShared(i, key);
                    i++;
                    }else i=(factArrayList.size()-1);
                }
                break;
        }
    }

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



//    public void showMenu(View v) {
//
//        PopupMenu popup = new PopupMenu(this, v);
//        // This activity implements OnMenuItemClickListener
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.menu_jump:
//                        return true;
//                    case R.id.menu_verify:
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//        });
//        popup.inflate(R.menu.inner_menu);
//        popup.show();
//    }

    }
