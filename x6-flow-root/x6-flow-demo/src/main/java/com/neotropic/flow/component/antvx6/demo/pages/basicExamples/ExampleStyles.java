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
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Route("/basic/examples/styles")
public class ExampleStyles extends VerticalLayout{
    private X6Factory factory;
    private static String HEADER_TEXT = "X6 Styles";
    private static String STYLES_NODE = "Node Styles"; 
    private static String STYLES_EDGE = "Edge Styles"; 
    private static String DESCRIPTION = "The x6 add-on supports handling styles for nodes and edges.";
    private static String DESCRIPTION_STYLES_NODE = "The supported styles for nodes apply to all node types available in the Add-on: X6Node, X6NodeBackground, and X6NodeText.";
    private static String DESCRIPTION_STYLES_EDGE = "The supported styles for edges apply to all edges types available in the Add-on: X6BasicEdge, X6Edge and soon X6EdgeMultipleLabels.";
    private static String NOTE = "Whenever a change is made to the canvas (add nodes, edges, change styles, etc.), it must be done through an x6 or vaadin event, to update the current view.";
    private static String EXAMPLE_CHANGE_COLOR = "Example change color";
    private static String EXAMPLE_CHANGE_COLOR_DESCRIPTION = "You can use the vaadin button click event to update the UI. (Any X6 or vaadin event will work and DON'T forget to always update the model";
    
    private int currentColor = 0;
    
    public ExampleStyles(){
        this.factory = new X6Factory();
        
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
        VerticalLayout lytNodeStyles = createNodeStyles();
        VerticalLayout lytEdgeStyles = createEdgeStyles();
        VerticalLayout lytChangeColor = createExampleChangeColor();
        add(lytNodeStyles, lytEdgeStyles, lytChangeColor);
    }
    
    private VerticalLayout createNodeStyles(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        AntvX6 basicCanvas = this.factory.getBasicCanvas(600, 600, X6Constants.GRAPH_BACKGROUND_COLOR);
       
        //Through an event , we draw the nodes
        basicCanvas.addGraphCreatedListener(evt -> {
            //Create the node
            X6Node node = new X6Node();
            node.setId(UUID.randomUUID().toString());
            node.setGeometry(new Geometry(100, 100, 50, 50));
            node.setShape(X6Constants.SHAPE_RECT);
            node.setLabel("I'm a X6Node");
            
            //Apply styles to Node
            node.getNodeStyles().setBorderRadius(8);
            node.getNodeStyles().setDash("4.4");
            node.getNodeStyles().setFillColor("red");
            node.getNodeStyles().setStrokeColor("blue");
            node.getNodeStyles().setStrokeWidth(3);
            node.getNodeStyles().setzIndex(2);
            
            //Apply styles to Node's label
            node.getLabelStyles().setFontFamily("Courier");
            node.getLabelStyles().setFontSize(15);
            node.getLabelStyles().setFontColor("red");
            node.getLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);
            
            
            //Add the nodes to the canvas
            basicCanvas.drawNode(node);
        });
        
