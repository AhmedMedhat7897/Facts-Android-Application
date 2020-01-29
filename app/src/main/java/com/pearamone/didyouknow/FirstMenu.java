package com.pearamone.didyouknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstMenu extends AppCompatActivity {

    Button categories;
    Button favFacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_menu);

        categories = findViewById(R.id.first_menu_cat);
        favFacts = findViewById(R.id.first_menu_fav);

        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstMenu.this, HomeMenu.class);
                startActivity(intent);
            }
        });

        favFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstMenu.this, MainActivity.class);
                intent.putExtra("choice",20);
                startActivity(intent);
            }
        });
    }
}
