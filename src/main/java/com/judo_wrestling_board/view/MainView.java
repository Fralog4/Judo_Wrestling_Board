package com.judo_wrestling_board.view;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

public class MainView extends Composite<VerticalLayout> implements HasComponents, RouterLayout {

    private final Div childContent = new Div();

    public MainView() {
        add(childContent);
        childContent.setClassName("child-content");
        childContent.setSizeFull();
        addClassName("main-view");

        Div judoSection = createSection("Judo");
        judoSection.setClassName("judo-section");
        Button judoButton = new Button("Judo");
        judoButton.addClickListener(event -> navigateToJudo());
        judoButton.setClassName("judo-button");
        judoSection.add(judoButton);

        Div wrestlingSection = createSection("Wrestling");
        wrestlingSection.setClassName("wrestling-section");
        Button wrestlingButton = new Button("Wrestling");
        wrestlingButton.addClickListener(event -> navigateToWrestling());
        wrestlingButton.setClassName("wrestling-button");
        wrestlingSection.add(wrestlingButton);

        childContent.add(judoSection, wrestlingSection);


    }

    private Div createSection(String title) {
        Div section = new Div();
        section.setClassName("section");

        Div titleDiv = new Div();
        titleDiv.setClassName("section-title");

        VerticalLayout sectionLayout = new VerticalLayout(titleDiv);
        sectionLayout.setClassName("section-layout");

        return section;
    }

    private void navigateToWrestling() {
        UI.getCurrent().navigate("wrestlingView");
    }

    private void navigateToJudo() {
        UI.getCurrent().navigate("judoView");
    }
}
