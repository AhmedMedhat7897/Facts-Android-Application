package com.pearamone.didyouknow;

public class Fact {
    //GENERAL
    public static String TABLE_GENERAL_NAME = "general_fact_table";
    public static String GENERAL_FACT_NAME = "general_fact_name";
    public static String GENERAL_FACT_ID = "general_fact_id";
    public static String GENERAL_FACT_FAV = "general_fact_fav";

    public static final String CREATE_GENERAL_TABLE =
            "CREATE TABLE " + TABLE_GENERAL_NAME + "("
                    + GENERAL_FACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + GENERAL_FACT_NAME + " TEXT,"
                    + GENERAL_FACT_FAV + " BOOLEAN"
                    + ")";
    //ANIMAL
    public static String TABLE_ANIMAL_NAME = "animal_fact_table";
    public static String ANIMAL_FACT_NAME = "animal_fact_name";
    public static String ANIMAL_FACT_ID = "animal_fact_id";
    public static String ANIMAL_FACT_FAV = "animal_fact_fav";

    public static final String CREATE_ANIMAL_TABLE =
            "CREATE TABLE " + TABLE_ANIMAL_NAME + "("
                    + ANIMAL_FACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ANIMAL_FACT_NAME + " TEXT,"
                    + ANIMAL_FACT_FAV + " BOOLEAN"
                    + ")";

    //COMPUTER
    public static String TABLE_COMPUTER_NAME = "computer_fact_table";
    public static String COMPUTER_FACT_NAME = "computer_fact_name";
    public static String COMPUTER_FACT_ID = "computer_fact_id";
    public static String COMPUTER_FACT_FAV = "computer_fact_fav";

    public static final String CREATE_COMPUTER_TABLE =
            "CREATE TABLE " + TABLE_COMPUTER_NAME + "("
                    + COMPUTER_FACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COMPUTER_FACT_NAME + " TEXT,"
                    + COMPUTER_FACT_FAV + " BOOLEAN"
                    + ")";

    //COUNTRIES
    public static String TABLE_COUNTRIES_NAME = "countries_fact_table";
    public static String COUNTRIES_FACT_NAME = "countries_fact_name";
    public static String COUNTRIES_FACT_ID = "countries_fact_id";
    public static String COUNTRIES_FACT_FAV = "countries_fact_fav";

    public static final String CREATE_COUNTRIES_TABLE =
            "CREATE TABLE " + TABLE_COUNTRIES_NAME + "("
                    + COUNTRIES_FACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COUNTRIES_FACT_NAME + " TEXT,"
                    + COUNTRIES_FACT_FAV + " BOOLEAN"
                    + ")";

    //FOOD
    public static String TABLE_FOOD_NAME = "food_fact_table";
    public static String FOOD_FACT_NAME = "food_fact_name";
    public static String FOOD_FACT_ID = "food_fact_id";
    public static String FOOD_FACT_FAV = "food_fact_fav";

    public static final String CREATE_FOOD_TABLE =
            "CREATE TABLE " + TABLE_FOOD_NAME + "("
                    + FOOD_FACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + FOOD_FACT_NAME + " TEXT,"
                    + FOOD_FACT_FAV + " BOOLEAN"
                    + ")";

    //HUMANBODY
    public static String TABLE_HUMANBODY_NAME = "humanbody_fact_table";
    public static String HUMANBODY_FACT_NAME = "humanbody_fact_name";
    public static String HUMANBODY_FACT_ID = "humanbody_fact_id";
    public static String HUMANBODY_FACT_FAV = "humanbody_fact_fav";

    public static final String CREATE_HUMANBODY_TABLE =
            "CREATE TABLE " + TABLE_HUMANBODY_NAME + "("
                    + HUMANBODY_FACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + HUMANBODY_FACT_NAME + " TEXT,"
                    + HUMANBODY_FACT_FAV + " BOOLEAN"
                    + ")";

    //PEOPLE
    public static String TABLE_PEOPLE_NAME = "people_fact_table";
    public static String PEOPLE_FACT_NAME = "people_fact_name";
    public static String PEOPLE_FACT_ID = "people_fact_id";
    public static String PEOPLE_FACT_FAV = "people_fact_fav";

    public static final String CREATE_PEOPLE_TABLE =
            "CREATE TABLE " + TABLE_PEOPLE_NAME + "("
                    + PEOPLE_FACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PEOPLE_FACT_NAME + " TEXT,"
                    + PEOPLE_FACT_FAV + " BOOLEAN"
                    + ")";

    //PSYCHOLOGY
    public static String TABLE_PSYCHOLOGY_NAME = "psychology_fact_table";
    public static String PSYCHOLOGY_FACT_NAME = "psychology_fact_name";
    public static String PSYCHOLOGY_FACT_ID = "psychology_fact_id";
    public static String PSYCHOLOGY_FACT_FAV = "psychology_fact_fav";

    public static final String CREATE_PSYCHOLOGY_TABLE =
            "CREATE TABLE " + TABLE_PSYCHOLOGY_NAME + "("
                    + PSYCHOLOGY_FACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PSYCHOLOGY_FACT_NAME + " TEXT,"
                    + PSYCHOLOGY_FACT_FAV + " BOOLEAN"
                    + ")";

    //SCIENCE
    public static String TABLE_SCIENCE_NAME = "science_fact_table";
    public static String SCIENCE_FACT_NAME = "science_fact_name";
    public static String SCIENCE_FACT_ID = "science_fact_id";
    public static String SCIENCE_FACT_FAV = "science_fact_fav";

    public static final String CREATE_SCIENCE_TABLE =
            "CREATE TABLE " + TABLE_SCIENCE_NAME + "("
                    + SCIENCE_FACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + SCIENCE_FACT_NAME + " TEXT,"
                    + SCIENCE_FACT_FAV + " BOOLEAN"
                    + ")";

    //UNIVERSE
    public static String TABLE_UNIVERSE_NAME = "universe_fact_table";
    public static String UNIVERSE_FACT_NAME = "universe_fact_name";
    public static String UNIVERSE_FACT_ID = "universe_fact_id";
    public static String UNIVERSE_FACT_FAV = "universe_fact_fav";

    public static final String CREATE_UNIVERSE_TABLE =
            "CREATE TABLE " + TABLE_UNIVERSE_NAME + "("
                    + UNIVERSE_FACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + UNIVERSE_FACT_NAME + " TEXT,"
                    + UNIVERSE_FACT_FAV + " BOOLEAN"
                    + ")";

    //WEATHER
    public static String TABLE_WEATHER_NAME = "weather_fact_table";
    public static String WEATHER_FACT_NAME = "weather_fact_name";
    public static String WEATHER_FACT_ID = "weather_fact_id";
    public static String WEATHER_FACT_FAV = "weather_fact_fav";

    public static final String CREATE_WEATHER_TABLE =
            "CREATE TABLE " + TABLE_WEATHER_NAME + "("
                    + WEATHER_FACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + WEATHER_FACT_NAME + " TEXT,"
                    + WEATHER_FACT_FAV + " BOOLEAN"
                    + ")";


    private String fact;
    private int factId;
    private int favorite;

    public Fact(){

    }

    public Fact(int factId, String fact, int favorite) {
        this.fact = fact;
        this.factId = factId;
        this.favorite = favorite;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public int getFactId() {
        return factId;
    }

    public void setFactId(int factId) {
        this.factId = factId;
    }

    public int isFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
