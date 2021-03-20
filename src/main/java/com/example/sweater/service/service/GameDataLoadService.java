package com.example.sweater.service.service;

import com.example.sweater.entities.*;
import com.example.sweater.repos.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameDataLoadService {
    @Autowired
    private PageGameRepo pageGameRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private ElementRepo elementRepo;
    @Autowired
    private HintRepo hintRepo;
    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private PageRepo pageRepo;

    public void loadGameData(Team currentTeam, List<PageGame> pagesGame, Quest currentQuest){
        if (currentTeam.getGame() == null) {
            pagesGame = new ArrayList<>();
            Set<Hint> hintsGame = new HashSet<>();
            Set<Element> elementsGame = new HashSet<>();
            Set<Answer> answers = new HashSet<>();
            List<Page> pages = pageRepo.findByQuest(currentQuest).stream().sorted(Comparator.comparing(Page::getOrderNumber)).collect(Collectors.toList());
            for (Page i : pages) {
                PageGame createdPageGame = new PageGame(String.valueOf(i.getOrderNumber()),String.valueOf(i.getOrderNumber()) ,null, i.getTime(), i.getName());
                pagesGame.add(createdPageGame);
                for (Hint hint : i.getHints()){
                    if(hint.isText()) {
                        hintsGame.add(new Hint(hint.isText(), hint.isVideo(), hint.isImage(), hint.getString(), hint.getOrderNumber(),hint.getTime(), createdPageGame));
                    } else hintsGame.add(new Hint(hint.isText(), hint.isVideo(), hint.isImage(), hint.getFileName(), hint.getOrderNumber(),hint.getTime(), createdPageGame));
                }
                for (Element element : i.getElements()){
                    if(element.isText()) {
                        elementsGame.add(new Element(element.isText(), element.isVideo(), element.isImage(), element.getString(), element.getOrderNumber(), createdPageGame));
                    } else elementsGame.add(new Element(element.isText(), element.isVideo(), element.isImage(), element.getFileName(),element.getOrderNumber(), createdPageGame));
                }
                for (Answer a : answerRepo.FindByPage(i)){
                    answers.add(new Answer(createdPageGame, a.getAnswers()));
                }

                createdPageGame.setElements(elementsGame);
                createdPageGame.setHints(hintsGame);
                createdPageGame.setAnswers(answers);
                pageGameRepo.save(createdPageGame);
                elementRepo.saveAll(elementsGame);
                hintRepo.saveAll(hintsGame);
                answerRepo.saveAll(answers);
                elementsGame.clear();
                hintsGame.clear();
            }
            Game newGame = new Game(currentTeam, currentQuest, pagesGame);
            pagesGame.forEach(i -> i.setGame(newGame));
            currentTeam.setGame(newGame);
            gameRepo.save(newGame);
            pageGameRepo.saveAll(pagesGame);
            teamRepo.save(currentTeam);
        }
    }
}
