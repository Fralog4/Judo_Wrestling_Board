package com.judo_wrestling_board.view;


import com.judo_wrestling_board.service.AthleteService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;

@Route("wrestlingView")

public class WrestlingView extends Div {

    private final AthleteService athleteService;
    private int counter = 0;


    public WrestlingView(AthleteService athleteService) {
        this.athleteService = athleteService;

        addClassName("wrestling-view");

        HorizontalLayout playerOneHorizontal = new HorizontalLayout();
        playerOneHorizontal.setSpacing(true);
        playerOneHorizontal.setClassName("player-one-horizontal");
        playerOneHorizontal.setWrap(true);
        add(playerOneHorizontal);


        HorizontalLayout playerTwoHorizontal = new HorizontalLayout();
        playerTwoHorizontal.setSpacing(true);
        playerTwoHorizontal.setClassName("player-two-horizontal");
        playerTwoHorizontal.setWrap(true);
        add(playerTwoHorizontal);



        IntegerField score = new IntegerField();
        score.setLabel("Punteggio");
        score.setClassName("score-wrestler");
        score.setMin(0);
        score.setMax(2);
        score.setStepButtonsVisible(true);
        score.setValue(0);


        IntegerField scoreTwo = new IntegerField();
        scoreTwo.setLabel("Punteggio");
        scoreTwo.setClassName("score-wrestler-two");
        scoreTwo.setMin(0);
        scoreTwo.setMax(2);
        scoreTwo.setStepButtonsVisible(true);
        scoreTwo.setValue(0);

        Select athleteOneChoose = new Select<>();
        athleteOneChoose.setItems(athleteService.getAllAthletes());
        athleteOneChoose.setLabel("Lottatore 1");
        athleteOneChoose.setPlaceholder("Seleziona un atleta");
        athleteOneChoose.setClassName("wrestler-one-choose");

        Select athleteTwoChoose = new Select<>();
        athleteTwoChoose.setItems(athleteService.getAllAthletes());
        athleteTwoChoose.setLabel("Lottatore 2");
        athleteTwoChoose.setPlaceholder("Seleziona un atleta");
        athleteTwoChoose.setClassName("wrestler-two-choose");



        Button ammonition = new Button("Ammonition");
        ammonition.setClassName("ammonition-button");
        ammonition.addClickListener(e -> addAmmonition(ammonition, playerOneHorizontal));

        Button ammonitionTwo = new Button("Ammonition");
        ammonitionTwo.setClassName("ammonition-button");
        ammonitionTwo.addClickListener(e -> addAmmonition(ammonitionTwo, playerTwoHorizontal));

        Button fourPoints = new Button("4 Points");
        fourPoints.setClassName("four-points-button");
        fourPoints.addClickListener(e -> addScore(fourPoints, playerOneHorizontal, score));

        Button twoPoints = new Button("2 Points");
        twoPoints.setClassName("two-points-button");
        twoPoints.addClickListener(e -> addScore(twoPoints, playerOneHorizontal, score));

        Button onePoint = new Button("1 Point");
        onePoint.setClassName("one-point-button");
        onePoint.addClickListener(e -> addScore(onePoint, playerOneHorizontal, score));

        Button onePointTwo = new Button("1 Point");
        onePointTwo.setClassName("one-point-button");
        onePointTwo.addClickListener(e -> addScore(onePointTwo, playerTwoHorizontal, scoreTwo));

        Button twoPointsTwo = new Button("2 Points");
        twoPointsTwo.setClassName("two-points-button");
        twoPointsTwo.addClickListener(e -> addScore(twoPointsTwo, playerTwoHorizontal, scoreTwo));

        Button fourPointsTwo = new Button("4 Points");
        fourPointsTwo.setClassName("four-points-button");
        fourPointsTwo.addClickListener(e -> addScore(fourPointsTwo, playerTwoHorizontal, scoreTwo));



        Button goBackToHome = new Button("Back to Home");
        goBackToHome.setClassName("back-button");
        goBackToHome.addClickListener(e -> {
            UI.getCurrent().navigate("/home");
        });

        Button refreshButton = new Button("Refresh");
        refreshButton.setClassName("refresh-button");
        refreshButton.addClickListener(e -> {
            UI.getCurrent().refreshCurrentRoute(true);
        });


        playerOneHorizontal.add(athleteOneChoose,score,ammonition, fourPoints, twoPoints, onePoint);
        playerTwoHorizontal.add(athleteTwoChoose,scoreTwo,ammonitionTwo, fourPointsTwo, twoPointsTwo, onePointTwo);
        add(playerOneHorizontal,playerTwoHorizontal,goBackToHome, refreshButton);









    }

    private void addScore(Button button, HorizontalLayout playerHorizontal, IntegerField score) {

        switch (button.getText()) {
            case "1 Point":
                score.setValue(score.getValue() + 1);
                break;
            case "2 Points":
                score.setValue(score.getValue() + 2);
                break;
            case "4 Points":
                score.setValue(score.getValue() + 4);
                break;
        }
        playerHorizontal.add(score);

    }

    private void addAmmonition(Button ammonition, HorizontalLayout playerOneHorizontal) {

        counter++;
        Icon ammonitionIcon = createIcon(VaadinIcon.WARNING);
        ammonitionIcon.setClassName("ammonition-icon");
        ammonitionIcon.getElement().setAttribute("theme", "badge error");
        ammonitionIcon.setSize("3em");

        Div newAmmoDisplay = new Div();
        newAmmoDisplay.getElement().setAttribute("theme", "badge error");
        newAmmoDisplay.setClassName("ammo-display");
        newAmmoDisplay.add(ammonitionIcon);


        if(counter >2) {

            Div expulsion = new Div();
            expulsion.setText("Expulsion");
            expulsion.getElement().setAttribute("theme", "badge error");
            expulsion.setClassName("expulsion");
            playerOneHorizontal.add(expulsion);

            counter = 0;
        }

        playerOneHorizontal.add(newAmmoDisplay);
    }


    private Icon createIcon(VaadinIcon vaadinIcon) {
        Icon icon = vaadinIcon.create();
        icon.getStyle().set("padding", "var(--lumo-space-xs)");
        return icon;
    }
}
