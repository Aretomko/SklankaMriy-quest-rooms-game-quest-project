package com.example.sweater.service;

import com.example.sweater.domain.Hint;
import com.example.sweater.repos.HintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpdateListOfHintsService {
    @Autowired
    private HintRepo hintRepo;

    public void changeOrderIfIndexExistAdd(List<Hint> hints, int orderNumber){
        if (hints.stream().anyMatch(i -> i.getOrderNumber() == orderNumber)) {
            List<Hint> hintsToUpdate = hints.stream().filter(i -> i.getOrderNumber() >= orderNumber).collect(Collectors.toList());
            for (Hint h : hintsToUpdate) {
                h.setOrderNumber(h.getOrderNumber() + 1);
                hintRepo.save(h);
            }
        }
    }
    public void changeOrderIfIndexExistDelete(List<Hint> hints, Hint hintToDelete){
        if (hints.stream().anyMatch(i -> i.getOrderNumber() == hintToDelete.getOrderNumber())) {
            List<Hint> hintsToUpdate = hints.stream().filter(i->i.getOrderNumber() >= hintToDelete.getOrderNumber()).collect(Collectors.toList());
            for (Hint h : hints) {
                h.setOrderNumber(h.getOrderNumber()-1);
                hintRepo.save(h);
            }
        }
    }
}
