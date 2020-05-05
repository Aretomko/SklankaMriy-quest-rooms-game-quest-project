package com.example.sweater.service;

import com.example.sweater.domain.PageGame;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindCurrentPageService {
    public PageGame findCurrentPage(List<PageGame> pagesGame){
        for (PageGame p: pagesGame) {
            if (!p.isAnswered()){
                return p;
            }
        }
        return null;
    }
}
