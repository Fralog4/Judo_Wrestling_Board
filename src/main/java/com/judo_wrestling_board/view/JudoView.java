package com.judo_wrestling_board.view;

import com.judo_wrestling_board.model.JudoScore;
import com.judo_wrestling_board.service.AthleteService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;


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

        Button ipponButton = new Button("Ippon");
        ipponButton.addClickListener(event -> addScore(ipponButton,athleteOne,score));
        ipponButton.setClassName("ipponButton-button");

        Button wazaAriButton = new Button("Waza-Ari");
        wazaAriButton.addClickListener(event -> addScore(wazaAriButton,athleteOne,score));
        wazaAriButton.setClassName("wazaAri-button");

        Button yukoButton = new Button("Yuko");
        yukoButton.addClickListener(event -> addScore(yukoButton,athleteOne,score));
        yukoButton.setClassName("yuko-button");

        Button ipponButtonTwo = new Button("Ippon");
        ipponButtonTwo.addClickListener(event -> addScore(ipponButton,athleteTwo,scoreTwo));
        ipponButtonTwo.setClassName("ipponButton-button");

        Button wazaAriButtonTwo = new Button("Waza-Ari");
        wazaAriButtonTwo.addClickListener(event -> addScore(wazaAriButton,athleteTwo,scoreTwo));
        wazaAriButtonTwo.setClassName("wazaAri-button");

        Button yukoButtonTwo = new Button("Yuko");
        yukoButtonTwo.addClickListener(event -> addScore(yukoButton,athleteTwo,scoreTwo));
        yukoButtonTwo.setClassName("yuko-button");


        Select athleteOneChoose = new Select<>();
        athleteOneChoose.setItems(athleteService.getAllAthletes());
        athleteOneChoose.setLabel("Atleta 1");
        athleteOneChoose.setPlaceholder("Seleziona un atleta");
        athleteOneChoose.setClassName("athlete-one-choose");

        Select athleteTwoChoose = new Select<>();
        athleteTwoChoose.setItems(athleteService.getAllAthletes());
        athleteTwoChoose.setLabel("Atleta 2");
        athleteTwoChoose.setPlaceholder("Seleziona un atleta");
        athleteTwoChoose.setClassName("athlete-two-choose");


        athleteOne.add(athleteOneChoose,score,ipponButton, wazaAriButton, yukoButton);
        athleteTwo.add(athleteTwoChoose,scoreTwo,ipponButtonTwo, wazaAriButtonTwo, yukoButtonTwo);


        Button goBackToHome = new Button("Back to Home");
        goBackToHome.setClassName("back-button");
        goBackToHome.addClickListener(e -> {
            UI.getCurrent().navigate("/home");
        });


        add(athleteOne, athleteTwo,goBackToHome);
    }

    private void addScore(Button button, HorizontalLayout playerHorizontal,IntegerField scoreLayout) {
        VerticalLayout buttonAndScoreLayout = (VerticalLayout) playerHorizontal.getChildren()
                .filter(component -> component.getClass().equals(VerticalLayout.class))
                .findFirst()
                .orElseGet(() -> {
                    VerticalLayout newLayout = new VerticalLayout();
                    newLayout.setSpacing(false);
                    newLayout.setPadding(false);
                    playerHorizontal.add(newLayout);
                    return newLayout;
                });

        Span scoreDisplay = (Span) buttonAndScoreLayout.getChildren()
                .filter(component -> component.getClass().equals(Span.class))
                .findFirst()
                .orElseGet(() -> {
                    Span newScoreDisplay = new Span();
                    newScoreDisplay.setClassName("score-display");
                    buttonAndScoreLayout.add(newScoreDisplay);
                    return newScoreDisplay;
                });

        JudoScore score = getScoreFromButton(button.getText(),scoreLayout);
        scoreDisplay.setText(score.toString().toUpperCase());
    }


    private JudoScore getScoreFromButton(String buttonText, IntegerField scoreLayout) {
        switch (buttonText) {
            case "Ippon":
                scoreLayout.setValue(10);
                return JudoScore.IPPON;
            case "Waza-Ari":
                scoreLayout.setValue(1);
                return JudoScore.WAZARI;
            case "Yuko":
                scoreLayout.setValue(1);
                return JudoScore.YUKO;
            default:
                throw new IllegalArgumentException("Unknown button text: " + buttonText);
        }
    }
}