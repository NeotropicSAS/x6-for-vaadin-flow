package com.neotropic.flow.component.antvx6.demo.pages.basicExamples;

import com.neotropic.flow.component.antvx6.AntvX6;
import com.neotropic.flow.component.antvx6.constants.X6Constants;
import com.neotropic.flow.component.antvx6.demo.components.HeaderComponent;
import com.neotropic.flow.component.antvx6.demo.components.NoteComponent;
import com.neotropic.flow.component.antvx6.demo.factory.X6Factory;
import com.neotropic.flow.component.antvx6.objects.Geometry;
import com.neotropic.flow.component.antvx6.objects.X6Node;
import com.neotropic.flow.component.antvx6.objects.X6NodeBackground;
import com.neotropic.flow.component.antvx6.objects.X6NodeText;
import com.neotropic.flow.component.antvx6.utilities.X6NodeTextUtilities;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.UUID;

/**
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Route("/basic/examples/nodes")
public class ExampleNodes extends VerticalLayout{
    private final X6Factory factory;
    private static final String HEADER_TEXT = "X6 Nodes";
    private static final String DRAW_NODE = "Draw Node";
    private static final String DRAW_BACKGROUND = "Draw Node Background";
    private static final String DRAW_NODE_TEXT = "Draw Node Text";
    private static final String DESCRIPTION = "The X6 add-on supports three types of nodes with different responsibilities.";
    private static final String NOTE = "Whenever a change is made to the canvas (add nodes, edges, change styles, etc.), it must be done through an x6 or vaadin event, to update the current view.";
    private static final String DESCRIPTION_DRAW_NODE = "It's the basic node that is drawn on a canvas.";
    private static final String DESCRIPTION_DRAW_BACKGROUND = "It is a resizable node that acts as a background for other nodes on the canvas. By double clicking on the background you can resize it.";
    private static final String DESCRIPTION_DRAW_BACKGROUND_NOTE = "Don't forget to add the transform plugin and the background resize event." ;
    private static final String DESCRIPTION_DRAW_NODE_TEXT = "They are nodes that serve as texts for other nodes, it is commonly used when more customization of the basic labels of a node is needed.";
    
    public ExampleNodes(X6Factory factory){
        this.factory = factory;
        
        setSizeFull();
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
        VerticalLayout lytDrawNode = createAddNode();
        VerticalLayout lytDrawBackground = createAddBackground();
        VerticalLayout lytDrawText = createAddNodeText();
        add(lytDrawNode, lytDrawBackground, lytDrawText);
    }
    
    private VerticalLayout createAddNode(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        AntvX6 basicCanvas = factory.getBasicCanvas(600, 600, X6Constants.GRAPH_BACKGROUND_COLOR);
       
        // Thorough an event , we draw the nodes
        basicCanvas.addGraphCreatedListener(evt -> {
            //Examples of how to create X6Nodes
            X6Node rect = new X6Node();
            rect.setId(UUID.randomUUID().toString());
            rect.setGeometry(new Geometry(100, 100, 50, 50));
            rect.setShape(X6Constants.SHAPE_RECT);
            rect.setLabel("I'm a Rectangle of X6");
            rect.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);
            
            X6Node ellipse = new X6Node();
            ellipse.setId(UUID.randomUUID().toString());
            ellipse.setGeometry(new Geometry(250, 100, 50, 80));
            ellipse.setShape(X6Constants.SHAPE_ELLIPSE);
            ellipse.setLabel("I'm a Ellipse of X6");
            ellipse.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);
            
            X6Node circle = new X6Node();
            circle.setId(UUID.randomUUID().toString());
            circle.setGeometry(new Geometry(400, 100, 50, 50));
            circle.setShape(X6Constants.SHAPE_CIRCLE);
            circle.setLabel("I'm a Circle of X6");
            circle.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);
            
            X6Node image = new X6Node();
            image.setId(UUID.randomUUID().toString());
            image.setGeometry(new Geometry(250, 250, 80, 80));
            image.setShape(X6Constants.SHAPE_IMAGE);
            image.setLabel("I'm a Image of X6");
            image.setImgUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaZj-3Gf3IOMX44LXAIpUVhghfvXsCEId-EYmMtnvoKLnsMLUQxmuj4EH8aUfYiJoMn9o&usqp=CAU");
            image.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);
            
            //Add the nodes to the canvas
            basicCanvas.drawNode(rect);
            basicCanvas.drawNode(ellipse);
            basicCanvas.drawNode(circle);
            basicCanvas.drawNode(image);
        });
        
        lytBasicCanvas.add(new H4(DRAW_NODE), new Paragraph(DESCRIPTION_DRAW_NODE) ,basicCanvas);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createAddBackground(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        AntvX6 basicCanvas = factory.getBasicCanvas(600, 600, X6Constants.GRAPH_BACKGROUND_COLOR);
       
        // through an event, we create the nodes
        basicCanvas.addGraphCreatedListener(evt -> {
            //Transform plugin.
            basicCanvas.addTransformPlugin(false, true, true, 20, 20, true);
            
            //Resize background event.
            basicCanvas.initEventResizeNodeBackgroundDblClick();
            
            //Node background
            X6NodeBackground background = new X6NodeBackground();
            background.setId(UUID.randomUUID().toString());
            background.setGeometry(new Geometry(100, 100, 350, 350));
            background.setShape(X6Constants.SHAPE_IMAGE);
            background.setLabel("I'm a Background of X6");
            background.setImgUrl("https://www.livehome3d.com/assets/img/articles/how-to-draw-a-floor-plan/floor-plan-of-a-house-with-a-pool.jpg");
            background.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);
            
            X6Node andElement = new X6Node();
            andElement.setId(UUID.randomUUID().toString());
            andElement.setGeometry(new Geometry(100, 100, 20, 20));
            andElement.setShape(X6Constants.SHAPE_RECT);
            andElement.setLabel("I'm not the background");
            andElement.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);
            
            //add the nodes
            basicCanvas.drawNodeBackground(background);
            basicCanvas.drawNode(andElement);
        });
        
        lytBasicCanvas.add(new H4(DRAW_BACKGROUND ), new Paragraph(DESCRIPTION_DRAW_BACKGROUND), new NoteComponent(DESCRIPTION_DRAW_BACKGROUND_NOTE) ,basicCanvas);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createAddNodeText(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        AntvX6 basicCanvas = factory.getInteractionsCanvas(600, 600, X6Constants.GRAPH_BACKGROUND_COLOR);
       
        //through an event, we create the node
        basicCanvas.addGraphCreatedListener(evt -> {
            //main node
            X6Node node = new X6Node();
            node.setId(UUID.randomUUID().toString());
            node.setGeometry(new Geometry(100, 100, 100, 100));
            node.setShape(X6Constants.SHAPE_RECT);
            node.setLabel("I'm the main node.");
            node.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_DEFAULT);
            node.setMovable(true);
            
            //node text
            X6NodeText text = new X6NodeText();
            text.setId(UUID.randomUUID().toString());
            text.setGeometry(new Geometry(0, 0, 0, 0));
            text.setShape(X6Constants.SHAPE_RECT);
            text.setLabel("I'm the node text of the main node.");
            text.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_DEFAULT);
            //we set its parent (main node)
            text.setParentId(node.getId());
            text.setMovable(false);
            //position it below the node
            text.getNodeLabelStyles().setFontColor("gray");
            text.getNodeStyles().setBorderRadius(8);
            text.getNodeStyles().setStrokeWidth(0);
            text.getNodeStyles().setFillColor("#DCD0FF");
            
            //Using utilities to create the dimensions and position of the NodeText
            X6NodeTextUtilities.calculateLabelDimensions(text.getGeometry(), text.getLabel(), text.getNodeLabelStyles().getFontSize());
            X6NodeTextUtilities.calculateLabelPosition(node.getGeometry(), text.getGeometry(), X6Constants.BOTTOM, 10);
            
            //Draw the node
            basicCanvas.drawNode(node);
            basicCanvas.drawText(text);
        });
        
        lytBasicCanvas.add(new H4(DRAW_NODE_TEXT), new Paragraph(DESCRIPTION_DRAW_NODE_TEXT) ,basicCanvas);
        return lytBasicCanvas;
    }
    
    /*End of elementes UI*/ 
}
