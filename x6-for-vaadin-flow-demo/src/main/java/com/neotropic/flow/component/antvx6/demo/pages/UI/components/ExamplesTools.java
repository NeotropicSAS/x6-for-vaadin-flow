package com.neotropic.flow.component.antvx6.demo.pages.UI.components;

import com.neotropic.flow.component.antvx6.AntvX6;
import com.neotropic.flow.component.antvx6.constants.X6Constants;
import com.neotropic.flow.component.antvx6.demo.components.HeaderComponent;
import com.neotropic.flow.component.antvx6.demo.factory.X6Factory;
import com.neotropic.flow.component.antvx6.objects.Geometry;
import com.neotropic.flow.component.antvx6.objects.X6Edge;
import com.neotropic.flow.component.antvx6.objects.X6Node;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.UUID;

/**
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Route("/UI/components/examples/tools")
public class ExamplesTools extends VerticalLayout{
    private final X6Factory factory;
    private static final String HEADER_TEXT = "X6 Tools";
    private static final String TOOL_NODE_EDITOR = "Node editor";
    private static final String TOOL_BUTTON_REMOVE = "Node Button remove";
    private static final String TOOL_BUTTON_CUSTOM_REMOVE = "Node Custom Button remove";
    private static final String TOOL_VERTICES_SEGMENTS = "Vertices and Segments tool";
    private static final String DESCRIPTION = "They are small UI components rendered on the objects drawn on the canvas, such as nodes or edges.";
    private static final String DESCRIPTION_TOOL_NODE_EDITOR = "This tool creates a text field when double-clicking on a node, allowing us to modify the label of the node.";
    private static final String DESCRIPTION_TOOL_BUTTON_REMOVE = "This tool creates a button to delete nodes. When hovering over a node, the delete button appears.";
    private static final String DESCRIPTION_TOOL_BUTTON_CUSTOM_REMOVE = 
    "This tool creates a custom button to delete nodes. When hovering over a node, the button appears, and clicking it triggers a custom event indicating that the remove button was pressed. The deletion itself is handled manually, not automatically.";
    private static final String DESCRIPTION_TOOL_VERTICES_SEGMENTS = "This tool allows creating vertices from the UI and, through segments, positioning straight lines within the edge.";
    
    public ExamplesTools(X6Factory factory){
        this.factory = factory;
        
        setDefaultHorizontalComponentAlignment(Alignment.START); 
        
        createHeader();  
        createExamples();
    }
    
    /*Elements UI*/ 
    
    private void createHeader(){
        HeaderComponent header = new HeaderComponent(HEADER_TEXT, DESCRIPTION);
        add(header);
    }
    
    private void createExamples(){
        VerticalLayout lytNodeEditor = createNodeEditorTool();
        VerticalLayout lytButtonRemove = createNodeButtonRemoveTool();
        VerticalLayout lytButtonRemoveCustom =createNodeButtonRemoveCustomTool();
        VerticalLayout lytEdgeTools = createEdgeTools();
        add(lytNodeEditor, lytButtonRemove, lytButtonRemoveCustom,lytEdgeTools);
    }
    
    private VerticalLayout createNodeEditorTool(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        lytBasicCanvas.setWidthFull();
        lytBasicCanvas.setHeight("600px");
        AntvX6 basicCanvas = factory.getBasicCanvas(X6Constants.GRAPH_BACKGROUND_COLOR);
        basicCanvas.setSizeFull();
        Div canvasWrapper = new Div(basicCanvas);
        canvasWrapper.setSizeFull();
       
        // through an event, we create the nodes
        basicCanvas.addGraphCreatedListener(evt -> {
            
            X6Node node = new X6Node();
            node.setId(UUID.randomUUID().toString());
            node.setGeometry(new Geometry(100, 100, 100, 100));
            node.setShape(X6Constants.SHAPE_RECT);
            node.setLabel("Double click on me");
            node.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);
            /*This tool should be added when drawing the element. 
            *In this version of X6, it does not require handling through a double-click event (which was necessary in versions prior to 2.x.x.x).
            */
            node.getTools().add(X6Constants.NODE_EDITOR);
            
            //Add an event if you need to get the new text
            
            //Init the event that detect changes
            basicCanvas.initEventNodeChanged();
            //add the listener
            basicCanvas.addNodeChangedListener(listener -> {
                String newText = listener.getNewLabel();
                node.setLabel(newText);
            });
            
            //add the nodes
            basicCanvas.drawNode(node);
        });
        
        lytBasicCanvas.add(new H4(TOOL_NODE_EDITOR), new Paragraph(DESCRIPTION_TOOL_NODE_EDITOR) ,canvasWrapper);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createNodeButtonRemoveTool() {
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        lytBasicCanvas.setWidthFull();
        lytBasicCanvas.setHeight("600px");
        AntvX6 basicCanvas = factory.getBasicCanvas(X6Constants.GRAPH_BACKGROUND_COLOR);
        basicCanvas.setSizeFull();
        Div canvasWrapper = new Div(basicCanvas);
        canvasWrapper.setSizeFull();

        basicCanvas.addGraphCreatedListener(evt -> {
            //Add events to add and remove node tools.
            basicCanvas.initEventAddNodeButtonRemove();
            basicCanvas.initEventRemoveNodeButtonRemoveTool();
            
            X6Node node = new X6Node();
            node.setId(UUID.randomUUID().toString());
            node.setGeometry(new Geometry(100, 100, 100, 100));
            node.setShape(X6Constants.SHAPE_RECT);
            node.setLabel("Hover over me");
            
            basicCanvas.drawNode(node);
        });

        lytBasicCanvas.add(new H4(TOOL_BUTTON_REMOVE), new Paragraph(DESCRIPTION_TOOL_BUTTON_REMOVE), canvasWrapper);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createNodeButtonRemoveCustomTool() {
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        lytBasicCanvas.setWidthFull();
        lytBasicCanvas.setHeight("600px");
        AntvX6 basicCanvas = factory.getBasicCanvas(X6Constants.GRAPH_BACKGROUND_COLOR);
        basicCanvas.setSizeFull();
        Div canvasWrapper = new Div(basicCanvas);
        canvasWrapper.setSizeFull();

        basicCanvas.addGraphCreatedListener(evt -> {
            // Register the custom tool
            basicCanvas.registerConfirmRemoveToolNode();
            
            //Add events to add and remove node tools.
            basicCanvas.initEventAddNodeButtonRemoveCustomTool();
            basicCanvas.initEventRemoveNodeButtonRemoveCustomTool();
            
            X6Node node = new X6Node();
            node.setId(UUID.randomUUID().toString());
            node.setGeometry(new Geometry(100, 100, 100, 100));
            node.setShape(X6Constants.SHAPE_RECT);
            node.setLabel("Hover over me");
            
            basicCanvas.drawNode(node);
        });
        
        basicCanvas.addButtonRemoveCustomToolClicked(evt -> {
            String cellId = evt.getCellId();

            Dialog confirmDialog = new Dialog();
            confirmDialog.add(new Span("Are you sure you want to delete this node?"));

            Button yesButton = new Button("Yes", e -> {
                confirmDialog.close();
                basicCanvas.removeCell(cellId);
            });

            Button noButton = new Button("No", e -> confirmDialog.close());

            HorizontalLayout buttons = new HorizontalLayout(yesButton, noButton);
            confirmDialog.add(buttons);

            UI ui = UI.getCurrent();
            ui.beforeClientResponse(basicCanvas, ctx -> confirmDialog.open());
        });

        lytBasicCanvas.add(new H4(TOOL_BUTTON_CUSTOM_REMOVE), new Paragraph(DESCRIPTION_TOOL_BUTTON_CUSTOM_REMOVE), canvasWrapper);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createEdgeTools() {
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        lytBasicCanvas.setWidthFull();
        lytBasicCanvas.setHeight("600px");
        AntvX6 basicCanvas = factory.getBasicCanvas(X6Constants.GRAPH_BACKGROUND_COLOR);
        basicCanvas.setSizeFull();
        Div canvasWrapper = new Div(basicCanvas);
        canvasWrapper.setSizeFull();

        basicCanvas.addGraphCreatedListener(evt -> {
            //Add events to vertices and segments
            basicCanvas.initEventAddEdgeVerticesTool();
            basicCanvas.initEventRemoveEdgeTools();
            
            X6Node source = new X6Node();
            source.setId(UUID.randomUUID().toString());
            source.setGeometry(new Geometry(100, 100, 50, 50));
            source.setShape(X6Constants.SHAPE_RECT);
            source.setLabel("Source");
            
            X6Node target = new X6Node();
            target.setId(UUID.randomUUID().toString());
            target.setGeometry(new Geometry(300, 100, 50, 50));
            target.setShape(X6Constants.SHAPE_RECT);
            target.setLabel("Target");
            
            X6Edge edge = new X6Edge(UUID.randomUUID().toString(), source.getId(), target.getId(), "Press and hover over my line");
            
            basicCanvas.drawNode(source);
            basicCanvas.drawNode(target);
            basicCanvas.drawEdge(edge);
        });

        lytBasicCanvas.add(new H4(TOOL_VERTICES_SEGMENTS), new Paragraph(DESCRIPTION_TOOL_VERTICES_SEGMENTS), canvasWrapper);
        return lytBasicCanvas;
    }
    
    /*End of elementes UI*/ 
}
