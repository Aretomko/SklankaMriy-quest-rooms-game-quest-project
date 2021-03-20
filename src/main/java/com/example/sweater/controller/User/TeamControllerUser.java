package com.example.sweater.controller.User;

import com.example.sweater.entities.Team;
import com.example.sweater.repos.repos.QuestRepo;
import com.example.sweater.repos.repos.TeamRepo;
import com.example.sweater.service.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class TeamControllerUser {
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private QuestRepo questRepo;
    @Autowired
    private ValidationService validationService;

    @GetMapping("/registration-of-team/{questId}")
    public String getTeamRegistrationPage(@PathVariable String questId,
                                          Map<String, Object> model)
    {
        questId = validationService.validateId(questId);
        model.put("questId", questId);
        return "registration-of-team-user";
    }

    @PostMapping("/addNewTeamUser/{questId}")
    public String addNewTeamByUser(@RequestParam String teamName,
                                   @RequestParam String capName,
                                   @RequestParam String capNumber,
                                   @RequestParam String secondCapName,
                                   @RequestParam String secondCapNumber,
                                   @RequestParam String quantityOfPlayers,
                                   @PathVariable String questId,
                                   Map<String, Object> model){
        Team createdTeam;
        questId = validationService.validateId(questId);
        if (questRepo.findById(Long.valueOf(questId)).isPresent()){
            createdTeam = new Team(teamName, capName, capNumber, secondCapName, secondCapNumber, quantityOfPlayers, questRepo.findById(Long.valueOf(questId)).get());
        }else {
            return "registration-of-team-user";
        }
        //validating users input, if validation is successful the output will be conformation for user if not - the wrong fields will be highlighted
        boolean successful = validationService.validateTeamCreationUser(model, createdTeam);
        if (successful){
            teamRepo.save(createdTeam);
            return "registration-of-team-user-successful";
        }
        return "registration-of-team-user";
    }
}
