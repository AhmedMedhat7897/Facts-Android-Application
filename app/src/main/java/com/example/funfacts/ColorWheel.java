package com.example.funfacts;

import android.graphics.Color;

import java.util.Random;

public class ColorWheel {
    // Fields or Member Variables - Properties about the object
    private String[] colors = {
            "#E15258",
            "#F9845B",
            "#838CC7",
            "#7D669E",
            "#53BBB4",
            "#51B46D",
            "#E0AB18",
            "#637A91",
            "#F092B0",
            "#B7C0C7"
};
    //Methods - Actions the object can take
    int getColor(){
        //randomly select a fact
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(colors.length);
        int color = Color.parseColor(colors[randomNumber]);
        return color;
    }
}
