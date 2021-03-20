package com.example.sweater.controller.User;

import com.example.sweater.entities.Application;
import com.example.sweater.repos.repos.ApplicationService;
import com.example.sweater.service.service.MailSender;
import com.example.sweater.service.service.TranslitService;
import com.example.sweater.service.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Controller
public class ApplicationControllerUser {
    @Autowired
    private ValidationService validationService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private TranslitService translitService;

    @GetMapping("/apply")
    public String getApplicationForm(){
        return "registration-of-application";
    }
    @PostMapping("/addNewApplication")
    public String addNewApplication(@RequestParam String name,
                                    @RequestParam String number,
                                    @RequestParam String mail,
                                    Map<String, Object> model){
        Application createdApplication = new Application(name, number, mail, LocalDate.now().toString(), LocalTime.now().toString());
        //validating application fields, returning errs is some fields are wrong
        boolean successful = validationService.validateApplicationCreationUser(model, createdApplication);
        if (successful){
            applicationService.save(createdApplication);
            //sending the mail notification to the administrator
            mailSender.send("Sklyanka.mriy@gmail.com", "Applicatiom submited", "New registration on quest, name: - "+ translitService.toTranslit(name) + " number: - " + number + " mail - "+ mail);
            return "application-successful";
        }
        return "registration-of-application";
    }
}
