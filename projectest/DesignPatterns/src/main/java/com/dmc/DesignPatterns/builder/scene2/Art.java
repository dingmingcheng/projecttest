package com.dmc.DesignPatterns.builder.scene2;

public class Art {
    private Integer history;
    private Integer geography;
    private Integer politics;

    public Art(Integer history, Integer geography, Integer politics) {
        this.history = history;
        this.geography = geography;
        this.politics = politics;
    }

    public Integer getHistory() {
        return history;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }

    public Integer getGeography() {
        return geography;
    }

    public void setGeography(Integer geography) {
        this.geography = geography;
    }

    public Integer getPolitics() {
        return politics;
    }

    public void setPolitics(Integer politics) {
        this.politics = politics;
    }
}
