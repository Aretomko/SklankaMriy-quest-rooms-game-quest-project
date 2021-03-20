package com.example.sweater.controller.admin.admin;

import com.example.sweater.entities.Element;
import com.example.sweater.entities.Page;
import com.example.sweater.repos.repos.ElementRepo;
import com.example.sweater.repos.repos.PageGameRepo;
import com.example.sweater.repos.repos.PageRepo;
import com.example.sweater.service.service.*;
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
import java.util.*;

@Controller
public class ElementsControllerAdmin {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private ElementRepo elementRepo;
    @Autowired
    private PageRepo pageRepo;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private FileValidation fileValidation;
    @Autowired
    private FileService fileService;
    @Autowired
    private PageGameRepo pageGameRepo;
    @Autowired
    private NumbersValidation integerNumbersValidation;
    @Autowired
    private UpdateListOfElementsService updateListOfElementsService;

    @GetMapping("/admin/modifyPage/{pageId}")
    public String getElementsOfThePage(@PathVariable String pageId,
                                       Map<String, Object> model){

        List<Element> elements;
        Page page;
        pageId = validationService.validateId(pageId);
        if (pageRepo.findById(Long.valueOf(pageId)).isPresent()) {
            page = pageRepo.findById(Long.valueOf(pageId)).get();
            elements = elementRepo.FindByPage(page);
        }else {
            return "redirect:/admin/quests";
        }
        elements.sort(Comparator.comparing(Element::getOrderNumber));
        model.put("pageId", pageId);
        model.put("elements", elements);
        model.put("uploadPath", uploadPath);
        model.put("questId", page.getQuest().getId());
        return "elements-of-page";
    }
    @PostMapping("/admin/addNewElementFile/{pageId}")
    public String addNewElementFileAdmin(@PathVariable String pageId,
                                     @RequestParam String number,
                                     @RequestParam(value = "file", required = false) MultipartFile file,
                                     Map<String, Object> model) throws IOException {
        List<Element> elements;
        Page page;
        pageId = validationService.validateId(pageId);
        if (pageRepo.findById(Long.valueOf(pageId)).isPresent()) {
            page = pageRepo.findById(Long.valueOf(pageId)).get();
            elements = elementRepo.FindByPage(page);
        }else {
            return "redirect:/admin/quests";
        }
        //Validating the "number" parameter to be only integer, if it is not - return error message
        int orderNumber = integerNumbersValidation.validateOrderNumberIntegerElements(model,pageId,elements,number);
        //Updating all elements and increasing their order number if we need to put a new element as first or in the middle;
        updateListOfElementsService.changeOrderIfIndexExistAdd(elements, orderNumber);
        //saving the file into the memory producing the filename using UUID
        String resultFilename = fileService.fileService(file);
        //finding the type of the file
        boolean isVideo = fileValidation.isVideo(resultFilename);
        boolean isImage = fileValidation.isImage(resultFilename);
        if (isVideo == isImage){
            model.put("fileError","Незнайомий формат(");
            validationService.provideStandardOutputAddElement(model,pageId, elements);
        }
        Element newElement = new Element(false, isVideo, isImage, resultFilename, orderNumber, pageRepo.findById(Long.valueOf(pageId)).get());
        elementRepo.save(newElement);
        elements = elementRepo.FindByPage(pageRepo.findById(Long.valueOf(pageId)).get());
        //creating standard model
        validationService.provideStandardOutputAddElement(model, pageId,elements);
        model.put("questId", page.getQuest().getId());
        return "elements-of-page";
    }
    @PostMapping("/admin/addNewElementText/{pageId}")
    public String addNewElementText(@PathVariable String pageId,
                                    @RequestParam String number,
                                    @RequestParam String text,
                                    Map<String, Object> model){
        List<Element> elements;
        Page page;
        pageId = validationService.validateId(pageId);
        if (pageRepo.findById(Long.valueOf(pageId)).isPresent()) {
            page = pageRepo.findById(Long.valueOf(pageId)).get();
            elements = elementRepo.FindByPage(page);
        }else {
            return "redirect:/admin/quests";
        }
        //Validating the "number" parameter to be only integer, if it is not - return error message
        int orderNumber = integerNumbersValidation.validateOrderNumberIntegerElements(model,pageId,elements,number);
        //Updating all elements and increasing their order number if we need to put a new element as first or in the middle;
        updateListOfElementsService.changeOrderIfIndexExistAdd(elements, orderNumber);
        Element newElement = new Element(true, false, false, text, orderNumber, pageRepo.findById(Long.valueOf(pageId)).get());
        elementRepo.save(newElement);
        elements = elementRepo.FindByPage(pageRepo.findById(Long.valueOf(pageId)).get());
        //creating standard model
        validationService.provideStandardOutputAddElement(model, pageId, elements);
        model.put("questId", page.getQuest().getId());
        return "elements-of-page";
    }
    @GetMapping("/admin/previewPage/{pageId}")
    public String previewPageAdmin(@PathVariable String pageId,
                                   Map<String, Object> model){
        List<Element> elements;
        pageId = validationService.validateId(pageId);
        if (pageRepo.findById(Long.valueOf(pageId)).isPresent()) {
            elements = elementRepo.FindByPage(pageRepo.findById(Long.valueOf(pageId)).get());
        }else {
            return "redirect:/admin/quests";
        }
        elements.sort(Comparator.comparing(Element::getOrderNumber));
        model.put("elements", elements);
        return "preview-page";
    }
    @GetMapping("/admin/previewPageGame/{pageId}")
    public String previewPageGameAdmin(@PathVariable String pageId,
                                   Map<String, Object> model){
        List<Element> elements;
        pageId = validationService.validateId(pageId);
        if (pageGameRepo.findById(Long.valueOf(pageId)).isPresent()) {
            elements = elementRepo.FindByPageGame(pageGameRepo.findById(Long.valueOf(pageId)).get());
        }else {
            return "redirect:/admin/quests";
        }
        elements.sort(Comparator.comparing(Element::getOrderNumber));
        model.put("elements", elements);
        return "preview-page";
    }
    @GetMapping("/admin/deleteElement/{elementId}/{pageId}")
    public String deleteElement(@PathVariable String elementId,
                                @PathVariable String pageId,
                                Map<String, Object> model){
        List<Element> elements;
        Element element;
        Page page ;
        pageId = validationService.validateId(pageId);
        //return default page if the id not exist
        if (pageRepo.findById(Long.valueOf(pageId)).isPresent() && elementRepo.findById(Long.valueOf(elementId)).isPresent()) {
            page = pageRepo.findById(Long.valueOf(pageId)).get();
            elements = elementRepo.FindByPage(page);
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
        elements = elementRepo.FindByPage(pageRepo.findById(Long.valueOf(pageId)).get());
        //creating default model
        validationService.provideStandardOutputAddElement(model, pageId,elements);
        model.put("questId", page.getQuest().getId());
        return "elements-of-page";
    }
    @GetMapping("/admin/editElement/{elementId}")
    public String editElement(@PathVariable String elementId,
                                  Map<String, Object> model){
        Element currentElement;
        elementId = validationService.validateId(elementId);
        if(elementRepo.findById(Long.valueOf(elementId)).isPresent()){
            currentElement = elementRepo.findById(Long.valueOf(elementId)).get();
        }else return "redirect:/admin/quests";
        model.put("elements" , new ArrayList<Element>(Collections.singletonList(currentElement)));
        return "edit-element";
    }
    @GetMapping("/admin/editElementImage/{elementId}")
    public String editElementImage(@PathVariable String elementId,
                                   Map<String, Object> model){
        Element currentElement;
        elementId = validationService.validateId(elementId);
        if(elementRepo.findById(Long.valueOf(elementId)).isPresent()){
            currentElement = elementRepo.findById(Long.valueOf(elementId)).get();
        }else return "redirect:/admin/quests";
        model.put("elements" , new ArrayList<Element>(Collections.singletonList(currentElement)));
        return "edit-file-element";
    }
    @PostMapping("/admin/editElementImageConfirm/{elementId}")
    public String editElementImageConfirm(@PathVariable String elementId,
                                         @RequestParam String number,
                                         @RequestParam(value = "file", required = false) MultipartFile file,
                                         Map<String, Object> model) throws IOException {
        List<Element> elements;
        Element currentElement;
        elementId = validationService.validateId(elementId);
        if(elementRepo.findById(Long.valueOf(elementId)).isPresent()){
            currentElement = elementRepo.findById(Long.valueOf(elementId)).get();
        }else return "redirect:/admin/quests";
        elements = elementRepo.FindByPage(currentElement.getPage());
        Page page  =currentElement.getPage();
        //Validating the "number" parameter to be only integer, if it is not - return error message
        int orderNumber = integerNumbersValidation.validateOrderNumberIntegerElements(model,String.valueOf(page.getId()),elements,number);
        //Updating all elements and increasing their order number if we need to put a new element as first or in the middle;
        updateListOfElementsService.changeOrderIfIndexExistAdd(elements, orderNumber);
        currentElement.setOrderNumber(Integer.parseInt(number));
        //updating the attached file if it was attached
        if(!file.getOriginalFilename().equals("")){
            File fileToDelete = new File(uploadPath+"/"+currentElement.getFileName());
            fileToDelete.delete();
            String resultFilename = fileService.fileService(file);
            currentElement.setFileName(resultFilename);
        }
        currentElement.setOrderNumber(Integer.parseInt(number));
        elementRepo.save(currentElement);
        model.put("elements", elementRepo.FindByPage(currentElement.getPage()));
        model.put("pageId", page.getId());
        return "elements-of-page";
    }
    @PostMapping("/admin/editElementTextConfirm/{elementId}")
    public String editElementTextConfirm(@PathVariable String elementId,
                                         @RequestParam String number,
                                         @RequestParam String text,
                                         Map<String, Object> model){
        List<Element> elements;
        Element currentElement;
        elementId = validationService.validateId(elementId);
        if(elementRepo.findById(Long.valueOf(elementId)).isPresent()){
            currentElement = elementRepo.findById(Long.valueOf(elementId)).get();
        }else return "redirect:/admin/quests";
        elements = elementRepo.FindByPage(currentElement.getPage());
        Page page  =currentElement.getPage();
        //Validating the "number" parameter to be only integer, if it is not - return error message
        int orderNumber = integerNumbersValidation.validateOrderNumberIntegerElements(model,String.valueOf(page.getId()),elements,number);
        //Updating all elements and increasing their order number if we need to put a new element as first or in the middle;
        updateListOfElementsService.changeOrderIfIndexExistAdd(elements, orderNumber);
        elementRepo.saveAll(elements);
        currentElement.setOrderNumber(Integer.parseInt(number));
        currentElement.setString(text);
        elementRepo.save(currentElement);
        model.put("elements", elementRepo.FindByPage(currentElement.getPage()));
        model.put("pageId", page.getId());
        return "elements-of-page";
    }
}
