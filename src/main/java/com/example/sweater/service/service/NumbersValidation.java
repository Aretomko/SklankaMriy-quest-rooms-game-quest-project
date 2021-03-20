package com.example.sweater.service.service;

import com.example.sweater.entities.Element;
import com.example.sweater.entities.Hint;
import com.example.sweater.entities.PageGame;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NumbersValidation {
    public int validateTimeInteger(Map<String, Object> model, String pageId, List<Hint> hints, String number){
        int timeNumber = 0;
        try{
            timeNumber = Integer.parseInt(number);
        }catch (NumberFormatException e){
            model.put("timeError", "Можна вписати тільки чило");
            model.put("pageId", pageId);
            model.put("hints", hints);
            return timeNumber;
        }
        return timeNumber;
    }
    public int validateOrderNumberIntegerHints(Map<String, Object> model, String pageId, List<Hint> hints, String number){
        int orderNumber = 0;
        try{
            orderNumber = Integer.parseInt(number);
        }catch (NumberFormatException e){
            model.put("numberError", "Можна вписати тільки чило");
            model.put("pageId", pageId);
            model.put("hints", hints);
            return orderNumber;
        }
        return orderNumber;
    }
    public int validateOrderNumberIntegerElements(Map<String, Object> model, String pageId, List<Element> elements, String number){
        int orderNumber = 0;
        try{
            orderNumber = Integer.parseInt(number);
        }catch (NumberFormatException e){
            model.put("numberError", "Можна вписати тільки чило");
            model.put("pageId", pageId);
            model.put("elements", elements);
            return orderNumber;
        }
        return orderNumber;
    }
    public int validateOrderNumberIntegerPageGame(Map<String, Object> model, String teamName, List<PageGame> pageGames, String number){
        int orderNumber = 0;
        try{
            orderNumber = Integer.parseInt(number);
        }catch (NumberFormatException e){
            model.put("numberError", "Можна вписати тільки чило");
            model.put("teamName", teamName);
            model.put("pages", pageGames);
            return orderNumber;
        }
        return orderNumber;
    }
    public double validateDoubleTimePage(Map<String, Object> model, String time){
        double returnTime = 0.00;
        try {
            returnTime = Double.parseDouble(time);
        }catch (NumberFormatException e){
            model.put("timeError", "Можна вписати тільки чило");
        }
        return returnTime;
    }
    public int validationOrderNumberPage(Map<String, Object> model, String number){
        int orderNumber = 0;
        try{
            orderNumber = Integer.parseInt(number);
        }catch (NumberFormatException e){
            model.put("numberError", "Можна вписати тільки чило");
            return 0;
        }
        return orderNumber;
    }
}
