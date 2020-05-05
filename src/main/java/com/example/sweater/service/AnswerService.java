package com.example.sweater.service;

import com.example.sweater.domain.Answer;
import com.example.sweater.domain.Game;
import com.example.sweater.domain.PageGame;
import com.example.sweater.repos.AnswerRepo;
import com.example.sweater.repos.GameRepo;
import com.example.sweater.repos.PageGameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AnswerService {
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private PageGameRepo pageGameRepo;

    public void checkAnswer(List<Answer> answers, PageGame currentPage, Game game, String answer ){
        for (Answer a : answers){
            if (!a.isFound()){
                String[] ans = a.getAnswers().split("/");
                for (String i : ans) {
                    long diffInMillies = Math.abs(new Date(System.currentTimeMillis()).getTime()- currentPage.getStart().getTime());
                    long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    long diffPause =0;
                    if (game.getPauseStart() != null && game.getPauseFinish()!=null){
                        long diffInMilliesPause = Math.abs(game.getPauseFinish().getTime()- game.getPauseStart().getTime());
                        diffPause = TimeUnit.SECONDS.convert(diffInMilliesPause, TimeUnit.MILLISECONDS);
                        diff = diff - diffPause;
                        int sumMinutesPause = (int) diffPause/60;
                        int sumSecondsPause = (int) diffPause%60;
                        game.setPause(String.valueOf(sumMinutesPause +"."+ sumSecondsPause));
                        gameRepo.save(game);

                    }
                    int sumMinutes = (int) diff/60;
                    int sumSeconds = (int) diff%60;
                    double time =0;
                    if (String.valueOf(sumSeconds).length() == 1) {
                        time = Double.parseDouble(sumMinutes + ".0" + sumSeconds);
                    } else {
                        time = Double.parseDouble(sumMinutes + "." + sumSeconds);
                    }
                    currentPage.setTimeElapsed(time);
                    if (answer.equals(i)) {
                        a.setFound(true);
                        currentPage.setAnswerTime(time);
                        a.setAnswers(answer);
                        answerRepo.save(a);
                    }
                    if (answers.stream().allMatch(Answer::isFound)) {
                        currentPage.setAnswered(true);
                        if (game.getPauseStart() !=null&& game.getPauseFinish()!=null) {
                            game.setPauseFinish(null);
                            game.setPauseStart(null);
                            gameRepo.save(game);
                        }
                    }
                    pageGameRepo.save(currentPage);
                }
            }
        }
        gameRepo.save(game);
    }
}
