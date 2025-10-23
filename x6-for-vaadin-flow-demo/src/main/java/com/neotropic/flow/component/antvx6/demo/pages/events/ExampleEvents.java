package com.neotropic.flow.component.antvx6.demo.pages.events;

import com.neotropic.flow.component.antvx6.AntvX6;
import com.neotropic.flow.component.antvx6.constants.X6Constants;
import com.neotropic.flow.component.antvx6.demo.components.HeaderComponent;
import com.neotropic.flow.component.antvx6.demo.components.NoteComponent;
import com.neotropic.flow.component.antvx6.demo.factory.X6Factory;
import com.neotropic.flow.component.antvx6.objects.Geometry;
import com.neotropic.flow.component.antvx6.objects.X6Node;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.UUID;

/**
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Route("/examples/events")
public class ExampleEvents extends VerticalLayout{
    private X6Factory factory;
    private static String HEADER_TEXT = "X6 Events";
    private static String LISTENER_EVENT = "Events that require a listener";
    private static String EVENT = "Events that don't require a listener";
    private static String DESCRIPTION = "The X6 add-on provides support for event handling within the canvas.";
    private static String LISTENER_EVENT_DESCRIPTION = "For this example we are going to detect the new position of a node, first we must start the event and then add the listener that returns the new position.";
    private static String EVENT_DESCRIPTION = "In this example, we will add node tools to a node, which will appear when hovering over it. To achieve this, we simply need to initialize the event responsible for adding and removing the node tools.";
    private static String NOTE = "Events must be initiated, and you must also add the listener (if applicable) to detect changes. In the AntvX6 class of the add-on you can check the events and listeners currently supported";
    
    public ExampleEvents(){
        this.factory = new X6Factory();
        
        setDefaultHorizontalComponentAlignment(Alignment.START); 
        
        createHeader();
        createExamples();
    }
    
    /*Elements UI*/
    
    private void createHeader(){
        HeaderComponent header = new HeaderComponent(HEADER_TEXT, DESCRIPTION);
        NoteComponent note = new NoteComponent(NOTE);
        add(header, note);
    }
    
    private void createExamples(){
        VerticalLayout lytNodeNewPosition = createEventNodeNewPosition();
        VerticalLayout lytNodeTools = createEventNodeTools();
        add(lytNodeNewPosition, lytNodeTools);
    }
    
    private VerticalLayout createEventNodeNewPosition() {
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        lytBasicCanvas.setWidthFull();
        lytBasicCanvas.setHeight("600px");
        AntvX6 basicCanvas = this.factory.getBasicCanvas(X6Constants.GRAPH_BACKGROUND_COLOR);
        basicCanvas.setSizeFull();
        Div canvasWrapper = new Div(basicCanvas);
        canvasWrapper.setSizeFull();

        Div positionInfo = new Div();
        positionInfo.setText("ID: N/A, X: N/A, Y: N/A");

        basicCanvas.addGraphCreatedListener(evt -> {
            basicCanvas.initEventGetNodeNewPosition();

            X6Node node = new X6Node();
            node.setId("Node 1");
            node.setGeometry(new Geometry(100, 100, 50, 50));
            node.setShape(X6Constants.SHAPE_RECT);
            node.setLabel("Move me");
            basicCanvas.drawNode(node);

            basicCanvas.addNodeMovedListener(listener -> {
                String id = listener.getId();
                double newX = listener.getX();
                double newY = listener.getY();
                positionInfo.setText("ID node: " + id + ", new X: " + newX + ", new Y: " + newY);
                
                //Don't forget update the model
                node.getGeometry().getCoordinates().setX(newX);
                node.getGeometry().getCoordinates().setY(newY);
            });
        });

        lytBasicCanvas.add(new H4(LISTENER_EVENT), new Paragraph(LISTENER_EVENT_DESCRIPTION), positionInfo, canvasWrapper);
        return lytBasicCanvas;
    }

    private VerticalLayout createEventNodeTools() {
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        lytBasicCanvas.setWidthFull();
        lytBasicCanvas.setHeight("600px");
        AntvX6 basicCanvas = this.factory.getBasicCanvas(X6Constants.GRAPH_BACKGROUND_COLOR);
        basicCanvas.setSizeFull();
        Div canvasWrapper = new Div(basicCanvas);
        canvasWrapper.setSizeFull();

        basicCanvas.addGraphCreatedListener(evt -> {
            //Add events to add and remove node tools
            basicCanvas.initEventAddNodeButtonRemove();
            basicCanvas.initEventRemoveNodeButtonRemoveTool();
            
            X6Node node = new X6Node();
            node.setId(UUID.randomUUID().toString());
            node.setGeometry(new Geometry(100, 100, 50, 50));
            node.setShape(X6Constants.SHAPE_RECT);
            node.setLabel("Hover over me");
            
            basicCanvas.drawNode(node);
        });

        lytBasicCanvas.add(new H4(EVENT), new Paragraph(EVENT_DESCRIPTION), canvasWrapper);
        return lytBasicCanvas;
    }
    
    /*End of elementes UI*/ 
}
