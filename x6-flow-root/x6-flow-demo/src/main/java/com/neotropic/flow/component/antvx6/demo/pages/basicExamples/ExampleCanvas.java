package com.neotropic.flow.component.antvx6.demo.pages.basicExamples;

import com.neotropic.flow.component.antvx6.AntvX6;
import com.neotropic.flow.component.antvx6.constants.X6Constants;
import com.neotropic.flow.component.antvx6.demo.components.HeaderComponent;
import com.neotropic.flow.component.antvx6.demo.factory.X6Factory;
import com.neotropic.flow.component.antvx6.objects.Geometry;
import com.neotropic.flow.component.antvx6.objects.X6Node;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.UUID;

/**
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Route("/basic/examples/canvas")
public class ExampleCanvas extends VerticalLayout{
    private final X6Factory factory;
    private static final String HEADER_TEXT = "X6 Canvas";
    private static final String DESCRIPTION = "The X6 add-on currently supports two types of canvas:";
    private static final String BASIC_CANVAS = "Basic Canvas";
    private static final String INTERACTIONS_CANVAS = "Interactions Canvas";
    private static final String DESCRIPTION_BASIC_CANVAS = "It's a basic canvas where there are no human-node iteration restrictions.";
    private static final String DESCRIPTION_INTERACTIONS_CANVAS = "It's a canvas where you can restrict or not the interaction of human-node, for the node you want.";
    
    public ExampleCanvas(X6Factory factory){
        this.factory = factory;
        
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.START); 
        
        createHeader();
        createExamples();   
    }
    
    private X6Node createNode(double x, double y, double width, double height, String label, String labelPosition, boolean movable){
        /*
            String id, double x, double y,double width, double height, String shape
            Without these attributes, a node cannot exist.
        */
        X6Node node = new X6Node();
        
        node.setId(UUID.randomUUID().toString());
        node.setGeometry(new Geometry(x, y, width, height));
        node.setShape(X6Constants.SHAPE_RECT);
        node.setLabel(label);
        node.getNodeLabelStyles().setLabelPosition(labelPosition);
        node.setMovable(movable);
        
        return node;
    }
    
    /*Elements UI*/ 
    
    private void createHeader(){
        HeaderComponent header = new HeaderComponent(HEADER_TEXT, DESCRIPTION);
        add(header);
    }
    
    private void createExamples(){
        VerticalLayout lytBasicCanvas = createBasicCanvas();
        VerticalLayout lytInteractionsCanvas = createInteractionsCanvas();
        add(lytBasicCanvas, lytInteractionsCanvas);
    }
    
    private VerticalLayout createBasicCanvas(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        
        // Here we create the canvas (take a look to class X6Factory)
        AntvX6 basicCanvas = factory.getBasicCanvas(600, 600, X6Constants.GRAPH_BACKGROUND_COLOR);
        
        /*
            Whenever a change is made to the canvas, it must be notified with an event, so that it is updated.
            For static views, for example, the most common is through an event that reports that the canvas was created, for cases of adding elements.
            Vaadin events also work to make changes to the canvas
        */
        
        basicCanvas.addGraphCreatedListener(evt -> {
            X6Node node = createNode(100, 100, 60, 60, "I am a node", X6Constants.LABEL_NODE_POSITION_BOTTOM, true);
            basicCanvas.drawNode(node);
        });
        
        lytBasicCanvas.add(new H4(BASIC_CANVAS), new Paragraph(DESCRIPTION_BASIC_CANVAS) ,basicCanvas);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createInteractionsCanvas(){  
        VerticalLayout lytInteractionsCanvas = new VerticalLayout();
        // Here we create the canvas (take a look to class X6Factory)
        AntvX6 interactionsCanvas = factory.getInteractionsCanvas(600, 600, X6Constants.GRAPH_BACKGROUND_COLOR);
        
        /*
            Whenever a change is made to the canvas, it must be notified with an event, so that it is updated.
            For static views, for example, the most common is through an event that reports that the canvas was created, for cases of adding elements.
            Vaadin events also work to make changes to the canvas
        */
        
        interactionsCanvas.addGraphCreatedListener(evt -> {
            X6Node node1 = createNode(100, 100, 60, 60, "You can move me", X6Constants.LABEL_NODE_POSITION_BOTTOM, true);
            X6Node node2 = createNode(300, 300,60, 60, "I can't move ", X6Constants.LABEL_NODE_POSITION_BOTTOM, false);
            interactionsCanvas.drawNode(node1);
            interactionsCanvas.drawNode(node2);
        });
        
        lytInteractionsCanvas.add(new H4(INTERACTIONS_CANVAS), new Paragraph(DESCRIPTION_INTERACTIONS_CANVAS), interactionsCanvas);
        return lytInteractionsCanvas;
        
    }
    
    /*End of elementes UI*/ 
}
