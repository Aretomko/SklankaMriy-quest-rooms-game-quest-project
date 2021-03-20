package com.example.sweater.controller.admin.admin;

import com.example.sweater.entities.Hint;
import com.example.sweater.entities.Page;
import com.example.sweater.repos.repos.HintRepo;
import com.example.sweater.repos.repos.PageRepo;
import com.example.sweater.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
public class HintControllerAdmin {
    @Autowired
    private PageRepo pageRepo;
    @Autowired
    private HintRepo hintRepo;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileValidation fileValidation;
    @Autowired
    private NumbersValidation integerNumbersValidation;
    @Autowired
    private UpdateListOfHintsService updateListOfHintsService;

    @GetMapping("/admin/hints/{pageId}")
    public String getHints(@PathVariable String pageId,
                           Map<String, Object> model) {
        Page page ;
        Set<Hint> hints;
        pageId = validationService.validateId(pageId);
        if (pageRepo.findById(Long.valueOf(pageId)).isPresent()){
            page = pageRepo.findById(Long.valueOf(pageId)).get();
            hints = page.getHints();
        }else {
            return "redirect:/admin/quests";
        }
        model.put("hints", hints);
        model.put("pageId", pageId);
        model.put("questId", page.getQuest().getId());
        return "hints";
    }

