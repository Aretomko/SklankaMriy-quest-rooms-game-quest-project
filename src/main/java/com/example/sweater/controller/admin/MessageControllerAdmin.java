package com.example.sweater.controller.admin;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.Team;
import com.example.sweater.repos.MessageRepo;
import com.example.sweater.repos.TeamRepo;
import com.example.sweater.service.FileService;
import com.example.sweater.service.GameIterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
public class MessageControllerAdmin {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private GameIterationService gameIterationService;
    @Autowired
    private FileService fileService;
    @Autowired
    private TeamRepo teamRepo;
    @PostMapping("/admin/addMessage/{code}")
    public String addMessageAdmin(@PathVariable String code,
                                  @RequestParam String text,
                                  @RequestParam(value = "file", required = false) MultipartFile file,
                                  Map<String, Object> model) throws IOException {
        Message message;
        Team team = teamRepo.findByCode(code);
        if (team == null) return gameIterationService.iteration(model, code);
        if(file == null){
            message = new Message(team,text, null, false);
        }else {
            String resultFilename = fileService.fileService(file);
            message = new Message(team,text, resultFilename, false);
        }
        messageRepo.save(message);
        //hiding the alert of a new message
        team.setChecked(true);
        team.setCheckedUser(false);
        teamRepo.save(team);
        //sorting messages by id, id is increasing so the order will be mantained
        List<Message> messages = team.getMessages();
        messages.sort(Comparator.comparing(Message::getId));
        model.put("messages", messages);
        model.put("code", team.getCode());
        model.put("teamName", team.getTeamName());
        return "messages";
    }
    @GetMapping("/admin/getMessages/{code}")
    public String getMessages(@PathVariable String code,
                              Map<String, Object> model){
        Team team = teamRepo.findByCode(code);
        if (team == null) return gameIterationService.iteration(model, code);
        List<Message> messages = team.getMessages();
        //hiding the alert of a new message
        team.setChecked(true);
        teamRepo.save(team);
        messages.sort(Comparator.comparing(Message::getId));
        model.put("messages", messages);
        model.put("code", team.getCode());
        model.put("teamName", team.getTeamName());
        return "messages";
    }
    @CrossOrigin
    @GetMapping("/getMessagesRest/{code}")
    public String getMessagesRest(@PathVariable String code,
                                  Map<String, Object> model){
        Team team = teamRepo.findByCode(code);
        if (team == null) return gameIterationService.iteration(model, code);
        List<Message> messages = team.getMessages();
        messages.sort(Comparator.comparing(Message::getId));
        team.setCheckedUser(true);
        teamRepo.save(team);
        model.put("messages", messages);
        model.put("code", team.getCode());
        return "messagesrest";
    }
    @CrossOrigin
    @GetMapping("/admin/getMessagesRest/{code}")
    public String getMessagesAdminRest(@PathVariable String code,
                                  Map<String, Object> model){
        Team team = teamRepo.findByCode(code);
        if (team == null) return gameIterationService.iteration(model, code);
        List<Message> messages = team.getMessages();
        messages.sort(Comparator.comparing(Message::getId));
        model.put("messages", messages);
        model.put("code", team.getCode());
        return "messages-rest-admin";
    }
}
