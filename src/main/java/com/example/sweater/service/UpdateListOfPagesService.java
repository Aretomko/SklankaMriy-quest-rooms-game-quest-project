package com.example.sweater.service;

import com.example.sweater.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpdateListOfPagesService {
    public void updateListAdd(List<Page> pages, Page page){
        if (pages.stream().anyMatch(i -> i.getOrderNumber() == page.getOrderNumber())) {
            List<Page> pagesToUpdate = pages.stream().filter(i->i.getOrderNumber() >= page.getOrderNumber()).collect(Collectors.toList());
            for (Page e : pagesToUpdate) {
                e.setOrderNumber(e.getOrderNumber()+1);
            }
        }
    }

}