    @PostMapping("/admin/addNewHintFile/{pageId}")
    public String addNewElementFileAdmin(@PathVariable String pageId,
                                         @RequestParam String time,
                                         @RequestParam(value = "file", required = false) MultipartFile file,
                                         Map<String, Object> model) throws IOException {

        List<Hint> hints;
        Page page;
        pageId = validationService.validateId(pageId);
        if (pageRepo.findById(Long.valueOf(pageId)).isPresent()) {
            page = pageRepo.findById(Long.valueOf(pageId)).get();
            hints = hintRepo.FindByPage(page);
        }else {
            return "redirect:/admin/quests";
        }
        //Validating the "number" parameter to be only integer, if it is not - return error message
        int orderNumber = integerNumbersValidation.validateOrderNumberIntegerHints(model,pageId,hints,"0");
        //Updating all hints and increasing their order number if we need to put a new hints as first or in the middle;
        updateListOfHintsService.changeOrderIfIndexExistAdd(hints, orderNumber);
        String resultFilename = fileService.fileService(file);
        //finding the type of the file
        boolean isVideo = fileValidation.isVideo(resultFilename);
        boolean isImage = fileValidation.isImage(resultFilename);
        //wrong file
        if (isVideo == isImage) {
            model.put("fileError", "Незнайомий формат(");
            model.put("pageId", pageId);
            model.put("hints", hints);
            model.put("questId", page.getQuest().getId());
        }
        Hint newHint = new Hint(false, isVideo, isImage, resultFilename, orderNumber, Integer.parseInt(time), pageRepo.findById(Long.valueOf(pageId)).get());
        hintRepo.save(newHint);
        hints = hintRepo.FindByPage(pageRepo.findById(Long.valueOf(pageId)).get());
        model.put("pageId", pageId);
        model.put("hints", hints);
        model.put("questId", page.getQuest().getId());
        return "hints";
    }
    @PostMapping("/admin/addNewHintText/{pageId}")
    public String addNewHintText(@PathVariable String pageId,
                                    @RequestParam String text,
                                    @RequestParam String time,
                                    Map<String, Object> model){
        List<Hint> hints;
        Page page;
        pageId = validationService.validateId(pageId);
        if (pageRepo.findById(Long.valueOf(pageId)).isPresent()) {
            page = pageRepo.findById(Long.valueOf(pageId)).get();
            hints = hintRepo.FindByPage(page);
        }else {
            return "redirect:/admin/quests";
        }
        //Validating the "time" parameter to be only integer, if not - return error message
        int intTime =  integerNumbersValidation.validateTimeInteger(model,pageId,hints,time);
        //Validating the "number" parameter to be only integer, if it is not - return error message
        int orderNumber = integerNumbersValidation.validateOrderNumberIntegerHints(model,pageId,hints,"0");
        //Updating all hints and increasing their order number if we need to put a new hints as first or in the middle;
        updateListOfHintsService.changeOrderIfIndexExistAdd(hints, orderNumber);
        Hint newHint = new Hint(true, false, false, text, orderNumber,intTime, pageRepo.findById(Long.valueOf(pageId)).get());
        hintRepo.save(newHint);
        hints = hintRepo.FindByPage(pageRepo.findById(Long.valueOf(pageId)).get());
        model.put("pageId", pageId);
        model.put("hints", hints);
        model.put("questId", page.getQuest().getId());
        return "hints";
    }
    @GetMapping("/admin/deleteHint/{hintId}/{pageId}")
    public String deleteHint(@PathVariable String hintId,
                             @PathVariable String pageId,
                             Map<String , Object> model){
        Page page;
        pageId = validationService.validateId(pageId);
        if (pageRepo.findById(Long.valueOf(pageId)).isPresent()) {
            page = pageRepo.findById(Long.valueOf(pageId)).get();
        }else {return "redirect:/admin/hints/"+pageId;}
        List<Hint> hints = hintRepo.FindByPage(page);
        Hint hintToDelete;
        if(hintRepo.findById(Long.valueOf(hintId)).isPresent()) {
            hintToDelete = hintRepo.findById(Long.valueOf(hintId)).get();
        }else {return "redirect:/admin/hints/"+pageId;}
        //Updating all hints and increasing their order number if we need to put a new hints as first or in the middle;
        updateListOfHintsService.changeOrderIfIndexExistDelete(hints, hintToDelete);
        hintRepo.delete(hintToDelete);
        hints = hintRepo.FindByPage(pageRepo.findById(Long.valueOf(pageId)).get());
        model.put("pageId", pageId);
        model.put("hints", hints);
        model.put("questId", page.getQuest().getId());
        return "hints";
    }
    @GetMapping("/admin/editHintText/{hintId}")
    public String editHint(@PathVariable String hintId,
                           Map<String,Object> model){
        Hint hint;
        hintId = validationService.validateId(hintId);
        if(hintRepo.findById(Long.valueOf(hintId)).isPresent()) {
            hint = hintRepo.findById(Long.valueOf(hintId)).get();
        }else {return "redirect:/admin/quests";}
        model.put("hints" , new ArrayList<Hint>(Collections.singleton(hint)));
        return "edit-hint";
    }
    @GetMapping("/admin/editHintFile/{hintId}")
    public String editHintFile(@PathVariable String hintId,
                           Map<String,Object> model){
        Hint hint;
        hintId = validationService.validateId(hintId);
        if(hintRepo.findById(Long.valueOf(hintId)).isPresent()) {
            hint = hintRepo.findById(Long.valueOf(hintId)).get();
        }else {return "redirect:/admin/quests";}
        model.put("hints" , new ArrayList<Hint>(Collections.singleton(hint)));
        return "edit-hint-file";
    }
    @PostMapping("/admin/editHintConfirmFile/{hintId}")
    public String hintFileEditConfirm(@PathVariable String hintId,
                                         @RequestParam String time,
                                         @RequestParam(value = "file", required = false) MultipartFile file,
                                         Map<String, Object> model) throws IOException {
        Hint hint;
        hintId = validationService.validateId(hintId);
        if(hintRepo.findById(Long.valueOf(hintId)).isPresent()) {
            hint = hintRepo.findById(Long.valueOf(hintId)).get();
        }else {return "redirect:/admin/quests";}
        List<Hint> hints = hintRepo.FindByPage(hint.getPage());
        //Validating the "number" parameter to be only integer, if it is not - return error message
        int orderNumber = integerNumbersValidation.validateOrderNumberIntegerHints(model,String.valueOf(hint.getPage().getId()),hints,"0");
        String resultFilename = fileService.fileService(file);
        if (!resultFilename.equals("")){
            hint.setFileName(resultFilename);
        }
        hint.setTime(Integer.parseInt(time));
        hintRepo.save(hint);
        hints = hintRepo.FindByPage(pageRepo.findById(hint.getPage().getId()).get());
        model.put("pageId", hint.getPage().getId());
        model.put("hints", hints);
        model.put("questId", hint.getPage().getQuest().getId());
        return "hints";
    }
    @PostMapping("/admin/editHintConfirmText/{hintId}")
    public String editHintConfirmText(@PathVariable String hintId,
                                    @RequestParam String text,
                                    @RequestParam String time,
                                    Map<String, Object> model){
        Hint hint;
        hintId = validationService.validateId(hintId);
        if(hintRepo.findById(Long.valueOf(hintId)).isPresent()) {
            hint = hintRepo.findById(Long.valueOf(hintId)).get();
        }else {return "redirect:/admin/quests";}
        Page page = hint.getPage();
        hint.setString(text);
        hint.setTime(Integer.parseInt(time));
        hintRepo.save(hint);
        model.put("pageId", page.getId());
        model.put("hints", page.getHints());
        model.put("questId", page.getQuest().getId());
        return "hints";
    }
}
