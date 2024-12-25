package com.judo_wrestling_board.view;

import com.judo_wrestling_board.model.JudoAmmonitions;
import com.judo_wrestling_board.model.JudoScore;
import com.judo_wrestling_board.service.AthleteService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;

@Route("judoView")
public class JudoView extends Div {
    private final AthleteService athleteService;
    private int shidoCounterOne = 0;
    private int shidoCounterTwo = 0;

    public JudoView(AthleteService athleteService) {
        this.athleteService = athleteService;
        addClassName("judo-view");

        HorizontalLayout athleteOne = new HorizontalLayout();
        athleteOne.setSpacing(true);
        athleteOne.setClassName("athlete-one");
        athleteOne.setWrap(true);

        HorizontalLayout athleteTwo = new HorizontalLayout();
        athleteTwo.setSpacing(true);
        athleteTwo.setClassName("athlete-two");
        athleteTwo.setWrap(true);

        IntegerField score = new IntegerField();
        score.setLabel("Punteggio");
        score.setClassName("score-integer");
        score.setMin(0);
        score.setMax(2);
        score.setStepButtonsVisible(true);
        score.setValue(0);

        IntegerField scoreTwo = new IntegerField();
        scoreTwo.setLabel("Punteggio");
        scoreTwo.setClassName("score-integer-two");
        scoreTwo.setMin(0);
        scoreTwo.setMax(2);
        scoreTwo.setStepButtonsVisible(true);
        scoreTwo.setValue(0);

        Button shidoButton = new Button("Shido");
        shidoButton.addClickListener(event -> addAmmonition(shidoButton, athleteOne));
        shidoButton.setClassName("shido-button");

        Button hansokuMakeButton = new Button("Hansoku Make");
        hansokuMakeButton.addClickListener(event -> getHansokuFromButton(hansokuMakeButton, athleteOne));
        hansokuMakeButton.setClassName("hansokuMake-button");

        Button shidoButtonTwo = new Button("Shido");
        shidoButtonTwo.addClickListener(event -> addAmmonition(shidoButtonTwo, athleteTwo));
        shidoButtonTwo.setClassName("shido-button-two");

        Button hansokuMakeButtonTwo = new Button("Hansoku Make");
        hansokuMakeButtonTwo.addClickListener(event -> getHansokuFromButton(hansokuMakeButtonTwo, athleteTwo));
        hansokuMakeButtonTwo.setClassName("hansokuMake-button-two");

        Button ipponButton = new Button("Ippon");
        ipponButton.addClickListener(event -> addScore(ipponButton, athleteOne, score));
        ipponButton.setClassName("ipponButton-button");

        Button wazaAriButton = new Button("Waza-Ari");
        wazaAriButton.addClickListener(event -> addScore(wazaAriButton, athleteOne, score));
        wazaAriButton.setClassName("wazaAri-button");

        Button yukoButton = new Button("Yuko");
        yukoButton.addClickListener(event -> addScore(yukoButton, athleteOne, score));
        yukoButton.setClassName("yuko-button");

        Button ipponButtonTwo = new Button("Ippon");
        ipponButtonTwo.addClickListener(event -> addScore(ipponButton, athleteTwo, scoreTwo));
        ipponButtonTwo.setClassName("ipponButton-button");

        Button wazaAriButtonTwo = new Button("Waza-Ari");
        wazaAriButtonTwo.addClickListener(event -> addScore(wazaAriButton, athleteTwo, scoreTwo));
        wazaAriButtonTwo.setClassName("wazaAri-button");

        Button yukoButtonTwo = new Button("Yuko");
        yukoButtonTwo.addClickListener(event -> addScore(yukoButton, athleteTwo, scoreTwo));
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

        Button goBackToHome = new Button("Back to Home");
        goBackToHome.setClassName("back-button");
        goBackToHome.addClickListener(e -> UI.getCurrent().navigate("/home"));

        Button refreshButton = new Button("Refresh");
        refreshButton.setClassName("refresh-button");
        refreshButton.addClickListener(e -> UI.getCurrent().refreshCurrentRoute(true));

        athleteOne.add(athleteOneChoose, score, ipponButton, wazaAriButton, yukoButton, shidoButton, hansokuMakeButton);
        athleteTwo.add(athleteTwoChoose, scoreTwo, ipponButtonTwo, wazaAriButtonTwo, yukoButtonTwo, shidoButtonTwo, hansokuMakeButtonTwo);
        add(athleteOne, athleteTwo, goBackToHome, refreshButton);
    }

