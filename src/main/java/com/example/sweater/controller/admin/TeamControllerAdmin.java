package com.example.sweater.controller.admin;

import com.example.sweater.domain.*;
import com.example.sweater.repos.*;
import com.example.sweater.service.GameIterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
public class TeamControllerAdmin {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private PageGameRepo pageGameRepo;
    @Autowired
    private ElementRepo elementRepo;
    @Autowired
    private QuestRepo questRepo;
    @Autowired
    private HintRepo hintRepo;
    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private GameIterationService gameIterationService;

    @GetMapping("/admin/getTeams")
    public String getAllTeamsAdmin(Map<String ,Object> model) {
        model.put("teams", teamRepo.findAll());
        return "teams";
    }
    @GetMapping("/admin/deleteTeam/{id}")
    public String deleteTeamByIdAdmin(@PathVariable String id,
                                      Map<String, Object> model){
        Game game;
        Team team;
        List<PageGame> pagesGame;
        try {
            team = teamRepo.findById(Long.valueOf(id)).get();
            game= team.getGame();
        }catch (NoSuchElementException e){
            model.put("teams", teamRepo.findAll());
            return "teams";
        }
        if (game != null){
            pagesGame = game.getPageGames();
            // deleting everything related to the team and all files related to this team
            for (PageGame p : pagesGame){
                for (Element e : p.getElements()){
                    elementRepo.delete(e);
                    if (e.getFileName() == null || e.getFileName().equals("")){
                        File fileToDelete = new File(uploadPath+"/"+e.getFileName());
                        fileToDelete.delete();
                    }
                }
                for(Hint h : p.getHints()){
                    hintRepo.delete(h);
                    if (h.getFileName() == null || h.getFileName().equals("")){
                        File fileToDelete = new File(uploadPath+"/"+h.getFileName());
                        fileToDelete.delete();
                    }
                }
                for (Answer a : answerRepo.FindByPageGame(p)){
                    answerRepo.delete(a);
                }
                for (Message m : team.getMessages()){
                    if (m.getFilename() == null || m.getFilename().equals("")){
                        File fileToDelete = new File(uploadPath+"/"+m.getFilename());
                        fileToDelete.delete();
                    }
                    messageRepo.delete(m);
                }
            }
        }else {
            teamRepo.delete(team);
            model.put("teams", teamRepo.findAll());
            return "teams";
        }
        pageGameRepo.deleteAll(pagesGame);
        gameRepo.delete(game);
        teamRepo.delete(team);
        model.put("teams", teamRepo.findAll());
        return "teams";
    }
    @GetMapping("/admin/getTeamsByQuest/{questId}")
    public String getTeamsByQuest(@PathVariable String questId,
                                  Map<String, Object> model){
        Quest quest;
        if(questRepo.findById(Long.valueOf(questId)).isPresent()){
            quest = questRepo.findById(Long.valueOf(questId)).get();
        } else return "redirect:/admin/getTeams";
        // outputting all teams related to the quest
        List<Team> teams = teamRepo.FindByQuest(quest);
        model.put("teams", teams);
        return "teams";
    }
    @GetMapping("/admin/pause/{teamId}")
    public String pause(@PathVariable String teamId,
                        Map<String,Object> model){
        Team team = teamRepo.findById(Long.valueOf(teamId)).get();
        Game game = team.getGame();
        Date date = new Date(System.currentTimeMillis());
        //turning pause on and setting pause start time or turning off and setting the end of the pause time
        if (game.isPaused()) {
            game.setPaused(false);
            game.setPauseFinish(date);
        }else {
            game.setPaused(true);
            game.setPauseStart(date);
        }
        gameRepo.save(game);
        //returning the same page
        model.put("teams", teamRepo.findAll());
        return "redirect:/admin/getTeams";
    }
    @GetMapping("/admin/finishGame/{teamId}")
    public String finishGameAdmin(@PathVariable String teamId,
                                  Map<String,Object> model){
        Team team;
        if (teamRepo.findById(Long.valueOf(teamId)).isPresent()) {
            team = teamRepo.findById(Long.valueOf(teamId)).get();
        }else return "redirect:/admin/getTeams";
        Game game;
        if (team.getGame() != null) {
            game = team.getGame();
        }else return "redirect:/admin/getTeams";
        Date date = new Date(System.currentTimeMillis());
        game.setFinish(date);
        for(PageGame pageGame : game.getPageGames()){
            pageGame.setAnswered(true);
        }
        gameIterationService.iteration(model, team.getCode());
        return "redirect:/admin/getTeams";
    }
}
