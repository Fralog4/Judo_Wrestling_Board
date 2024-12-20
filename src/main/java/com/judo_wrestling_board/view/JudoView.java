package com.judo_wrestling_board.view;

import com.judo_wrestling_board.model.JudoScore;
import com.judo_wrestling_board.service.AthleteService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;

/***
 *
 * ricordati di far diventare persistene h2 c'è il modo oppure inietta sql, poi nella select appaere il json modifica che è brutto da vedere
 */

@Route("judoView")
public class JudoView extends Div {


    private final AthleteService athleteService;

    public JudoView(AthleteService athleteService) {
        this.athleteService = athleteService;

        addClassName("judo-view");

        HorizontalLayout buttonScoreLayout = new HorizontalLayout();
        buttonScoreLayout.addClassName("button-score-layout");

        Button ipponButton = new Button("Ippon");
        ipponButton.addClickListener(event -> addScore(ipponButton));
        ipponButton.setClassName("ipponButton-button");
        Button wazaAriButton = new Button("Waza-Ari");
        wazaAriButton.addClickListener(event -> addScore(wazaAriButton));
        wazaAriButton.setClassName("wazaAri-button");
        Button yukoButton = new Button("Yuko");
        yukoButton.addClickListener(event -> addScore(yukoButton));
        yukoButton.setClassName("yuko-button");
        buttonScoreLayout.add(ipponButton, wazaAriButton, yukoButton);


        HorizontalLayout athleteOne = new HorizontalLayout();
        athleteOne.setSpacing(true);
        athleteOne.setClassName("athlete-one");

        Select athleteOneChoose = new Select<>();
        athleteOneChoose.setItems(athleteService.getAllAthletes());
        athleteOneChoose.setLabel("Atleta 1");
        athleteOneChoose.setPlaceholder("Seleziona un atleta");
        athleteOneChoose.setClassName("athlete-one-choose");
        athleteOne.add(athleteOneChoose);

        HorizontalLayout athleteTwo = new HorizontalLayout();
        athleteTwo.setSpacing(true);
        athleteTwo.setClassName("athlete-two");

        Select athleteTwoChoose = new Select<>();
        athleteTwoChoose.setItems(athleteService.getAllAthletes());
        athleteTwoChoose.setLabel("Atleta 2");
        athleteTwoChoose.setPlaceholder("Seleziona un atleta");
        athleteTwoChoose.setClassName("athlete-two-choose");
        athleteTwo.add(athleteTwoChoose);


        IntegerField score = new IntegerField();
        score.setLabel("Punteggio");
        score.setClassName("score-integer");
        score.setMin(0);
        score.setMax(2);
        score.setStepButtonsVisible(true);
        score.setClearButtonVisible(true);


        IntegerField scoreTwo = new IntegerField();
        scoreTwo.setLabel("Punteggio");
        scoreTwo.setClassName("score-integer-two");
        scoreTwo.setMin(0);
        scoreTwo.setMax(2);
        scoreTwo.setStepButtonsVisible(true);
        scoreTwo.setClearButtonVisible(true);

        athleteOne.add(score);
        athleteTwo.add(scoreTwo);


        add(athleteOne, athleteTwo, buttonScoreLayout);
    }

    private void addScore(Button button) {
        JudoScore score = getScoreFromButton(button.getText());
        Span span = new Span();
        span.setText(score.toString().toUpperCase());

        add(span);
    }

    private JudoScore getScoreFromButton(String buttonText) {
        switch (buttonText) {
            case "Ippon":
                return JudoScore.IPPON;
            case "Waza-Ari":
                return JudoScore.WAZARI;
            case "Yuko":
                return JudoScore.YUKO;
            default:
                throw new IllegalArgumentException("Unknown button text: " + buttonText);
        }
    }
}
