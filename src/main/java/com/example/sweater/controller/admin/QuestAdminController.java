package com.example.sweater.controller.admin;

import com.example.sweater.domain.*;
import com.example.sweater.repos.*;
import com.example.sweater.service.FileService;
import com.example.sweater.service.FileValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class QuestAdminController {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private QuestRepo questRepo;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileValidation fileValidation;
    @Autowired
    private PageRepo pageRepo;
    @Autowired
    private ElementRepo elementRepo;
    @Autowired
    private HintRepo hintRepo;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private TeamRepo teamRepo;

    @GetMapping("/admin/quests")
    public String addNewQuestFormAdmin(Map<String, Object> model){
        model.put("quests" , questRepo.findAll());
        return "quests-admin";
    }

    @PostMapping("/admin/addNewQuest")
    public String addNewQuestAdmin(@RequestParam String name,
                                   @RequestParam String place,
                                   @RequestParam String shortDescription,
                                   @RequestParam String description,
                                   @RequestParam(value = "file", required = false) MultipartFile file,
                                   @RequestParam(value = "fileSmall", required = false) MultipartFile fileSmall,
                                   Map<String , Object> model) throws IOException {
        String resultFilename = fileService.fileService(file);
        String resultFilenameSmall = fileService.fileService(fileSmall);
        //validating images , checking weather they are images
        if (fileValidation.isImage(resultFilename) && fileValidation.isImage(resultFilenameSmall)) {
            Quest createdQuest = new Quest(place, name, shortDescription, description, resultFilename, resultFilenameSmall);
            questRepo.save(createdQuest);
        }else {
            model.put("fileError", "file is not an image");
        }
        model.put("quests", questRepo.findAll());
        return "quests-admin";
    }
    @GetMapping("/admin/deleteQuest/{id}")
    public String deleteQuestByIdAdmin(@PathVariable String id,
                                       Map<String, Object> model){
        Quest quest;
        List<Page> pages;
        List<Element> elementsToDelete = new ArrayList<>();
        List<Hint> hintsToDelete =  new ArrayList<>();
        List<Team> teamsToDelete = new ArrayList<>();
        List<Message> messagesToDelete = new ArrayList<>();

        if (questRepo.findById(Long.valueOf(id)).isPresent()) {
           quest = questRepo.findById(Long.valueOf(id)).get();
           pages = pageRepo.findByQuest(quest);
           //finding pages and hints of a page, adding them to the collections to be then deleted, checking weather they contain
           for (Page p : pages) {
               for (Element e :p.getElements()){
                   elementsToDelete.add(e);
                   if (e.getFileName() == null){
                       File file = new File(uploadPath+"/"+e.getFileName());
                       file.delete();
                   }
               }
               for (Hint h :  p.getHints()){
                   hintsToDelete.add(h);
                   if (h.getFileName() == null){
                       File file = new File(uploadPath+"/"+h.getFileName());
                       file.delete();
                   }
               }
               for (Team t : quest.getTeams()){
                   teamsToDelete.add(t);
                   for (Message m : t.getMessages()){
                       messagesToDelete.add(m);
                       if(m.getFilename() == null){
                           File file = new File(uploadPath+"/"+m.getFilename());
                           file.delete();
                       }
                   }
               }
           }
        }else return "redirect:/admin/quests";
        //deleting all the staff related to the quest
        messageRepo.deleteAll(messagesToDelete);
        teamRepo.deleteAll(teamsToDelete);
        elementRepo.deleteAll(elementsToDelete);
        hintRepo.deleteAll(hintsToDelete);
        pageRepo.deleteAll(pages);
        //deleting images of the quest
        File file = new File(uploadPath+"/"+quest.getFilename());
        File fileSmall = new File(uploadPath+"/"+quest.getFilenameSmall());
        file.delete();
        fileSmall.delete();
        questRepo.delete(quest);
        model.put("quests", questRepo.findAll());
        return "quests-admin";
    }
    @GetMapping("/admin/updateQuest/{questId}")
    public String updateQuest(@PathVariable String questId,
                              Map<String, Object> model){
        Quest quest;
        if (questRepo.findById(Long.valueOf(questId)).isPresent()){
            quest = questRepo.findById(Long.valueOf(questId)).get();
        }else return "redirect:/admin/quests";
        //outputting only one quest which we need to update;
        model.put("quests", new ArrayList<Quest>(Collections.singleton(quest)));
        return "edit-quest";
    }
    @PostMapping("/admin/editQuestSubmit/{questId}")
    public String editQuestSubmit(@RequestParam String name,
                                  @RequestParam String place,
                                  @RequestParam String shortDescription,
                                  @RequestParam String description,
                                  @RequestParam(value = "file", required = false) MultipartFile file,
                                  @RequestParam(value = "fileSmall", required = false) MultipartFile fileSmall,
                                  @PathVariable String questId,
                                  Map<String , Object> model) throws IOException {
        Quest quest;
        String resultFilename;
        String resultFilenameSmall;
        if (questRepo.findById(Long.valueOf(questId)).isPresent()){
            quest = questRepo.findById(Long.valueOf(questId)).get();
        }else return "redirect:/admin/quests";
        //changing the picture only if there was one uploaded, if the was no we will left the previous
        if (file.getOriginalFilename().equals("")){
            resultFilename = quest.getFilename();
        }else {
            File fileToDelete = new File(uploadPath+"/"+quest.getFilename());
            resultFilename = fileService.fileService(file);
            fileToDelete.delete();
        }
        //changing the picture only if there was one uploaded, if the was no we will left the previous
        if(fileSmall.getOriginalFilename().equals("")){
            resultFilenameSmall = quest.getFilenameSmall();
        }else{
            File fileToDelete = new File(uploadPath+"/"+quest.getFilenameSmall());
            resultFilenameSmall = fileService.fileService(fileSmall);
            fileToDelete.delete();
        }
        //setting new values
        quest.setPlace(place);
        quest.setName(name);
        quest.setDescription(description);
        quest.setShortDescription(shortDescription);
        quest.setFilename(resultFilename);
        quest.setFilenameSmall(resultFilenameSmall);
        questRepo.save(quest);
        return "redirect:/admin/quests";
    }
}
