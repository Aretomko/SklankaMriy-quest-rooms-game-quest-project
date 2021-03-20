package com.example.sweater.controller.User;

import com.example.sweater.entities.Baner;
import com.example.sweater.entities.Quest;
import com.example.sweater.repos.repos.BanerRepo;
import com.example.sweater.repos.repos.QuestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private QuestRepo questRepo;
    @Autowired
    private BanerRepo banerRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) throws InterruptedException {
        List<Quest> allQuests = questRepo.findAll();
        model.put("quests", allQuests);
        List<Baner> baners =   banerRepo.findAll();
        model.put("baners", baners);
        return "home";
    }

}
