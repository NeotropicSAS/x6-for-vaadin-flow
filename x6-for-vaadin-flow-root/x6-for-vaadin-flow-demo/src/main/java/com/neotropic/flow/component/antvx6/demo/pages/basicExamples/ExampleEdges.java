package com.neotropic.flow.component.antvx6.demo.pages.basicExamples;

import com.neotropic.flow.component.antvx6.AntvX6;
import com.neotropic.flow.component.antvx6.constants.X6Constants;
import com.neotropic.flow.component.antvx6.demo.components.HeaderComponent;
import com.neotropic.flow.component.antvx6.demo.components.NoteComponent;
import com.neotropic.flow.component.antvx6.demo.factory.X6Factory;
import com.neotropic.flow.component.antvx6.objects.Geometry;
import com.neotropic.flow.component.antvx6.objects.Vertex;
import com.neotropic.flow.component.antvx6.objects.X6Edge;
import com.neotropic.flow.component.antvx6.objects.X6EdgeLabel;
import com.neotropic.flow.component.antvx6.objects.X6Node;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Route("/basic/examples/edges")
public class ExampleEdges extends VerticalLayout{
    private final X6Factory factory;
    private static final String HEADER_TEXT = "X6 Edge";
    private static final String DRAW_EDGE_BASIC = "Draw Edge without labels";
    private static final String DRAW_EDGE_WITH_LABEL = "Draw Edge with a unique label";
    private static final String DRAW_EDGE_MULTIPLE_LABELS = "Draw Edge with multiple labels";
    private static final String DRAW_EDGE_THROUGH_PORT = "Draw Edge through ports";
    private static final String DRAW_EDGE_VERTICES = "Draw Edge with Vertices";
    private static final String DESCRIPTION = "The X6 add-on supports edge handling, from edges without labels to edges with multiple labels.";
    private static final String NOTE = "Whenever a change is made to the canvas (add nodes, edges, change styles, etc.), it must be done through an x6 or vaadin event, to update the current view.";
    private static final String DESCRIPTION_DRAW_EDGE_BASIC = "Edge without labels";
    private static final String DESCRIPTION_DRAW_EDGE_WITH_LABEL = "Edge with a single label";
    private static final String DESCRIPTION_DRAW_EDGE_MULTIPLE_LABELS = "Edge with multiple labels";
    private static final String DESCRIPTION_DRAW_EDGE_THROUGH_PORT = "We can create edges through nodes that manage ports (Only nodes of type X6Node have the characteristic of managing a connection port).";
    private static final String DESCRIPTION_DRAW_EDGE_VERTICES = "We can create vertices which are points inside the edges. If you want to add vertices from the UI use the add vertices and segments tool.";
    
    public ExampleEdges(X6Factory factory){
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
        VerticalLayout lytDrawBasicEdge = createAddBasicEdge();
        VerticalLayout lytDrawEdge = createAddX6EdgeSingleLabel();
        VerticalLayout lytDrawEdgeMultipleLabels = createAddX6EdgeMultipleLabels();
        VerticalLayout lytPort = connectionThroughPort();
        VerticalLayout lytVertices = createVertices();
        add(lytDrawBasicEdge, lytDrawEdge, lytDrawEdgeMultipleLabels, lytPort, lytVertices);
    }
    
