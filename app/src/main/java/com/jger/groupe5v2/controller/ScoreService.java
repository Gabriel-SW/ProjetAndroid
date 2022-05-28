package com.jger.groupe5v2.controller;

import com.jger.groupe5v2.model.Score;

import java.util.Dictionary;
import java.util.Map;

public class ScoreService {
    private final ScoreDao ScoreDao;

    public ScoreService(ScoreDao ScoreDao) {
        this.ScoreDao = ScoreDao;
    }

    public Long getComputeCount(){
        return ScoreDao.count();
    }

    public Map<String, Integer> getScore(){
        return ScoreDao.getScore();
    }

    public void storeInDB(Score score){
        ScoreDao.create(score);
    }
}

