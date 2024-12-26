package com.judo_wrestling_board.view;

import com.judo_wrestling_board.model.Athlete;
import com.judo_wrestling_board.model.Sport;
import com.judo_wrestling_board.model.WeightClass;
import com.judo_wrestling_board.service.AthleteService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("register")
public class RegisterView extends VerticalLayout {

    private final AthleteService athleteService;
    public RegisterView(AthleteService athleteService) {
        this.athleteService = athleteService;
        setClassName("register-view");

        Button goBackToHome = new Button("Back to Home");
        goBackToHome.setClassName("back-button");
        goBackToHome.addClickListener(e -> {
            UI.getCurrent().navigate("/home");
        });

        FormLayout form = new FormLayout();

        TextField nameField = new TextField("Nome");
        nameField.setClassName("name-field");
        TextField lastNameField = new TextField("Cognome");
        lastNameField.setClassName("last-name-field");
        Select weightClassSelect = new Select<>();
        weightClassSelect.setLabel("Classe di peso");
        weightClassSelect.setPlaceholder("Seleziona la classe di peso");
        weightClassSelect.setItems(
                "CLASS_UNDER_60KG",
                "CLASS_60KG",
                "CLASS_70KG",
                "CLASS_80KG",
                "CLASS_90KG",
                "CLASS_100KG");
        weightClassSelect.setClassName("weight-class-select");

        Select sportSelect = new Select<>();
        sportSelect.setLabel("Sport");
        sportSelect.setPlaceholder("Seleziona lo sport");
        sportSelect.setItems("Judo", "Wrestling");
        sportSelect.setClassName("sport-select");

        form.add(nameField, lastNameField, weightClassSelect, sportSelect);

        Button saveButton = new Button("Salva");
        saveButton.addClassName("save-button");
        saveButton.addClickListener(e -> {
            Athlete athlete = new Athlete();
            athlete.setName(nameField.getValue());
            athlete.setSurname(lastNameField.getValue());
            athlete.setSport(sportSelect.getValue().equals("Judo") ? Sport.JUDO : Sport.WRESTLING);
            athlete.setWeightClass(WeightClass.valueOf(weightClassSelect.getValue().toString()));
            athleteService.saveAthlete(athlete);
            UI.getCurrent().navigate("/home");
        });
        form.add(saveButton);
        add(form);
        add(goBackToHome);







    }
}
