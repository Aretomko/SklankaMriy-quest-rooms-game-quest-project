package com.example.sweater.controller.admin;

import com.example.sweater.domain.Game;
import com.example.sweater.domain.Page;
import com.example.sweater.domain.PageGame;
import com.example.sweater.domain.Quest;
import com.example.sweater.repos.GameRepo;
import com.example.sweater.repos.QuestRepo;
import freemarker.template.utility.DateUtil;
import org.flywaydb.core.internal.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
public class StatisticsControllerAdmin {
    @Autowired
    private QuestRepo questRepo;
    @Autowired
    private GameRepo gameRepo;

    @GetMapping("/admin/getStatistics")
    public String getStatistics(Map<String , Object> model){
        List<Quest> quests = questRepo.findAll();
        model.put("quests", quests);
        return "statistics";
    }
    @GetMapping("/admin/getStatistics/{questId}")
    public String getStatisticsById(@PathVariable String questId,
                                    Map<String, Object> model){
        Quest quest;
        if (questRepo.findById(Long.valueOf(questId)).isPresent()){
            quest = questRepo.findById(Long.valueOf(questId)).get();
        }else return "redirect;/admin/getStatistics";
        List<Page> pages = quest.getPages();
        pages = pages.stream().sorted(Comparator.comparing(Page::getOrderNumber)).collect(Collectors.toList());
        //outputting oly one quest that we need to show
        for (Game game: quest.getGames()) {
                try {
                    if (game.getFinish() != null) {
                        String[] arr = String.valueOf(Double.parseDouble(game.getSum())).split("\\.");
                        long diffInMillies = Math.abs(game.getFinish().getTime() - game.getStart().getTime());
                        long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                        long sumReal = Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
                        if (diff < sumReal - 60) {
                            Calendar c = Calendar.getInstance();
                            c.setTime(game.getStart());
                            c.add(Calendar.MINUTE, Integer.parseInt(arr[0]));
                            c.add(Calendar.SECOND, Integer.parseInt(arr[1]));
                            game.setFinish(c.getTime());
                            gameRepo.save(game);
                            return "redirect:/admin/getStatistics/" + questId;
                        }
                        if(game.getPause() != null){
                            String[] arrPause = String.valueOf(Double.parseDouble(game.getPause())).split("\\.");
                            int sumPause= Integer.parseInt(arrPause[0])*60 + Integer.parseInt(arrPause[1]);
                            if(diff-30 > sumReal + sumPause){
                                int realPause= (int) ((int) diff-sumReal);
                                int sumMinutes = (int) realPause/60;
                                int sumSeconds = (int) realPause%60;
                                game.setPause(sumMinutes+"."+sumSeconds);
                                gameRepo.save(game);
                                return "redirect:/admin/getStatistics/" + questId;
                            }
                        }
                    }
                }catch (NullPointerException ex){
                    return "redirect:/admin/quest";
                }
        }
        List<Quest> questToShow = new ArrayList<Quest>(){};
        questToShow.add(questRepo.findById(Long.valueOf(questId)).get());
        model.put("pages", pages);
        model.put("quests", questToShow);
        return "statistics";
    }

}