        lytBasicCanvas.add(new H4(STYLES_NODE), new Paragraph(DESCRIPTION_STYLES_NODE), nodeStylesSupported() ,basicCanvas);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createEdgeStyles(){
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
            
            //Create some vertex (It's not neccesary), It's just to show how borderRadious works
            Vertex vertex1 = new Vertex(140, 120);
            edge.getVertices().add(vertex1);
            
            //Apply styles
            edge.getEdgeStyles().setBorderRadius(8);
            edge.getEdgeStyles().setDash(4.4);
            edge.getEdgeStyles().setStrokeColor("red");
            edge.getEdgeStyles().setStrokeWidth(5);
            edge.getEdgeStyles().setzIndex(1);
            
            X6EdgeLabel label = edge.getLabelAt(0);
            
            label.getStyles().setFillColor("gray");
            label.getStyles().setFontColor("red");
            label.getStyles().setFontSize(15);
            label.getStyles().setFontFamily("Courier");
            label.getStyles().setStrokeColor("red");
            label.getStyles().setStrokeWidth(2);
            label.getStyles().setBorderRadius(4);

            //Add the elements to the canvas
            basicCanvas.drawNode(source);
            basicCanvas.drawNode(target);
            basicCanvas.drawEdge(edge);
        });
        
        lytBasicCanvas.add(new H4(STYLES_EDGE), new Paragraph(DESCRIPTION_STYLES_EDGE), edgeStylesSupported() ,basicCanvas);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createExampleChangeColor(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        AntvX6 basicCanvas = this.factory.getBasicCanvas(600, 600, X6Constants.GRAPH_BACKGROUND_COLOR);
       
        Button btnChangeColor = new Button("Change color");
        //Through an event , we draw the nodes
        basicCanvas.addGraphCreatedListener(evt -> {
            //Create the nodes
            X6Node node = new X6Node();
            node.setId(UUID.randomUUID().toString());
            node.setGeometry(new Geometry(50, 200, 50, 50));
            node.setShape(X6Constants.SHAPE_RECT);
            node.setLabel("I'm a X6Node");
            node.getLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);
            
            //Add the elements to the canvas
            basicCanvas.drawNode(node);
            
            btnChangeColor.addClickListener(event -> {
                List<String> colors = Arrays.asList("Red", "Blue", "Green", "Yellow", "Purple", "Orange", "Black", "White");
                basicCanvas.setNodeStyle(node.getId(), X6Constants.STYLE_FILLCOLOR, colors.get(currentColor));
                //It's always advisable to keep the model updated 
                node.setStyle(X6Constants.STYLE_FILLCOLOR, colors.get(currentColor));
                node.getNodeStyles().setFillColor(colors.get(currentColor));
                
                currentColor += 1;
                if(currentColor == 8)
                    currentColor = 0;
                
            });
        });
        
        lytBasicCanvas.add(new H4(EXAMPLE_CHANGE_COLOR),new Paragraph(EXAMPLE_CHANGE_COLOR_DESCRIPTION), basicCanvas, btnChangeColor);
        return lytBasicCanvas;
    }
    
    private HorizontalLayout nodeStylesSupported(){
        HorizontalLayout lytNodeStylesSupported = new HorizontalLayout();
        UnorderedList nodeStylesList = new UnorderedList(
                new ListItem("Border Radius"),
                new ListItem("Dash"),
                new ListItem("Fill Color"),
                new ListItem("Stroke Color"),
                new ListItem("Stroke Width"),
                new ListItem("Z-Index")
        );
        VerticalLayout nodeStylesSection = new VerticalLayout(
                new Span("Node Styles"),
                nodeStylesList
        );

        UnorderedList labelStylesList = new UnorderedList(
                new ListItem("Font Family"),
                new ListItem("Font Size"),
                new ListItem("Text Color"),
                new ListItem("Label Position")
        );
        VerticalLayout labelStylesSection = new VerticalLayout(
                new Span("Label Styles"),
                labelStylesList
        );

        lytNodeStylesSupported.add(nodeStylesSection, labelStylesSection);
        lytNodeStylesSupported.setSpacing(true);
        lytNodeStylesSupported.setPadding(true);
        return lytNodeStylesSupported;
    }
    
    private HorizontalLayout edgeStylesSupported(){
        HorizontalLayout lytEdgeStylesSupported = new HorizontalLayout();
        UnorderedList edgeStylesList = new UnorderedList(
                new ListItem("Border Radius"),
                new ListItem("Dash"),
                new ListItem("Font family"),
                new ListItem("Font size"),
                new ListItem("Text color"),
                new ListItem("Stroke color"),
                new ListItem("Stroke width"),
                new ListItem("Z-Index")
        );
        VerticalLayout edgeStylesSection = new VerticalLayout(
                new Span("Edge Styles"),
                edgeStylesList
        );

        lytEdgeStylesSupported.add(edgeStylesSection);
        lytEdgeStylesSupported.setSpacing(true);
        lytEdgeStylesSupported.setPadding(true);
        return lytEdgeStylesSupported;
    }
    
    /*End of elementes UI*/ 
}
