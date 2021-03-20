package com.example.sweater.service.service;

import com.example.sweater.entities.Game;
import com.example.sweater.entities.PageGame;
import com.example.sweater.repos.repos.PageGameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class NoAnswerService {
    @Autowired
    private PageGameRepo pageGameRepo;

    public void SetAsNotAnswered(Game currentGame, PageGame currentPageGame){
        long diffInMillies = Math.abs(new Date(System.currentTimeMillis()).getTime()- currentPageGame.getStart().getTime());
        long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if (currentGame.getPauseStart() != null && currentGame.getPauseFinish()!=null){
            long diffInMilliesPause = Math.abs(currentGame.getPauseFinish().getTime()- currentGame.getPauseStart().getTime());
            long diffPause = TimeUnit.SECONDS.convert(diffInMilliesPause, TimeUnit.MILLISECONDS);
            diff = diff - diffPause;
        }
        int sumMinutes = (int) diff/60;
        int sumSeconds = (int) diff%60;
        double time =0;
        if (String.valueOf(sumSeconds).length() == 1) {
            time = Double.parseDouble(sumMinutes + ".0" + sumSeconds);
        } else {
            time = Double.parseDouble(sumMinutes + "." + sumSeconds);
        }
        currentPageGame.setTimeElapsed(time);
        currentPageGame.setAnswerTime(time);
        currentPageGame.setAnswered(true);
        currentPageGame.setAnswerTime(currentPageGame.getTime());
        pageGameRepo.save(currentPageGame);
    }
}
