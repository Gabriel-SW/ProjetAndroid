package com.jger.groupe5v2.model;

public class Score extends BaseEntity {
    Integer score;
    String username;

    public Score(String userName, Integer score){
        this.username = userName;
        this.score = score;
    }

    public Score(){

    }

    public Integer getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
