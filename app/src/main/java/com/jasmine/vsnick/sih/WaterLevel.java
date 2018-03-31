package com.jasmine.vsnick.sih;

/**
 * Created by vsnick on 31-03-2018.
 */

class WaterLevel {


    private String time;
    private Double level;

    public WaterLevel(){

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "WaterLevel{" +
                "time='" + time + '\'' +
                ", level=" + level +
                '}';
    }
}
