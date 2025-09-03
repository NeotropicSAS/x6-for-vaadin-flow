package com.neotropic.flow.component.antvx6.demo.components;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
public class HeaderComponent extends VerticalLayout {
    public HeaderComponent(String headerText, String descriptionText){
        H2 header = new H2(headerText);
        LineHeaderComponent lineHeader = new LineHeaderComponent();
        Paragraph description = new Paragraph(descriptionText);
        add(header, lineHeader, description);
    }
}
