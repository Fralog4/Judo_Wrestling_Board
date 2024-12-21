package com.judo_wrestling_board.service;


import com.judo_wrestling_board.model.Athlete;
import com.judo_wrestling_board.repository.AthleteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {
    private final AthleteRepository athleteRepository;

    public AthleteService(AthleteRepository athleteRepository){this.athleteRepository = athleteRepository;}

    public Athlete getAthleteById(Long id){
        return athleteRepository.findById(id).orElse(null);
    }

    public Athlete getAthleteByName(String name){
        return athleteRepository.findByName(name);
    }

    public void saveAthlete(Athlete athlete){
        athleteRepository.save(athlete);
    }

    public void deleteAthlete(Long id){
        athleteRepository.deleteById(id);
    }

    public void updateAthlete(Athlete athlete){
        athleteRepository.save(athlete);
    }

    public List<String> getAllAthletes() {return athleteRepository.findAll()
            .stream()
            .map(athlete -> athlete.getName()+" "+athlete.getSurname()).toList();}

}
