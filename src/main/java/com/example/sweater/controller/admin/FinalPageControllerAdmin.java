package com.example.sweater.controller.admin;

import com.example.sweater.domain.Element;
import com.example.sweater.domain.Quest;
import com.example.sweater.repos.ElementRepo;
import com.example.sweater.repos.QuestRepo;
import com.example.sweater.service.FileService;
import com.example.sweater.service.FileValidation;
import com.example.sweater.service.UpdateListOfElementsService;
import com.example.sweater.service.ValidationService;
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
import java.util.List;
import java.util.Map;

@Controller
public class FinalPageControllerAdmin {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private QuestRepo questRepo;
    @Autowired
    private ElementRepo elementRepo;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileValidation fileValidation;
    @Autowired
    private UpdateListOfElementsService updateListOfElementsService;
    @Autowired
    private ValidationService validationService;

    @GetMapping("/admin/finalPage/{questId}")
    public String finalPage(@PathVariable String questId,
                            Map<String, Object> model){
        Quest quest;
        if(questRepo.findById(Long.valueOf(questId)).isPresent()){
            quest = questRepo.findById(Long.valueOf(questId)).get();
        }else return "redirect:/admin/quests";
        model.put("elements", quest.getFinalPageElements());
        model.put("questId", quest.getId());

        return "final-page-elements";
    }
    @PostMapping("/admin/addFinalElementText/{questId}")
    public String addFinal(@PathVariable String questId,
                           @RequestParam String number,
                           @RequestParam String text,
                           Map<String, Object> model){
        Quest quest;
        if(questRepo.findById(Long.valueOf(questId)).isPresent()){
            quest = questRepo.findById(Long.valueOf(questId)).get();
        }else return "redirect:/admin/quests";
        List<Element> elements = quest.getFinalPageElements();
        //Updating all elements and increasing their order number if we need to put a new element as first or in the middle;
        updateListOfElementsService.changeOrderIfIndexExistAdd(elements, Integer.parseInt(number));
        Element element = new Element(true, false, false, text, Integer.parseInt(number), quest);
        elementRepo.save(element);
        model.put("elements", elementRepo.FindByQuest(quest));
        model.put("questId", quest.getId());
        return "final-page-elements";
    }
    @PostMapping("/admin/addFinalElementFile/{questId}")
    public String addFinalElementFile(@PathVariable String questId,
                                      @RequestParam String number,
                                      @RequestParam(value = "file", required = false) MultipartFile file,
                                      Map<String, Object> model) throws IOException {
        Quest quest;
        if(questRepo.findById(Long.valueOf(questId)).isPresent()){
            quest = questRepo.findById(Long.valueOf(questId)).get();
        }else return "redirect:/admin/quests";
        List<Element> elements = quest.getFinalPageElements();
        //Updating all elements and increasing their order number if we need to put a new element as first or in the middle;
        updateListOfElementsService.changeOrderIfIndexExistAdd(elements, Integer.parseInt(number));
        //saving the file into the memory producing the filename using UUID
        String resultFilename = fileService.fileService(file);
        //finding the type of the file
        boolean isVideo = fileValidation.isVideo(resultFilename);
        boolean isImage = fileValidation.isImage(resultFilename);
        Element newElement = new Element(false, isVideo, isImage, resultFilename, Integer.parseInt(number), quest);
        elementRepo.save(newElement);
        model.put("elements", elementRepo.FindByQuest(quest));
        model.put("questId", quest.getId());
        return "final-page-elements";
    }
    @GetMapping("/admin/previewFinalPage/{questId}")
    public String previewFinalPage(@PathVariable String questId,
                                   Map<String,Object> model){
        Quest quest;
        if(questRepo.findById(Long.valueOf(questId)).isPresent()){
            quest = questRepo.findById(Long.valueOf(questId)).get();
        }else return "redirect:/admin/quests";
        model.put("elements", quest.getFinalPageElements());
        return "preview-page";
    }
    @GetMapping("/admin/deleteFinalElement/{elementId}/{questId}")
    public String deleteElement(@PathVariable String elementId,
                                @PathVariable String questId,
                                Map<String, Object> model){
        List<Element> elements;
        Element element;
        Quest quest;
        //return default page if the id not exist
        if (questRepo.findById(Long.valueOf(questId)).isPresent() && elementRepo.findById(Long.valueOf(elementId)).isPresent()) {
            quest = questRepo.findById(Long.valueOf(questId)).get();
            elements = elementRepo.FindByQuest(quest);
            element = elementRepo.findById(Long.valueOf(elementId)).get();
        }else {
            return "redirect:/admin/quests";
        }
        //changing the order of elements to do not live empty spaces between elements
        updateListOfElementsService.changeOrderIfIndexExistDelete(elements, element);
        //deleting the file from the memory to free the memory
        if (!element.isText()){
            File file = new File(uploadPath+"/"+element.getFileName());
            file.delete();
        }
        elementRepo.delete(element);
        elements = elementRepo.FindByQuest(quest);
        //creating default model
        model.put("elements", elements);
        model.put("questId", quest.getId());
        return "final-page-elements";
    }
}
