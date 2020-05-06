package com.pearamone.didyouknow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class FirstMenu extends AppCompatActivity {

    CardView categories;
    CardView favFacts;
    CardView settingsButton;
    CardView searchButton;
    ImageView premiumImageView;
    int vipStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_menu);
        vipStatus = readFromSharedVIP();
        categories = findViewById(R.id.first_menu_cat);
        favFacts = findViewById(R.id.first_menu_fav);
        searchButton = findViewById(R.id.search_button);
        premiumImageView = findViewById(R.id.activity_first_menu_crown);

        categories.setOnClickListener(v -> {
            Intent intent = new Intent(FirstMenu.this, HomeMenu.class);
            startActivity(intent);
        });

        favFacts.setOnClickListener(v -> {
            Intent intent = new Intent(FirstMenu.this, FavoritesActivity.class);
            startActivity(intent);
        });

        if(vipStatus == 0){
            searchButton.setOnClickListener(v -> {
                Intent intent = new Intent(FirstMenu.this, PremiumActivity.class);
                startActivity(intent);
            });
        }
        else if(vipStatus == 1) {
            searchButton.setOnClickListener(v -> {
                Intent intent = new Intent(FirstMenu.this, SearchActivity.class);
                startActivity(intent);
            });
        }
        if(vipStatus == 0){
            premiumImageView.setOnClickListener(v -> {
                Intent intent = new Intent(FirstMenu.this, PremiumActivity.class);
                startActivity(intent);
            });
        }else if (vipStatus == 1){
            premiumImageView.setOnClickListener(v -> {
                Toast toast = Toast.makeText(getApplicationContext(), "You're already a premium user!", Toast.LENGTH_LONG);
                toast.show();
            });
        }
    }



    private int readFromSharedVIP() {
        SharedPreferences preferences = getSharedPreferences("MyShared", MODE_PRIVATE);
        return preferences.getInt("VIPStatus", 0);
    }
}
