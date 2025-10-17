package com.neotropic.flow.component.antvx6.demo.components;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
public class NoteComponent extends VerticalLayout{
    public NoteComponent(String contentText){
        Paragraph note = new Paragraph("Note: ");
        note.getStyle().set("font-weight", "bold");
        add(note, new Paragraph(contentText));
    }
}
