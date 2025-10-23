package com.neotropic.flow.component.antvx6.demo.pages.UI.components;

import com.neotropic.flow.component.antvx6.AntvX6;
import com.neotropic.flow.component.antvx6.constants.X6Constants;
import com.neotropic.flow.component.antvx6.demo.components.HeaderComponent;
import com.neotropic.flow.component.antvx6.demo.factory.X6Factory;
import com.neotropic.flow.component.antvx6.objects.Geometry;
import com.neotropic.flow.component.antvx6.objects.X6Node;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.UUID;

/**
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Route("/UI/components/examples/plugins")
public class ExamplePlugins extends VerticalLayout{
    private final X6Factory factory;
    private static final String HEADER_TEXT = "X6 Plugins";
    private static final String PLUGIN_SCROLLER = "Scroller Plugin";
    private static final String PLUGIN_EXPORT = "Export Plugin";
    private static final String PLUGIN_SNAPLINE = "Snapline Plugin";
    private static final String PLUGIN_TRANSFORM = "Transform Plugin";
    private static final String PLUGIN_SELECTION = "Selection Plugin";
    private static final String PLUGIN_MINIMAP = "Minimap Plugin";
    private static final String DESCRIPTION = "They are UI components used to add features and behaviors to the visualization of graphs.";
    private static final String DESCRIPTION_PLUGIN_SCROLLER = "Create a scroll bar on the canvas.";
    private static final String DESCRIPTION_PLUGIN_EXPORT = "allows you to export the graph as an image.";
    private static final String DESCRIPTION_PLUGIN_SNAPLINE = "Create reference lines to position nodes.";
    private static final String DESCRIPTION_PLUGIN_TRANSFORM = "Allows resizing and rotating nodes within the canvas.";
    private static final String DESCRIPTION_PLUGIN_SELECTION = "Allows the selection of one or more nodes on the canvas.";
    private static final String DESCRIPTION_PLUGIN_MINIMAP = "Creates a minimap of the current canvas";
    
    public ExamplePlugins(X6Factory factory){
        this.factory = factory;
        
        setDefaultHorizontalComponentAlignment(Alignment.START); 
        
        createHeader();  
        createExamples();
    }
    
    private void createHeader(){
        HeaderComponent header = new HeaderComponent(HEADER_TEXT, DESCRIPTION);
        add(header);
    }
    
    private void createExamples(){
        VerticalLayout lytScroller = createScrollerPlugin();
        VerticalLayout lytExport = createExportPlugin();
        VerticalLayout lytSnapline = createSnaplinePlugin();
        VerticalLayout lytTransform = createTransformPlugin();
        VerticalLayout lytSelection = createSelectionPlugin();
        VerticalLayout lytMinimap =  createMinimapPlugin();
        add(lytScroller, lytExport, lytSnapline, lytTransform, lytSelection, lytMinimap);
    }
    
    private VerticalLayout createScrollerPlugin(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        lytBasicCanvas.setWidthFull();
        lytBasicCanvas.setHeight("600px");
        AntvX6 basicCanvas = factory.getBasicCanvas(X6Constants.GRAPH_BACKGROUND_COLOR);
        basicCanvas.setSizeFull();
        Div canvasWrapper = new Div(basicCanvas);
        canvasWrapper.setSizeFull();
        
        basicCanvas.addGraphCreatedListener(evt -> {
            
            //Add the scroller plugin
            basicCanvas.addScrollerPlugin(100);
            
            X6Node node = new X6Node();
            node.setId(UUID.randomUUID().toString());
            node.setGeometry(new Geometry(100, 100, 50, 50));
            node.setShape(X6Constants.SHAPE_RECT);
            node.setLabel("drag me");
            node.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);

            //Add the nodes to the canvas
            basicCanvas.drawNode(node);
        });
        
        lytBasicCanvas.add(new H4(PLUGIN_SCROLLER), new Paragraph(DESCRIPTION_PLUGIN_SCROLLER) ,canvasWrapper);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createExportPlugin(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        lytBasicCanvas.setWidthFull();
        lytBasicCanvas.setHeight("600px");
        AntvX6 basicCanvas = factory.getBasicCanvas(X6Constants.GRAPH_BACKGROUND_COLOR);
        basicCanvas.setSizeFull();
        Div canvasWrapper = new Div(basicCanvas);
        canvasWrapper.setSizeFull();
       
        basicCanvas.addGraphCreatedListener(evt -> {
            
            //Add the export plugin
            basicCanvas.addExportPlugin();
            //If you want to set the padding around the photo
            basicCanvas.setPaddingExportGraphJPEG(100);
            
            X6Node node1 = new X6Node();
            node1.setId(UUID.randomUUID().toString());
            node1.setGeometry(new Geometry(100, 100, 50, 50));
            node1.setShape(X6Constants.SHAPE_RECT);
            node1.setLabel("Hello!");
            node1.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_DEFAULT);
            
            X6Node node2 = new X6Node();
            node2.setId(UUID.randomUUID().toString());
            node2.setGeometry(new Geometry(300, 100, 50, 50));
            node2.setShape(X6Constants.SHAPE_RECT);
            node2.setLabel("Hi!");
            node2.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_DEFAULT);

            //Add the nodes to the canvas
            basicCanvas.drawNode(node1);
            basicCanvas.drawNode(node2);
        });
        
        Button exportButton = new Button("Export as JPEG", e -> {
            //calls the method that exports
            basicCanvas.exportGraphAsJPEG("photograph");
        });

        lytBasicCanvas.add(new H4(PLUGIN_EXPORT), new Paragraph(DESCRIPTION_PLUGIN_EXPORT), canvasWrapper, exportButton);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createSnaplinePlugin(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        lytBasicCanvas.setWidthFull();
        lytBasicCanvas.setHeight("600px");
        AntvX6 basicCanvas = factory.getBasicCanvas(X6Constants.GRAPH_BACKGROUND_COLOR);
        basicCanvas.setSizeFull();
        Div canvasWrapper = new Div(basicCanvas);
        canvasWrapper.setSizeFull();
       
        basicCanvas.addGraphCreatedListener(evt -> {
            //Add the snapline plugin
            basicCanvas.addSnaplinePlugin(true);
            
            X6Node node1 = new X6Node();
            node1.setId(UUID.randomUUID().toString());
            node1.setGeometry(new Geometry(100, 100, 50, 50));
            node1.setShape(X6Constants.SHAPE_RECT);
            node1.setLabel("Move me");
            node1.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);
            
            X6Node node2 = new X6Node();
            node2.setId(UUID.randomUUID().toString());
            node2.setGeometry(new Geometry(300, 100, 50, 50));
            node2.setShape(X6Constants.SHAPE_RECT);
            node2.setLabel("I'll wait....");
            node2.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);

            //Add the nodes to the canvas
            basicCanvas.drawNode(node1);
            basicCanvas.drawNode(node2);
        });
        

        lytBasicCanvas.add(new H4(PLUGIN_SNAPLINE), new Paragraph(DESCRIPTION_PLUGIN_SNAPLINE), canvasWrapper);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createTransformPlugin() {
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        lytBasicCanvas.setWidthFull();
        lytBasicCanvas.setHeight("600px");
        AntvX6 basicCanvas = factory.getBasicCanvas(X6Constants.GRAPH_BACKGROUND_COLOR);
        basicCanvas.setSizeFull();
        Div canvasWrapper = new Div(basicCanvas);
        canvasWrapper.setSizeFull();

        Div dimensionsInfo = new Div();
        dimensionsInfo.setText("Width: N/A, Height: N/A");

        basicCanvas.addGraphCreatedListener(evt -> {
            //Add transform plugin
            basicCanvas.addTransformPlugin(true, true, true, 20, 20, true);
            // Detect if the node had changes
            basicCanvas.initEventNodeChanged();
            // Adds dimension widgets when a node is double-clicked
            basicCanvas.initEventResizeNode();
            
            X6Node node = new X6Node();
            node.setId(UUID.randomUUID().toString());
            node.setGeometry(new Geometry(100, 100, 100, 100));
            node.setShape(X6Constants.SHAPE_RECT);
            node.setLabel("Resize me");
            basicCanvas.drawNode(node);

            basicCanvas.addNodeChangedListener(listener -> {
                double newWidth = listener.getWidth();
                double newHeight = listener.getHeight();
                dimensionsInfo.setText("new Width: " + newWidth + ", new Height: " + newHeight);
                
                //Don't forget update the model
                node.getGeometry().getDimensions().setWidth(newWidth);
                node.getGeometry().getDimensions().setHeight(newHeight);
            });
        });

        lytBasicCanvas.add(new H4(PLUGIN_TRANSFORM), new Paragraph(DESCRIPTION_PLUGIN_TRANSFORM), dimensionsInfo, canvasWrapper);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createSelectionPlugin(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        lytBasicCanvas.setWidthFull();
        lytBasicCanvas.setHeight("600px");
        AntvX6 basicCanvas = factory.getBasicCanvas(X6Constants.GRAPH_BACKGROUND_COLOR);
        basicCanvas.setSizeFull();
        Div canvasWrapper = new Div(basicCanvas);
        canvasWrapper.setSizeFull();
       
        basicCanvas.addGraphCreatedListener(evt -> {
            //Add the selection plugin
            basicCanvas.addSelectionPlugin(true, true, true, true, true, true);
            //This event detects if one or multiple cells has been selected
            basicCanvas.initEventCellSelected();
            //Returns the deselected cell id
            basicCanvas.initEventCellUnselect();
            //Add event if you want to detect the changes
            basicCanvas.initEventNodeChanged();
            
            X6Node node1 = new X6Node();
            node1.setId("node 1");
            node1.setGeometry(new Geometry(100, 100, 50, 50));
            node1.setShape(X6Constants.SHAPE_RECT);
            node1.setLabel("Select me");
            node1.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);
            
            X6Node node2 = new X6Node();
            node2.setId("node 2");
            node2.setGeometry(new Geometry(300, 100, 50, 50));
            node2.setShape(X6Constants.SHAPE_RECT);
            node2.setLabel("Me too...");
            node2.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);

            //Add the nodes to the canvas
            basicCanvas.drawNode(node1);
            basicCanvas.drawNode(node2);
            
            basicCanvas.addCellSelectedListener(listener-> {
                // Here do whatever you want
                //System.out.println("celdas " + listener.getId() + " " + listener.getCellType() + " " + listener.getNumberCells());
            });
            
            basicCanvas.addCellUnselectedListener(listener-> {
                // Here do whatever you want
            });
            
            // Update the model
            basicCanvas.addNodeChangedListener(listener -> {
                String id = listener.getId();
                double newX = listener.getX();
                double newY = listener.getY();
                
                X6Node response = basicCanvas.getNodeById(id);
                if(response != null){
                    response.getGeometry().getCoordinates().setX(newX);
                    response.getGeometry().getCoordinates().setY(newY);
                }
            });
        });
        

        lytBasicCanvas.add(new H4(PLUGIN_SELECTION), new Paragraph(DESCRIPTION_PLUGIN_SELECTION), canvasWrapper);
        return lytBasicCanvas;
    }
    
    private VerticalLayout createMinimapPlugin(){
        VerticalLayout lytBasicCanvas = new VerticalLayout();
        lytBasicCanvas.setWidthFull();
        lytBasicCanvas.setHeight("600px");
        AntvX6 basicCanvas = factory.getBasicCanvas(X6Constants.GRAPH_BACKGROUND_COLOR);
        basicCanvas.setSizeFull();
        Div canvasWrapper = new Div(basicCanvas);
        canvasWrapper.setSizeFull();
       
        //Activate the div that wraps the minimap
        basicCanvas.setMinimapState(true);
        
        basicCanvas.addGraphCreatedListener(evt -> {
            //Add the scroller plugin, It's not neccesary
            basicCanvas.addScrollerPlugin(100);
            // Add the plugin
            basicCanvas.addMinimapPlugin(200, 160);
            
            X6Node node = new X6Node();
            node.setId(UUID.randomUUID().toString());
            node.setGeometry(new Geometry(100, 100, 50, 50));
            node.setShape(X6Constants.SHAPE_RECT);
            node.setLabel("I'm a X6Node");
            node.getNodeLabelStyles().setLabelPosition(X6Constants.LABEL_NODE_POSITION_BOTTOM);
           
            //Add the nodes to the canvas
            basicCanvas.drawNode(node);
            
        });
        
        lytBasicCanvas.add(new H4(PLUGIN_MINIMAP), new Paragraph(DESCRIPTION_PLUGIN_MINIMAP), canvasWrapper);
        return lytBasicCanvas;
    }
}
