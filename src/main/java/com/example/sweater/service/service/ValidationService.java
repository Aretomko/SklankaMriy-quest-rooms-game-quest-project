package com.example.sweater.service.service;

import com.example.sweater.entities.Application;
import com.example.sweater.entities.Element;
import com.example.sweater.entities.Page;
import com.example.sweater.entities.Team;
import com.example.sweater.repos.repos.ElementRepo;
import com.example.sweater.repos.repos.PageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class ValidationService {
    @Autowired
    private PageRepo pageRepo;
    @Autowired
    private ElementRepo elementRepo;

    public boolean validateTeamCreationUser(Map<String , Object> model, Team inputTeam){
        boolean returnValue = true;
        boolean dataIsSaved = false;
        if (inputTeam.getTeamName().length() >= 30){
            returnValue = false;
            model.put("teamNameError", "Назва команди не може бути довшою за 30 символів");
            if (!dataIsSaved) {
                dataIsSaved = true;
                saveInputDataTeam(model, inputTeam);
            }
        }
        if (inputTeam.getCapName().length() >= 100){
            returnValue = false;
            model.put("capNameError", "Ім'я не мож бути довше за 100 символів");
            if (!dataIsSaved) {
                dataIsSaved = true;
                saveInputDataTeam(model, inputTeam);
            }
        }
        if (inputTeam.getCapNumber().length() != 10 ){
            returnValue = false;
            model.put("capNumberError", "Неіснуючий номер телефону");
            if (!dataIsSaved) {
                dataIsSaved = true;
                saveInputDataTeam(model, inputTeam);
            }
        }
            if (!inputTeam.getCapNumber().matches("[0-9]+")){
                returnValue = false;
                model.put("capNumberError", "Номер не може включати букви та знаки");
                if (!dataIsSaved) {
                    dataIsSaved = true;
                    saveInputDataTeam(model, inputTeam);
                }
        }
        if (inputTeam.getSecondCapName().length() >= 100){
            returnValue = false;
            model.put("secondCapNameError", "Ім'я не мож бути довше за 100 символів");
            if (!dataIsSaved) {
                dataIsSaved = true;
                saveInputDataTeam(model, inputTeam);
            }
        }
        if (inputTeam.getSecondCapNumber().length() != 10){
            returnValue = false;
            model.put("secondCapNumberError", "Неіснуючий номер телефону контактної особи");
            if (!dataIsSaved) {
                dataIsSaved = true;
                saveInputDataTeam(model, inputTeam);
            }
        }
        if (!inputTeam.getSecondCapNumber().matches("[0-9]+")){
            returnValue = false;
            model.put("secondCapNumberError", "Номер не може включати букви та знаки");
            if (!dataIsSaved) {
                dataIsSaved = true;
                saveInputDataTeam(model, inputTeam);
            }
        }
            if (inputTeam.getSecondCapNumber().equals(inputTeam.getCapNumber())){
                returnValue = false;
                model.put("secondCapNumberError", "Мобільні телефони контактних осіб не можуть збігатися");
                if (!dataIsSaved) {
                    dataIsSaved = true;
                    saveInputDataTeam(model, inputTeam);
                }
            }


        if (Integer.parseInt(inputTeam.getNumberOfPlayers()) >= 10000){
            returnValue = false;
            model.put("quantityOfPlayersError", "Не можна зареєструвати быльше 1000 гравців в одну команду");
            if (!dataIsSaved) {
                dataIsSaved = true;
                saveInputDataTeam(model, inputTeam);
            }
        }



        return returnValue;
    }

    public boolean validateApplicationCreationUser(Map<String, Object> model, Application inputApplication){
        boolean returnValue = true;
        boolean dataIsSaved = false;
        if (inputApplication.getName().length() > 100){
            returnValue = false;
            model.put("nameError", "Ім'я не може бути довшим за 100 символів");
            if (!dataIsSaved) {
                dataIsSaved = true;
                saveInputDataApplication(model, inputApplication);
            }
        }
        if (inputApplication.getNumber().length() != 10){
            returnValue = false;
            model.put("numberError", "Неіснуючий номер телефону (можливо пропущена цифра)");
            if (!dataIsSaved) {
                dataIsSaved = true;
                saveInputDataApplication(model, inputApplication);
            }
        }
            if (!Pattern.matches("[0-9]+", inputApplication.getNumber())){
                returnValue = false;
                model.put("numberError", "Номер не може включати букви та знаки");
                if (!dataIsSaved) {
                    dataIsSaved = true;
                    saveInputDataApplication(model, inputApplication);
                }
            }
        if (!isValidEmailAddress(inputApplication.getMail())){
            returnValue = false;
            model.put("mailError", "Неіснуючий адес електронної пошти");
            if (!dataIsSaved) {
                dataIsSaved = true;
                saveInputDataApplication(model, inputApplication);
            }
        }
        return returnValue;
    }
    private static void saveInputDataTeam(Map<String , Object> model, Team inputTeam){
        model.put("teamNameErrorData", inputTeam.getTeamName());
        model.put("capNameErrorData", inputTeam.getCapName());
        model.put("capNumberErrorData", inputTeam.getCapNumber());
        model.put("secondCapNameErrorData", inputTeam.getSecondCapName());
        model.put("secondCapNumberErrorData", inputTeam.getSecondCapNumber());
        model.put("quantityOfPlayersErrorData", inputTeam.getNumberOfPlayers());
    }

    public boolean validateInputNumber(Map<String, Object> model, Page inputPage){
            boolean returnValue = true;
            boolean dataIsSaved = false;
            boolean isInteger = true;
            try {
                inputPage.getOrderNumber();
            }catch (NumberFormatException e){
                isInteger = false;
            }

            if (!isInteger){
                model.put("numberError", "Можна вписати тільки чило");
                returnValue = false;
            }

            return returnValue;
    }
//    public boolean validateInputNumber(Map<String, Object> model, Element inputElement){
//        boolean returnValue = true;
//        boolean dataIsSaved = false;
//        boolean isInteger = true;
//        try {
//            Integer.parseInt(inputElement.getOrderNumber());
//        }catch (NumberFormatException e){
//            isInteger = false;
//        }
//
//        if (!isInteger){
//            model.put("numberError", "Можна вписати тільки чило");
//            returnValue = false;
//        }
//
//        return returnValue;
//    }
    private static void saveInputDataApplication(Map<String, Object> model, Application inputApplication){
        model.put("nameErrorData", inputApplication.getName());
        model.put("numberErrorData", inputApplication.getNumber());
        model.put("mailErrorData", inputApplication.getMail());
    }
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    public void provideStandardOutputAddElement(Map<String,Object> model, String pageId,  List<Element> elements){
        model.put("pageId", pageId);
        model.put("elements", elements);
    }

    public String  validateId(String id){
        StringBuffer stringBuffer = new StringBuffer();
        for (char c: id.toCharArray()){
            if (c != 160){
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }
}
