package com.pearamone.didyouknow;

public class Fact {

    private String fact;
    private int factId;
    private int favorite;
    private String tableName;

    public Fact(){

    }

    public Fact(int factId, String fact, int favorite, String tableName) {
        this.fact = fact;
        this.factId = factId;
        this.favorite = favorite;
        this.tableName = tableName;
    }

    public String getFact() {
        return fact;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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