    private VerticalLayout createAddBasicEdge(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        AntvX6 basicCanvas = factory.getBasicCanvas(600, 600, X6Constants.GRAPH_BACKGROUND_COLOR);
        
        //Through an event, draw the elements
        basicCanvas.addGraphCreatedListener(evt -> {
            // Node source
            X6Node source = new X6Node();
            source.setId(UUID.randomUUID().toString());
            source.setGeometry(new Geometry(100, 100, 50, 50));
            source.setShape(X6Constants.SHAPE_RECT);
            
            // Node target
            X6Node target = new X6Node();
            target.setId(UUID.randomUUID().toString());
            target.setGeometry(new Geometry(300, 100, 50, 50));
            target.setShape(X6Constants.SHAPE_RECT);
            
            //Create the basic edge
            X6Edge edge = new X6Edge(UUID.randomUUID().toString(), source.getId(), target.getId());
            
            //Add the elements
            basicCanvas.drawNode(source);
            basicCanvas.drawNode(target);
            basicCanvas.drawEdge(edge);
        });
        
        lytBasicCanvas.add(new H4(DRAW_EDGE_BASIC), new Paragraph(DESCRIPTION_DRAW_EDGE_BASIC) ,basicCanvas);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createAddX6EdgeSingleLabel(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        AntvX6 basicCanvas = factory.getBasicCanvas(600, 600, X6Constants.GRAPH_BACKGROUND_COLOR);
       
        //Through an event, we create the elements
        basicCanvas.addGraphCreatedListener(evt -> {
            // Node source
            X6Node source = new X6Node();
            source.setId(UUID.randomUUID().toString());
            source.setGeometry(new Geometry(100, 100, 50, 50));
            source.setShape(X6Constants.SHAPE_RECT);
            
            // Node target
            X6Node target = new X6Node();
            target.setId(UUID.randomUUID().toString());
            target.setGeometry(new Geometry(300, 100, 50, 50));
            target.setShape(X6Constants.SHAPE_RECT);
            
            //Create an edge with a single label in the middle of the edge
            X6Edge edge = new X6Edge(UUID.randomUUID().toString(), source.getId(), target.getId(), "label-connection");
            
            //Add the elements
            basicCanvas.drawNode(source);
            basicCanvas.drawNode(target);
            basicCanvas.drawEdge(edge);
        });
        
        lytBasicCanvas.add(new H4(DRAW_EDGE_WITH_LABEL), new Paragraph(DESCRIPTION_DRAW_EDGE_WITH_LABEL) ,basicCanvas);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createAddX6EdgeMultipleLabels(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        AntvX6 basicCanvas = factory.getBasicCanvas(600, 600, X6Constants.GRAPH_BACKGROUND_COLOR);
       
        //Through an event, we create the elements
        basicCanvas.addGraphCreatedListener(evt -> {
            // Node source
            X6Node source = new X6Node();
            source.setId(UUID.randomUUID().toString());
            source.setGeometry(new Geometry(100, 100, 50, 50));
            source.setShape(X6Constants.SHAPE_RECT);
            
            // Node target
            X6Node target = new X6Node();
            target.setId(UUID.randomUUID().toString());
            target.setGeometry(new Geometry(500, 100, 50, 50));
            target.setShape(X6Constants.SHAPE_RECT);
            
            //Create an edge with multiple labels
            List<X6EdgeLabel> labels = new ArrayList<>();
            labels.add(new X6EdgeLabel("label-left", 0.2));
            labels.add(new X6EdgeLabel("label-middle"));
            labels.add(new X6EdgeLabel("label-right", 0.8));
            
            X6Edge edge = new X6Edge(UUID.randomUUID().toString(), source.getId(), target.getId(), labels);
            
            //Add the elements
            basicCanvas.drawNode(source);
            basicCanvas.drawNode(target);
            basicCanvas.drawEdge(edge);
        });
        
        lytBasicCanvas.add(new H4(DRAW_EDGE_MULTIPLE_LABELS), new Paragraph(DESCRIPTION_DRAW_EDGE_MULTIPLE_LABELS), basicCanvas);
        return lytBasicCanvas;
    }
    
    private VerticalLayout connectionThroughPort(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        AntvX6 basicCanvas = this.factory.getBasicCanvas(600, 600, X6Constants.GRAPH_BACKGROUND_COLOR);
        //through an event, we create the elements
        basicCanvas.addGraphCreatedListener(evt -> {
            // Node source
            X6Node source = new X6Node();
            source.setId(UUID.randomUUID().toString());
            source.setGeometry(new Geometry(100, 100, 50, 50));
            source.setShape(X6Constants.SHAPE_RECT);
            source.setPort(true);
            
            // Node target
            X6Node target = new X6Node();
            target.setId(UUID.randomUUID().toString());
            target.setGeometry(new Geometry(300, 100, 50, 50));
            target.setShape(X6Constants.SHAPE_RECT);
            target.setPort(true);
            
            //Add the nodes
            basicCanvas.drawNode(source);
            basicCanvas.drawNode(target);
            
            //Start event that detects when two nodes connect, these method delete the edge that was created
            basicCanvas.initEventNodesConnected();
            
            //If a connection was created then we can add our edge
            basicCanvas.addNodesConnectedListener(evtEdge -> {
                //Create the edge (whatever of edge you want)
                X6Edge edge = new X6Edge(UUID.randomUUID().toString(), evtEdge.getIdSource(), evtEdge.getIdTarget(), "new-connection");
                //add the edge
                basicCanvas.drawEdge(edge);
            });
            
        });
        
        lytBasicCanvas.add(new H4(DRAW_EDGE_THROUGH_PORT ), new Paragraph(DESCRIPTION_DRAW_EDGE_THROUGH_PORT) ,basicCanvas);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createVertices(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        AntvX6 basicCanvas = this.factory.getBasicCanvas(600, 600, X6Constants.GRAPH_BACKGROUND_COLOR);
       
        //Through an event , we draw the nodes
        basicCanvas.addGraphCreatedListener(evt -> {
            //Create the nodes
            X6Node source = new X6Node();
            source.setId(UUID.randomUUID().toString());
            source.setGeometry(new Geometry(50, 200, 50, 50));
            source.setShape(X6Constants.SHAPE_RECT);
            source.setLabel("Source");
            
            //Create the node
            X6Node target = new X6Node();
            target.setId(UUID.randomUUID().toString());
            target.setGeometry(new Geometry(400, 200, 50, 50));
            target.setShape(X6Constants.SHAPE_RECT);
            target.setLabel("Target");

            //Create the Edge
            X6Edge edge = new X6Edge(UUID.randomUUID().toString(), source.getId(), target.getId(), "label-connection");
            
            //Create a vertex
            Vertex vertex1 = new Vertex(140, 120);
            edge.getVertices().add(vertex1);
           
            
            //Add the elements to the canvas
            basicCanvas.drawNode(source);
            basicCanvas.drawNode(target);
            basicCanvas.drawEdge(edge);
        });
        
        lytBasicCanvas.add(new H4(DRAW_EDGE_VERTICES), new Paragraph(DESCRIPTION_DRAW_EDGE_VERTICES) ,basicCanvas);
        return lytBasicCanvas;
    }
    
    /*End of elementes UI*/ 
}
