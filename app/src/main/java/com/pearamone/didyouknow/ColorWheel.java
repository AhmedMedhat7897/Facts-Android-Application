package com.pearamone.didyouknow;

import android.graphics.Color;

import java.util.Random;

public class ColorWheel {
    // Fields or Member Variables - Properties about the object
    private String[] colors = {
            "#F9845B",
            "#838CC7",
            "#7D669E",
            "#53BBB4",
            "#51B46D",
            "#E0AB18",
            "#637A91",
            "#F092B0",
            "#B7C0C7",
            "#d26769",
            "#1e841c",
            "#bbaf88",
            "#24985b",
            "#263736",
            "#6ec3ce",
            "#a8559e"

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
