package com.dmc.DesignPatterns.builder.scene2;

public class Science {
    private Integer pyhsics;
    private Integer chemistry;
    private Integer biology;

    public Science(Integer pyhsics, Integer chemistry, Integer biology) {
        this.pyhsics = pyhsics;
        this.chemistry = chemistry;
        this.biology = biology;
    }

    public Integer getPyhsics() {
        return pyhsics;
    }

    public void setPyhsics(Integer pyhsics) {
        this.pyhsics = pyhsics;
    }

    public Integer getChemistry() {
        return chemistry;
    }

    public void setChemistry(Integer chemistry) {
        this.chemistry = chemistry;
    }

    public Integer getBiology() {
        return biology;
    }

    public void setBiology(Integer biology) {
        this.biology = biology;
    }
}
