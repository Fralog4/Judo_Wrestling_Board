package com.judo_wrestling_board.controller;

import com.judo_wrestling_board.model.Athlete;
import com.judo_wrestling_board.service.AthleteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/judo-wrestling-board/atlethe")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {this.athleteService = athleteService;}

    @GetMapping("/name/{name}")
    public Athlete getAthleteByName(String name) {
        return athleteService.getAthleteByName(name);
    }

    @GetMapping("/id/{id}")
    public Athlete getAthleteById(@PathVariable Long id) {
        return athleteService.getAthleteById(id);
    }

    @PostMapping("/save")
    public void saveAthlete(@RequestParam Athlete athlete) {
        athleteService.saveAthlete(athlete);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAthlete(@PathVariable Long id) {
        athleteService.deleteAthlete(id);
    }

    @PutMapping("/update")
    public void updateAthlete(Athlete athlete) {
        athleteService.updateAthlete(athlete);
    }







}
