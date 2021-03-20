package com.example.sweater.controller.admin.admin;

import com.example.sweater.entities.Element;
import com.example.sweater.entities.Hint;
import com.example.sweater.entities.Page;
import com.example.sweater.entities.Quest;
import com.example.sweater.repos.repos.ElementRepo;
import com.example.sweater.repos.repos.HintRepo;
import com.example.sweater.repos.repos.PageRepo;
import com.example.sweater.repos.repos.QuestRepo;
import com.example.sweater.service.service.NumbersValidation;
import com.example.sweater.service.service.UpdateListOfPagesService;
import com.example.sweater.service.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class PageControllerAdmin {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private QuestRepo questRepo;
    @Autowired
    private PageRepo pageRepo;
    @Autowired
    private ElementRepo elementRepo;
    @Autowired
    private HintRepo hintRepo;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private NumbersValidation numbersValidation;
    @Autowired
    private UpdateListOfPagesService updateListOfPagesService;


    @GetMapping("/admin/listOfPages/{questId}")
    public String listOfPagesForQuest(@PathVariable String questId,
                                      Map<String , Object> model){
        questId = validationService.validateId(questId);
        if (questRepo.findById(Long.valueOf(questId)).isPresent()) {
            Quest currentQuest = questRepo.findById(Long.valueOf(questId)).get();
            List<Page> pages = pageRepo.findByQuest(currentQuest);
            pages.sort(Comparator.comparing(Page::getOrderNumber));
            model.put("questName", currentQuest.getName());
            model.put("questId", String.valueOf(currentQuest.getId()));
            model.put("pages", pages);
        }else {
            model.put("quests", questRepo.findAll());
            return "quests-admin";
        }
        return "pages-of-quest";
    }
    @PostMapping("/admin/addNewPage/{questId}")
    public String addNewPageAdmin(@RequestParam String number,
                                  @RequestParam String time,
                                  @RequestParam String name,
                                  @PathVariable String questId,
                                  Map<String, Object> model){
        Page page;
        Quest quest;
        questId = validationService.validateId(questId);
        if ( questRepo.findById(Long.valueOf(questId)).isPresent()) {
            quest = questRepo.findById(Long.valueOf(questId)).get();
        }else {
            return "redirect:/admin/quests";
        }
        List<Page> pages = pageRepo.findByQuest(quest);
        // validating time input
        double doubleTime = numbersValidation.validateDoubleTimePage(model, time);
        if (doubleTime==0.00){
            model.put("questName", quest.getName());
            model.put("questId", String.valueOf(quest.getId()));
            model.put("pages", pages);
            return "pages-of-quest";
        }
        //validating order number input
        int orderNumber = numbersValidation.validationOrderNumberPage(model, number);
        if (orderNumber == 0){
            model.put("questName", quest.getName());
            model.put("questId", String.valueOf(quest.getId()));
            model.put("pages", pages);
            return "pages-of-quest";
        }

        page = new Page(orderNumber, quest, doubleTime, name);
        //updating order numbers of pages in the list if we inserting a nrw element on the top or in the midle of the list
        updateListOfPagesService.updateListAdd(pages, page);
        pageRepo.save(page);
        pages = pageRepo.findByQuest(quest);
        model.put("questName", quest.getName());
        model.put("questId", String.valueOf(quest.getId()));
        model.put("pages", pages);
        return "pages-of-quest";
    }
    @GetMapping("/admin/deletePage/{pageId}")
    public String deletePageById(@PathVariable String pageId,
                                 Map<String,Object> model){
        Page page;
        Quest quest;
        pageId = validationService.validateId(pageId);
        List<Page> pagesForReturn = new ArrayList<>();
        if ( pageRepo.findById(Long.valueOf(pageId)).isPresent()) {
            page = pageRepo.findById(Long.valueOf(pageId)).get();
            quest = page.getQuest();
        }else return "redirect:/admin/quests";
        List<Element> elementsToDelete = elementRepo.FindByPage(page);
        for(Element e : elementsToDelete){
            if (!e.isText()){
                File file = new File(uploadPath+"/"+e.getFileName());
                file.delete();
            }
        }
        List<Hint> hintsToDelete = hintRepo.FindByPage(page);
        List<Page> pages = pageRepo.findByQuest(quest);
        if (pages.stream().anyMatch(i -> i.getOrderNumber() == page.getOrderNumber())) {
            List<Page> elementsToUpdate = pages.stream().filter(i->i.getOrderNumber() > page.getOrderNumber()).collect(Collectors.toList());
            for (Page e : elementsToUpdate) {
                e.setOrderNumber(e.getOrderNumber()-1);
                pageRepo.save(e);
            }
            elementsToUpdate.sort(Comparator.comparing(Page::getOrderNumber));
            pagesForReturn = elementsToUpdate;
        }
        elementRepo.deleteAll(elementsToDelete);
        hintRepo.deleteAll(hintsToDelete);
        pageRepo.delete(page);
        model.put("questName", quest.getName());
        model.put("questId", String.valueOf(quest.getId()));
        model.put("pages", pagesForReturn);
        return "pages-of-quest";
    }
    @PostMapping("/admin/updatePageConfirm/{pageId}")
    public String updatePageConfirm(@RequestParam String number,
                             @RequestParam String time,
                             @RequestParam String name,
                             @PathVariable String pageId,
                             Map<String, Object> model){
        Page currentPage;
        pageId = validationService.validateId(pageId);
        if(pageRepo.findById(Long.valueOf(pageId)).isPresent()){
            currentPage = pageRepo.findById(Long.valueOf(pageId)).get();
        }else return "redirect:/admin/quests";
        Quest quest = currentPage.getQuest();
        if(time.length() == 3 ) time = time.concat("0");
        currentPage.setTime(Double.parseDouble(time));
        currentPage.setName(name);
        currentPage.setOrderNumber(Integer.parseInt(number));
        List<Page> pages = pageRepo.findByQuest(quest);
        //updating order numbers of pages in the list if we inserting a nrw element on the top or in the midle of the list
        //updateListOfPagesService.updateListAdd(pages, currentPage);

        pageRepo.save(currentPage);
        return "redirect:/admin/listOfPages/"+quest.getId();
    }
    @GetMapping("/admin/updatePage/{pageId}")
    public String updatePage(@PathVariable String pageId,
                             Map<String, Object> model){
        Page currentPage;
        pageId = validationService.validateId(pageId);
        if(pageRepo.findById(Long.valueOf(pageId)).isPresent()){
            currentPage = pageRepo.findById(Long.valueOf(pageId)).get();
        }else return "redirect:/admin/quests";
        model.put("pages", new ArrayList<Page>(Collections.singletonList(currentPage)));
        return "edit-page";
    }

}
