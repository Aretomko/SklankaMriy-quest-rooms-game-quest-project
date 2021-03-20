package com.example.sweater.controller.User;

import com.example.sweater.entities.Message;
import com.example.sweater.entities.Team;
import com.example.sweater.repos.repos.MessageRepo;
import com.example.sweater.repos.repos.TeamRepo;
import com.example.sweater.service.service.FileService;
import com.example.sweater.service.service.GameIterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
public class MessageControllerUser {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private GameIterationService gameIterationService;
    @Autowired
    private FileService fileService;
    @Autowired
    private TeamRepo teamRepo;

    @GetMapping("/chat/{code}")
    public String chat(@PathVariable String code,
                       Map<String, Object>model){
        Team team = teamRepo.findByCode(code);
        if(team == null) return "redirect:/";
        List<Message> messages = team.getMessages();
        //sorting messages by id, id is always  increasing so the order will be maintained
        messages.sort(Comparator.comparing(Message::getId));
        team.setCheckedUser(true);
        teamRepo.save(team);
        model.put("messages", messages);
        model.put("code", team.getCode());
        return "chat-user";
    }

    @PostMapping("/chat/addMessage/{code}")
    public String addMessage(@PathVariable String code,
                             @RequestParam String text,
                             @RequestParam(value = "file", required = false) MultipartFile file,
                             Map<String, Object>model) throws IOException {
        Message message;
        Team team = teamRepo.findByCode(code);
        if (team == null) return gameIterationService.iteration(model, code);
        // if file was attached we save the file with the message, if not we save only text
        if(file == null){
             message = new Message(team,text, null, true);
        }else {
            String resultFilename = fileService.fileService(file);
             message = new Message(team,text, resultFilename, true);
        }
        //rising the alert in the administration panel
        team.setChecked(false);
        teamRepo.save(team);
        messageRepo.save(message);
        List<Message> messages = team.getMessages();
        //sorting messages by id, id is always  increasing so the order will be maintained
        messages.sort(Comparator.comparing(Message::getId));
        model.put("messages", messages);
        model.put("code", team.getCode());
        return "chat-user";
    }
    @CrossOrigin
    @GetMapping("/getButtonGame/{code}")
    public String getButtonGame(@PathVariable String code,
                                Map<String, Object> model){
        Message message;
        Team team = teamRepo.findByCode(code);
        if (team == null) return gameIterationService.iteration(model, code);
        model.put("newMessageAlert", team.isCheckedUser());
        return "chat-button-game";
    }
}
