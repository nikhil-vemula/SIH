package com.jasmine.vsnick.sih;


import java.io.Serializable;

/**
 * Created by vsnick on 30-03-2018.
 */
class Location implements Serializable{
    private Double lat;
    private Double log;
    public Location(){}
    public Location(Double lat,Double lng){
        this.lat = lat;
        this.log = log;
    }

    public void setLat(Double lat) {
        this.lat = (lat);
    }

    public Double getLat(){
        return lat;

    }

    public void setLog(Double log) {
        this.log = (log);
    }

    public Double getLog(){
        return log;
    }
}
public class Structure implements Serializable{

    private String id;
    private String dept;
    private Location location;
    private String state;
    private String status;
    private String type;
    private String uid;
    private String photoUrl;

    public Structure(){

    }
    public Structure(String dept,Location location,String state,String status,String type,String uid){
        this.dept = dept;
        this.location = location;
        this.state = state;
        this.status = status;
        this.type = type;
        this.uid = uid;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getDept(){
        return dept;
    }
    public String getState(){
        return state;
    }
    public String getType(){
        return type;
    }
    public String getStatus(){
        return status;
    }
    public String getUid(){
        return uid;
    }
    public String getPhotoUrl(){
        return photoUrl;
    }
    public void setDept(String dept){
        this.dept = dept;
    }
    public void setLocation(Location location){
        this.location = location;
    }
    public void setState(String state){
        this.state = state;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void setType(String type){
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setUid(String uid){
        this.uid = uid;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }
}
