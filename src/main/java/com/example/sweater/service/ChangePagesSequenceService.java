package com.example.sweater.service;

import com.example.sweater.domain.Game;
import com.example.sweater.domain.Page;
import com.example.sweater.domain.PageGame;
import com.example.sweater.repos.PageGameRepo;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChangePagesSequenceService {
    @Autowired
    private PageGameRepo pageGameRepo;

    public void changeSequence(int orderNumb, PageGame currentPageGame, List<PageGame> pagesGame){
            if (orderNumb < Integer.parseInt(currentPageGame.getOrderNumber())){
                List<PageGame> elementsToUpdate = pagesGame.stream().filter(i-> Integer.parseInt(i.getOrderNumber()) >= orderNumb).collect(Collectors.toList());
                for (PageGame e : elementsToUpdate) {
                    e.setOrderNumber(String.valueOf(Integer.parseInt(e.getOrderNumber())+1));
                }
            }
            if (orderNumb>Integer.parseInt(currentPageGame.getOrderNumber())) {
                List<PageGame> elementsToUpdate = pagesGame.stream().filter(i -> Integer.parseInt(i.getOrderNumber()) <= orderNumb).collect(Collectors.toList());
                for (PageGame e : elementsToUpdate) {
                    e.setOrderNumber(String.valueOf(Integer.parseInt(e.getOrderNumber()) - 1));
                }
            }
            currentPageGame.setOrderNumber(String.valueOf(orderNumb));
            pageGameRepo.save(currentPageGame);
    }
}