    private void addAmmonition(Button button, HorizontalLayout playerHorizontal) {
        int counter = (playerHorizontal.getClassName().equals("athlete-one"))
                ? ++shidoCounterOne
                : ++shidoCounterTwo;

        Image yellowCard = new Image();
        yellowCard.setSrc("img/yellow-card.jpg");
        yellowCard.setWidth("30px");
        yellowCard.setHeight("40px");
        yellowCard.setClassName("yellow-card");

        Div newAmmoDisplay = new Div();
        newAmmoDisplay.setClassName("ammo-display");

        if (counter <= 2) {
            newAmmoDisplay.add(yellowCard);
            playerHorizontal.add(newAmmoDisplay);
        } else if (counter == 3) {
            Image redCard = new Image();
            redCard.setSrc("img/red-card.jpg");
            redCard.setWidth("30px");
            redCard.setHeight("40px");
            redCard.setClassName("red-card");
            newAmmoDisplay.add(redCard);

            Span hansokuText = new Span("HANSOKU-MAKE");
            hansokuText.getElement().setAttribute("theme", "badge error");
            newAmmoDisplay.add(hansokuText);

            playerHorizontal.add(newAmmoDisplay);

            if (playerHorizontal.getClassName().equals("athlete-one")) {
                shidoCounterOne = 0;
            } else {
                shidoCounterTwo = 0;
            }
        }
    }

    private void getHansokuFromButton(Button button, HorizontalLayout playerHorizontal) {
        Div ammoDisplay = new Div();
        Image redCard = new Image();
        redCard.setSrc("img/red-card.jpg");
        redCard.setWidth("30px");
        redCard.setHeight("40px");
        redCard.setClassName("red-card");
        ammoDisplay.add(redCard);
        Span hansokuText = new Span("HANSOKU-MAKE");
        hansokuText.getElement().setAttribute("theme", "badge error");
        ammoDisplay.add(hansokuText);
        playerHorizontal.add(ammoDisplay);
    }

    private void addScore(Button button, HorizontalLayout playerHorizontal, IntegerField scoreLayout) {
        Span scoreDisplay = new Span();
        scoreDisplay.setClassName("score-display");
        playerHorizontal.add(scoreDisplay);
        JudoScore score = getScoreFromButton(button.getText(), scoreLayout);
        scoreDisplay.setText(score.toString().toUpperCase());
    }

    private JudoScore getScoreFromButton(String buttonText, IntegerField scoreLayout) {
        switch (buttonText) {
            case "Ippon":
                if (scoreLayout.getValue() < 10) {
                    scoreLayout.setValue(10);
                    return JudoScore.IPPON;
                } else {
                    return JudoScore.NOPOINT;
                }
            case "Waza-Ari":
                if (scoreLayout.getValue() < 3) {
                    scoreLayout.setValue(scoreLayout.getValue() + 1);
                    return JudoScore.WAZARI;
                } else if (scoreLayout.getValue() == 3) {
                    return JudoScore.IPPON;
                } else {
                    return JudoScore.NOPOINT;
                }
            case "Yuko":
                if (scoreLayout.getValue() < 10) {
                    scoreLayout.setValue(scoreLayout.getValue() + 1);
                    return JudoScore.YUKO;
                }
            default:
                throw new IllegalArgumentException("Unknown button text: " + buttonText);
        }
    }
}