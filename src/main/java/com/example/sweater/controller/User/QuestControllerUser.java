package com.example.sweater.controller.User;

import com.example.sweater.entities.Quest;
import com.example.sweater.repos.repos.QuestRepo;
import com.example.sweater.service.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.Map;

@Controller
public class QuestControllerUser {
    @Autowired
    private QuestRepo questRepo;
    @Autowired
    private ValidationService validationService;

    @GetMapping("/quests")
    public String returnAllQuests(Map<String, Object> model) {
        model.put("quests", questRepo.findAll());
        return "quests";
    }

    @GetMapping("/moreInfo/{questId}")
    public String moreInfoQuest(@PathVariable String questId,
                                Map<String, Object> model){
        LinkedList<Quest> quest = new LinkedList<>();
        questId = validationService.validateId(questId);
        if (questRepo.findById(Long.valueOf(questId)).isPresent()) {
            quest.add(questRepo.findById(Long.valueOf(questId)).get());
        }else {
            return "redirect:/quests";
        }
        model.put("quests", quest);
        return "more-info-quest";
    }
}
