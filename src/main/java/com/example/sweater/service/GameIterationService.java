package com.example.sweater.service;

import com.example.sweater.domain.*;
import com.example.sweater.repos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class GameIterationService {
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private PageGameRepo pageGameRepo;
    @Autowired
    private ElementRepo elementRepo;
    @Autowired
    private HintRepo hintRepo;
    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private MessageRepo messageRepo;

    private static final Logger logger = LoggerFactory.getLogger(GameIterationService.class);


    public String iteration(Map<String , Object> model, String code){
        Team currentTeam = teamRepo.findByCode(code);
        if (currentTeam == null) return "no-such-team-page";
        Game currentGame = currentTeam.getGame();
        if (currentGame == null || !currentGame.isStarted())  return "game-not-started-page";
        if (currentGame.isPaused()) {
            model.put("code", currentTeam.getCode());
            return "game-is-paused";
        }
        List<PageGame> pages = pageGameRepo.FindByGame(currentGame);
        pages = pages.stream().filter(i-> !i.isAnswered()).collect(Collectors.toList());
        pages.sort(Comparator.comparing(PageGame::getOrderNumber));
        PageGame currentPageGame;
        if (pages.stream().findFirst().isPresent()) {
            currentPageGame = pages.stream().findFirst().get();
            if (currentPageGame.getStart() == null){
                Date date = new Date(System.currentTimeMillis());
                currentPageGame.setStart(date);
                pageGameRepo.save(currentPageGame);
            }
       }else{
            Date date = new Date(System.currentTimeMillis());
            currentGame.setFinish(date);
            int sumMinutes = 0;
            int sumSeconds = 0;
            long pause = 0;
            for (PageGame pageGame : currentGame.getPageGames()){
                String[] arr=String.valueOf(pageGame.getAnswerTime()).split("\\.");
                if (arr[1].length() > 2) arr[1] = arr[1].substring(0,1);
                if (arr[1].length() ==1) arr[1] = arr[1]+"0";
                logger.info("minutes" + arr[0] + "seconds" + arr[1]);
                sumMinutes = sumMinutes + Integer.parseInt(arr[0]);
                sumSeconds = sumSeconds + Integer.parseInt(arr[1]);
            }
            int sumMinutesPause = (int) pause/60;
            int sumSecondsPause = (int) pause%60;
            double timePause =0;
//            if (pause < 10) {
//                timePause = Double.parseDouble(sumMinutesPause+ ".0" + sumSecondsPause);
//            } else {
//                timePause = Double.parseDouble(sumMinutesPause + "." + sumSecondsPause);
//            }
            if (sumSeconds>60){
                int addition = (int) sumSeconds/60;
                sumMinutes = sumMinutes + addition;
                sumSeconds = sumSeconds- (addition * 60);
            }
            logger.info("setting" + " minutes " + sumMinutes + " seconds " + sumSeconds);
            if (sumSeconds < 10){

                currentGame.setSum(sumMinutes+".0"+sumSeconds);
            }else {currentGame.setSum(sumMinutes+"."+sumSeconds);}
            currentGame.setFinished(true);
            currentGame.setFinish(date);
            gameRepo.save(currentGame);
            List<Element> elements = currentGame.getQuest().getFinalPageElements();
            model.put("elements" , elements);
            return "preview-page";
        }
        List<Element> elements = elementRepo.FindByPageGame(currentPageGame);
        String[] arrTimeLimitCounter=String.valueOf(currentPageGame.getTime()).split("\\.");
        //change
        long diffInMillies = Math.abs(new Date(System.currentTimeMillis()).getTime()- currentPageGame.getStart().getTime());
        long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if (currentGame.getPauseStart()!= null && currentGame.getPauseFinish()!=null){
            long diffInMilliesPause = Math.abs(currentGame.getPauseFinish().getTime()- currentGame.getPauseStart().getTime());
            long diffPause = TimeUnit.SECONDS.convert(diffInMilliesPause, TimeUnit.MILLISECONDS);
            diff = diff - diffPause;
        }
        int sumMinutes = (int) diff/60;
        int sumSeconds = (int) diff%60;
        currentPageGame.setTimeElapsed(Double.parseDouble(sumMinutes+"."+sumSeconds));
        String[] arrTimeElapsedCounter = String.valueOf(currentPageGame.getTimeElapsed()).split("\\.");
        if (arrTimeLimitCounter[1].length() == 1) arrTimeLimitCounter[1] = arrTimeLimitCounter[1]+"0";
        if (arrTimeElapsedCounter[1].length() == 1 && !arrTimeElapsedCounter[1].equals("0")) arrTimeElapsedCounter[1] = arrTimeElapsedCounter[1] +"0";
        List<Hint> hints = hintRepo.FindByPageGame(currentPageGame);
        List<Hint> shownHints = new ArrayList<>();
        for (Hint h : hints){
            if (h.getTime()< currentPageGame.getTimeElapsed()){

                shownHints.add(h);

                if (hints.isEmpty()) break;
            }
        }
        if (!hints.isEmpty())hints = hints.stream().filter(i-> i.getTime() > 0).collect(Collectors.toList());
        if (!shownHints.isEmpty()){model.put("shownHints", shownHints);}
        model.put("teamName", currentTeam.getTeamName());
        model.put("pageName", currentPageGame.getName());
        model.put("pageNumber", currentPageGame.getOrderNumber());
        model.put("hints", hints);
        model.put("elements", elements);
        model.put("code", code);
        //model.put("timeLimit", currentPageGame.getTime() - currentPageGame.getTimeElapsed());
        model.put("minsElapsed", arrTimeElapsedCounter[0]);
        model.put("secondsElapsed", arrTimeElapsedCounter[1]);
        if ( Double.parseDouble(arrTimeLimitCounter[1])-Double.parseDouble(arrTimeElapsedCounter[1])>0) {
            model.put("timeLimitCounterMinutes", Double.parseDouble(arrTimeLimitCounter[0]) - Double.parseDouble(arrTimeElapsedCounter[0]));
            model.put("timeLimitCounterSeconds", Double.parseDouble(arrTimeLimitCounter[1]) - Double.parseDouble(arrTimeElapsedCounter[1]));
        }else {
            model.put("timeLimitCounterMinutes", Double.parseDouble(arrTimeLimitCounter[0]) - Double.parseDouble(arrTimeElapsedCounter[0]) -1);
            model.put("timeLimitCounterSeconds", Double.parseDouble(arrTimeLimitCounter[1])+60 - Double.parseDouble(arrTimeElapsedCounter[1]));
        }
        if (answerRepo.FindByPageGame(currentPageGame).stream().anyMatch(Answer::isFound)) {
            List<Answer> answers = (answerRepo.FindByPageGame(currentPageGame).stream().filter(Answer::isFound).collect(Collectors.toList()));
            model.put("multiQuestionAnswers", answers);
        }
        int size = answerRepo.FindByPageGame(currentPageGame).size();
        model.put("numberOfAnswers", size);
        model.put("newMessageAlert", currentTeam.isCheckedUser());
        return "game";
    }
}
