package com.pearamone.didyouknow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    RecyclerView NewsRecyclerView;
    SearchAdapter searchAdapter;
    List<Fact> mData;
    DatabaseFacts db;
    FloatingActionButton fabSwitcher;
    boolean isDark = false;
    ConstraintLayout rootLayout;
    EditText searchInput;
    String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        db = new DatabaseFacts(this);
        mData = new ArrayList<>();

        NewsRecyclerView = findViewById(R.id.news_rv);
        fabSwitcher = findViewById(R.id.fab_switcher);
        rootLayout = findViewById(R.id.root_layout);
        searchInput = findViewById(R.id.search_input);

        searchString = searchInput.getText().toString();

        mData.addAll(db.getSearchedFacts(DatabaseFacts.TABLE_GENERAL_NAME,searchString));
        mData.addAll(db.getSearchedFacts(DatabaseFacts.TABLE_ANIMAL_NAME,searchString));
        mData.addAll(db.getSearchedFacts(DatabaseFacts.TABLE_COMPUTER_NAME,searchString));
        mData.addAll(db.getSearchedFacts(DatabaseFacts.TABLE_COUNTRIES_NAME,searchString));
        mData.addAll(db.getSearchedFacts(DatabaseFacts.TABLE_FOOD_NAME,searchString));
        mData.addAll(db.getSearchedFacts(DatabaseFacts.TABLE_HUMANBODY_NAME,searchString));
        mData.addAll(db.getSearchedFacts(DatabaseFacts.TABLE_PEOPLE_NAME,searchString));
        mData.addAll(db.getSearchedFacts(DatabaseFacts.TABLE_PSYCHOLOGY_NAME,searchString));
        mData.addAll(db.getSearchedFacts(DatabaseFacts.TABLE_SCIENCE_NAME,searchString));
        mData.addAll(db.getSearchedFacts(DatabaseFacts.TABLE_UNIVERSE_NAME,searchString));
        mData.addAll(db.getSearchedFacts(DatabaseFacts.TABLE_WEATHER_NAME,searchString));
        mData.addAll(db.getSearchedFacts(DatabaseFacts.TABLE_HACKS_NAME,searchString));





        isDark = getThemeStatePref();
        if(isDark){
            searchInput.setBackgroundResource(R.drawable.search_input_dark_style);
            rootLayout.setBackgroundColor(getResources().getColor(R.color.black));
        }else{
            searchInput.setBackgroundResource(R.drawable.search_input_style);
            rootLayout.setBackgroundColor(getResources().getColor(R.color.white));
        }



        searchAdapter = new SearchAdapter(this,mData,isDark);
        NewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        fabSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDark=!isDark;
                if(isDark){
                    rootLayout.setBackgroundColor(getResources().getColor(R.color.black));
                    searchInput.setBackgroundResource(R.drawable.search_input_dark_style);
                }else {
                    rootLayout.setBackgroundColor(getResources().getColor(R.color.white));
                    searchInput.setBackgroundResource(R.drawable.search_input_style);
                }
                searchAdapter = new SearchAdapter(getApplicationContext(),mData,isDark);
                NewsRecyclerView.setAdapter(searchAdapter);
                saveThemeStatePref(isDark);
            }
        });

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchAdapter.getFilter().filter(s);
                NewsRecyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void saveThemeStatePref(boolean isDark) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyShared",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isDark",isDark);
        editor.commit();
    }

    private boolean getThemeStatePref(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyShared",MODE_PRIVATE);
        boolean isDark = pref.getBoolean("isDark",false);
        return isDark;
    }
}
