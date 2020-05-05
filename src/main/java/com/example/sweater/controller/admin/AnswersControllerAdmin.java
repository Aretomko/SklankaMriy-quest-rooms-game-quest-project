package com.example.sweater.controller.admin;

import com.example.sweater.domain.Answer;
import com.example.sweater.domain.Page;
import com.example.sweater.repos.AnswerRepo;
import com.example.sweater.repos.PageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AnswersControllerAdmin {
    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private PageRepo pageRepo;

    @GetMapping("/admin/answers/{pageId}")
    public String answers(@PathVariable String pageId,
                          Map<String, Object> model){
        Page currentPage;
        if(pageRepo.findById(Long.valueOf(pageId)).isPresent()){
            currentPage = pageRepo.findById(Long.valueOf(pageId)).get();
        }else return "redirect:/admin/quests";
        model.put("answers", answerRepo.FindByPage(currentPage));
        model.put("pageId", pageId);
        return "answers";
    }
    @PostMapping("/admin/addAnswer/{pageId}")
    public String addAnswer(@PathVariable String pageId,
                            @RequestParam String answer,
                            Map<String, Object> model){
        Page currentPage;
        if(pageRepo.findById(Long.valueOf(pageId)).isPresent()){
            currentPage = pageRepo.findById(Long.valueOf(pageId)).get();
        }else return "redirect:/admin/quests";
        Answer newAnswer = new Answer(currentPage, answer);
        answerRepo.save(newAnswer);
        model.put("answers", answerRepo.FindByPage(currentPage));
        model.put("pageId", pageId);
        return "answers";
    }
    @GetMapping("/admin/deleteAnswer/{ansId}/{pageId}")
    public String deleteAnswer(@PathVariable String ansId,
                               @PathVariable String pageId,
                               Map<String, Object> model){
        Answer answer;
        Page currentPage;
        if ( answerRepo.findById(Long.valueOf(ansId)).isPresent()) {
            answer = answerRepo.findById(Long.valueOf(ansId)).get();
        }else return  "redirect:/admin/quests";
        answerRepo.delete(answer);
        if(pageRepo.findById(Long.valueOf(pageId)).isPresent()){
            currentPage = pageRepo.findById(Long.valueOf(pageId)).get();
        }else return "redirect:/admin/quests";
        model.put("answers", answerRepo.FindByPage(currentPage));
        model.put("pageId", pageId);
        return "answers";
    }
}
