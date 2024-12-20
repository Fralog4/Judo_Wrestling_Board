package com.judo_wrestling_board.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "home", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Home")
public class HomeView extends Div {
    public HomeView() {
        setClassName("home-page");


        Button registerAthlete= new Button("Registra un atleta");
        registerAthlete.addClickListener(event -> navigateToRegisterView());
        registerAthlete.setClassName("register-athlete-button");

        add(registerAthlete);
    }

    private void navigateToRegisterView() {
        UI.getCurrent().navigate("register");
    }
}