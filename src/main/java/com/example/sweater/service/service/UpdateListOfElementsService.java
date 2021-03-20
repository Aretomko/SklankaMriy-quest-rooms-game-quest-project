package com.example.sweater.service.service;
import com.example.sweater.entities.Element;
import com.example.sweater.repos.repos.ElementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpdateListOfElementsService {
    @Autowired
    private ElementRepo elementRepo;

    public void changeOrderIfIndexExistAdd(List<Element> elements,int orderNumber){
        if (elements.stream().anyMatch(i -> i.getOrderNumber() == orderNumber)) {
            List<Element> elementsToUpdate = elements.stream().filter(i->i.getOrderNumber() >= orderNumber).collect(Collectors.toList());
            for (Element e : elementsToUpdate) {
                e.setOrderNumber(e.getOrderNumber()+1);
                elementRepo.save(e);
            }
        }
    }
    public void changeOrderIfIndexExistDelete(List<Element> elements, Element element){
        if (elements.stream().anyMatch(i -> i.getOrderNumber() == element.getOrderNumber())) {
            List<Element> elementsToUpdate = elements.stream().filter(i->i.getOrderNumber() >= element.getOrderNumber()).collect(Collectors.toList());
            for (Element e : elementsToUpdate) {
                e.setOrderNumber(e.getOrderNumber()-1);
                elementRepo.save(e);
            }
        }
    }
}
