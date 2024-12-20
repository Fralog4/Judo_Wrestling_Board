package com.judo_wrestling_board.view;

import com.judo_wrestling_board.model.JudoScore;
import com.judo_wrestling_board.service.AthleteService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
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

        HorizontalLayout athleteOne = new HorizontalLayout();
        athleteOne.setSpacing(true);
        athleteOne.setClassName("athlete-one");

        HorizontalLayout athleteTwo = new HorizontalLayout();
        athleteTwo.setSpacing(true);
        athleteTwo.setClassName("athlete-two");

        Button ipponButton = new Button("Ippon");
        ipponButton.addClickListener(event -> addScore(ipponButton,athleteOne));
        ipponButton.setClassName("ipponButton-button");
        Button wazaAriButton = new Button("Waza-Ari");
        wazaAriButton.addClickListener(event -> addScore(wazaAriButton,athleteOne));
        wazaAriButton.setClassName("wazaAri-button");
        Button yukoButton = new Button("Yuko");
        yukoButton.addClickListener(event -> addScore(yukoButton,athleteOne));
        yukoButton.setClassName("yuko-button");



        Button ipponButtonTwo = new Button("Ippon");
        ipponButtonTwo.addClickListener(event -> addScore(ipponButton,athleteTwo));
        ipponButtonTwo.setClassName("ipponButton-button");
        Button wazaAriButtonTwo = new Button("Waza-Ari");
        wazaAriButtonTwo.addClickListener(event -> addScore(wazaAriButton,athleteTwo));
        wazaAriButtonTwo.setClassName("wazaAri-button");
        Button yukoButtonTwo = new Button("Yuko");
        yukoButtonTwo.addClickListener(event -> addScore(yukoButton,athleteTwo));
        yukoButtonTwo.setClassName("yuko-button");



//        buttonScoreLayout.add(ipponButton, wazaAriButton, yukoButton);



        Select athleteOneChoose = new Select<>();
        athleteOneChoose.setItems(athleteService.getAllAthletes());
        athleteOneChoose.setLabel("Atleta 1");
        athleteOneChoose.setPlaceholder("Seleziona un atleta");
        athleteOneChoose.setClassName("athlete-one-choose");
        athleteOne.add(athleteOneChoose);

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

        athleteOne.add(score,ipponButton, wazaAriButton, yukoButton);
        athleteTwo.add(scoreTwo,ipponButtonTwo, wazaAriButtonTwo, yukoButtonTwo);


        Button goBackToHome = new Button("Back to Home");
        goBackToHome.setClassName("back-button");
        goBackToHome.addClickListener(e -> {
            UI.getCurrent().navigate("/home");
        });


        add(athleteOne, athleteTwo,goBackToHome);
    }

    private void addScore(Button button, HorizontalLayout playerHorizontal) {
        Span scoreDisplay = (Span) playerHorizontal.getChildren()
                .filter(component -> component.getClass().equals(Span.class))
                .findFirst()
                .orElseGet(() -> {
                    Span newScoreDisplay = new Span();
                    newScoreDisplay.setClassName("score-display");
                    playerHorizontal.addComponentAtIndex(0, newScoreDisplay);
                    return newScoreDisplay;
                });

        JudoScore score = getScoreFromButton(button.getText());
        scoreDisplay.setText(score.toString().toUpperCase());
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
