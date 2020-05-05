package com.example.sweater.controller.User;

import com.example.sweater.domain.*;
import com.example.sweater.repos.*;
import com.example.sweater.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
public class GameController {
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private QuestRepo questRepo;
    @Autowired
    private PageRepo pageRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private ElementRepo elementRepo;
    @Autowired
    private HintRepo hintRepo;
    @Autowired
    private PageGameRepo pageGameRepo;
    @Autowired
    private NumbersValidation integerNumbersValidation;
    @Autowired
    private GameDataLoadService gameDataLoadService;
    @Autowired
    private GameIterationService gameIterationService;
    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private FindCurrentPageService findCurrentPageService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private NoAnswerService noAnswerService;
    @Autowired
    private ChangePagesSequenceService changePagesSequenceService;

    @GetMapping("/start/{code}")
    public String startGameTeamGet(@PathVariable String code,
                                Map<String, Object> model){
        Team currentTeam = teamRepo.findByCode(code);
        if (currentTeam == null) return "no-such-team-page";
        if (currentTeam.getGame() != null){
            //checking weather the start of the game was confirmed by administrator
            if (currentTeam.getGame().isStarted()) {
                Game game = currentTeam.getGame();
                Date date = new Date(System.currentTimeMillis());
                game.setStart(date);
                gameRepo.save(game);
            }}
        return gameIterationService.iteration(model, code);
    }
    @PostMapping("/start/{code}")
    public String startGameTeamPost(@RequestParam String code,
                            Map<String, Object> model){
        Team currentTeam = teamRepo.findByCode(code);
        if (currentTeam == null) return "no-such-team-page";
        if (currentTeam.getGame() != null){
            //checking weather the start of the game was confirmed by administrator
        if (currentTeam.getGame().isStarted()) {
            Game game = currentTeam.getGame();
            Date date = new Date(System.currentTimeMillis());
            game.setStart(date);
            gameRepo.save(game);
        }}
        return gameIterationService.iteration(model, code);
    }
    @PostMapping("/answer/{code}")
    public String readAnswer(@RequestParam String answer,
                             @PathVariable String code,
                             Map<String, Object> model){
        Team currentTeam = teamRepo.findByCode(code);
        if (currentTeam == null) return "no-such-team-page";

        Game game = currentTeam.getGame();

        if (game == null) return "game-not-started-page";
        // find all questions which team needs to answer
        List<PageGame> pagesGame = pageGameRepo.FindByGame(game);
        // sorting by ordernumber which can be changed before the game for particular team
        pagesGame.sort(Comparator.comparing(PageGame::getOrderNumber));
        // find first still not answered page
        PageGame currentPage = findCurrentPageService.findCurrentPage(pagesGame);
        //if there are no not answered question we we start the game iteration where the game will be finished;
        if (currentPage == null) return gameIterationService.iteration(model, code);
        List<Answer> answers = answerRepo.FindByPageGame(currentPage);
        //checking the answer, if it is right then marking the page as answered
        answerService.checkAnswer(answers, currentPage,game, answer);
        return gameIterationService.iteration(model, code);
    }
    @GetMapping("/noAnswer/{code}")
    public String timeoutNoAnswer(@PathVariable String code,
                                  Map<String, Object> model){
        Team currentTeam = teamRepo.findByCode(code);
        if (currentTeam == null) return "no-such-team-page";

        Game currentGame = currentTeam.getGame();
        if (currentGame == null) return "game-not-started-page";

        List<PageGame> pagesGame = pageGameRepo.FindByGame(currentGame);

        pagesGame.sort(Comparator.comparing(PageGame::getOrderNumber));

        PageGame currentPageGame = findCurrentPageService.findCurrentPage(pagesGame);
        //if there are no not answered question we we start the game iteration where the game will be finished;
        if (currentPageGame == null) return gameIterationService.iteration(model, code);
        //setting the question as not answered, calculating the answer time
        noAnswerService.SetAsNotAnswered(currentGame, currentPageGame);
        return gameIterationService.iteration(model,code);
    }

    @GetMapping("/admin/changeSequence/{teamId}")
    public String changeSequence(@PathVariable String teamId,
                                 Map<String, Object> model){
        List<PageGame> pagesGame = null;
        Team currentTeam ;
        if (teamRepo.findById(Long.valueOf(teamId)).isPresent()) {
            currentTeam = teamRepo.findById(Long.valueOf(teamId)).get();
        }else return "no-such-team-page";
        Quest currentQuest = currentTeam.getQuest();
        // if the game for the team isn't yet created we create it
        if (currentTeam.getGame() == null ) {
            gameDataLoadService.loadGameData(currentTeam, pagesGame, currentQuest);
        }
        List<PageGame> outputPages = pageGameRepo.FindByGame(currentTeam.getGame());
        //outputting pages in a sequence according to it's order number
        outputPages = outputPages.stream().sorted(Comparator.comparing(PageGame::getOrderNumber)).collect(Collectors.toList());
        model.put("pages", outputPages);
        model.put("teamName", currentTeam.getTeamName());
        return "pages-game";
    }
    @PostMapping("/admin/changeOrderForPage/{pageId}")
    public String changeTheOrderForPage(@PathVariable String pageId,
                                        @RequestParam String orderNumber,
                                        Map<String, Object> model){
        PageGame currentPageGame;
        if (pageGameRepo.findById(Long.valueOf(pageId)).isPresent()) {
            currentPageGame = pageGameRepo.findById(Long.valueOf(pageId)).get();
        }else return "redirect:/admin/getTeams";
        Game currentGame = currentPageGame.getGame();
        if (currentGame == null) return "redirect:/admin/getTeams";
        List<PageGame> pagesGame = pageGameRepo.FindByGame(currentGame);
        //validating the input weather it is an valid integer
        int orderNumb = integerNumbersValidation.validateOrderNumberIntegerPageGame(model, currentGame.getTeam().getTeamName(), pagesGame, orderNumber);
        // if order number not equals 0 it means that the validation was successful
        if (orderNumb !=0){
            //changing the sequence of pages
            changePagesSequenceService.changeSequence(orderNumb, currentPageGame, pagesGame);
        }else{
                //providing error data in case of failed validation
                model.put("teamName", currentGame.getTeam().getTeamName());
                model.put("pages", pagesGame);
                model.put("numberError", "Поданий номер не ціле число");
                return "pages-game";
        }
        // output pages in a new order
        model.put("teamName", currentGame.getTeam().getTeamName());
        pagesGame.sort(Comparator.comparing(PageGame::getOrderNumber));
        model.put("pages", pagesGame);
        return "pages-game";
    }
    @GetMapping("/admin/startGame/{teamId}")
    public String startGameAdmin(@PathVariable String teamId,
                                 Map<String, Object> model){
        List<PageGame> pagesGame = null;
        Team currentTeam = null;
        if (teamRepo.findById(Long.valueOf(teamId)).isPresent()) {
            currentTeam = teamRepo.findById(Long.valueOf(teamId)).get();
        }else return "no-such-team-page";
        Quest currentQuest = currentTeam.getQuest();
        // if the game for the team isn't yet created we create it
        if (currentTeam.getGame() == null ) {
            gameDataLoadService.loadGameData(currentTeam, pagesGame, currentQuest);
        }
        Game currentGame  = currentTeam.getGame();
        //confirming the start, now users can access the game
        currentGame.setStarted(true);
        gameRepo.save(currentGame);
        return "redirect:/admin/getTeams";
    }
}



