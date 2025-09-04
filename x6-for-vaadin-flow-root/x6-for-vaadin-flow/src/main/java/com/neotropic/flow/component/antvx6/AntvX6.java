/*
 *  Copyright 2010-2024 Neotropic SAS <contact@neotropic.co>.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       https://apache.org/licenses/LICENSE-2.0.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.neotropic.flow.component.antvx6;

import com.google.gson.JsonObject;
import com.neotropic.flow.component.antvx6.objects.Geometry;
import com.neotropic.flow.component.antvx6.objects.X6Edge;
import com.neotropic.flow.component.antvx6.objects.X6Node;
import com.neotropic.flow.component.antvx6.objects.X6NodeBackground;
import com.neotropic.flow.component.antvx6.constants.X6Constants;
import com.neotropic.flow.component.antvx6.events.BackgroundChangedEvent;
import com.neotropic.flow.component.antvx6.events.BringToFrontEvent;
import com.neotropic.flow.component.antvx6.events.CellRemovedEvent;
import com.neotropic.flow.component.antvx6.events.CellSelectedEvent;
import com.neotropic.flow.component.antvx6.events.CellUnselectedEvent;
import com.neotropic.flow.component.antvx6.events.EdgeChangedEvent;
import com.neotropic.flow.component.antvx6.events.EdgeCreatedEvent;
import com.neotropic.flow.component.antvx6.events.EdgeDblClickEvent;
import com.neotropic.flow.component.antvx6.events.GraphCleanedEvent;
import com.neotropic.flow.component.antvx6.events.GraphCreatedEvent;
import com.neotropic.flow.component.antvx6.events.GraphRefreshedEvent;
import com.neotropic.flow.component.antvx6.events.NodeBackgroundResizedEvent;
import com.neotropic.flow.component.antvx6.events.NodeChangedEvent;
import com.neotropic.flow.component.antvx6.events.NodeMovedEvent;
import com.neotropic.flow.component.antvx6.events.SendToBackEvent;
import com.neotropic.flow.component.antvx6.jsonGenerator.JsonGenerator;
import com.neotropic.flow.component.antvx6.objects.X6NodeText;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.Registration;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Tag("x-6")
@JsModule("./src/x-6.ts")
@NpmPackage(value = "@antv/x6", version = "2.18.1")
@NpmPackage(value = "@antv/x6-plugin-snapline", version = "2.1.7")
@NpmPackage(value = "@antv/x6-plugin-transform", version = "2.1.8")
@NpmPackage(value = "@antv/x6-plugin-export", version = "2.1.6")
@NpmPackage(value = "@antv/x6-plugin-selection", version = "2.2.2")
@NpmPackage(value = "@antv/x6-plugin-scroller", version = "2.0.10")
@NpmPackage(value = "@antv/x6-plugin-minimap", version = "2.0.7")
@Getter@Setter
public class AntvX6 extends Div {
    /*
    * Basic graph configuration properties
    */
    private static final String PROPERTY_GRAPH_TYPE = "graph_type";
    private static final String PROPERTY_MINIMAP_DIV = "minimap_div";
    private static final String PROPERTY_CONTEXT_MENU_DIV = "context_menu_div";
    private static final String PROPERTY_GRAPH_WIDTH = "graph_width";
    private static final String PROPERTY_GRAPH_HEIGHT = "graph_height";
    private static final String PROPERTY_GRAPH_BACKGROUND_COLOR = "graph_background_color";
    private static final String PROPERTY_GRAPH_GRID = "graph_grid";
    private static final String PROPERTY_GRAPH_PANNING = "graph_panning";
    private static final String PROPERTY_GRAPH_MOUSE_WHEEL = "graph_mouse_wheel";
    private static final String PROPERTY_NODES_LABEL_BGCOLOR = "nodes_label_bgColor";
    private static final String PROPERTY_PADDING_EXPORT_GRAPH_JPEG = "padding_export_graph_JPEG";
    private static final String PROPERTY_GRAPH_ZOOM = "graph_zoom";
    private static final String PROPERTY_GRAPH_NODE_BACKGROUND_ID = "graph_node_background_id";

    /*
    * Background of the x6 canvas.
    */  
    private X6NodeBackground nodeBackground;
    /*
    * List of nodes present in the graph.
    */
    private List<X6Node> nodes;
    /*
    * List of text nodes present in the graph.
    */
    private List<X6NodeText> textNodes;
    /*
    * List of edges present in the graph.
    */
    private List<X6Edge> edges;
   
    public AntvX6() {
        this.nodeBackground = new X6NodeBackground();
        this.nodes = new ArrayList();
        this.textNodes = new ArrayList();
        this.edges = new ArrayList();
    }      
    
    /*
    * Methods to set property X6 web component
    */
    
    /**
    * Sets the type of the graph.
    * 
    * @param graphType the type of the graph to set. Use 0 for a basic graph and 1 for a graph with interactions.
    */
    public void setGraphType(int graphType){
        getElement().setProperty(PROPERTY_GRAPH_TYPE, graphType);
    }
    
    /**
    * Enables or disables the minimap state.
    * 
    * @param state the state to set for the minimap. Pass true to enable the minimap div, or false to disable it.
    */
    public void setMinimapState(boolean state){
        getElement().setProperty(PROPERTY_MINIMAP_DIV , state);
    }

    /**
     * Enables or disables the context menu state.
     * 
     * @param state the state to set for the context menu.Pass true to enable the context menu div, or false to disable it.
     */
    public void setContextMenuState(boolean state){
        getElement().setProperty(PROPERTY_CONTEXT_MENU_DIV, state);
    }

    /**
    * Set the ID of the node background.
     * @param idBackground id of the background node
    */
    public void setNodeBackgroundId(String idBackground){
        getElement().setProperty(PROPERTY_GRAPH_NODE_BACKGROUND_ID, idBackground);
    }
    
    /**
    * Sets the width of the graph.
    *
    * @param width the width to set for the graph in pixels.
    */
    public void setGraphWidth(int width){
        getElement().setProperty(PROPERTY_GRAPH_WIDTH, width);
    }
    
    /**
    * Sets the height of the graph.
    *
    * @param height the height to set for the graph in pixels.
    */
     public void setGraphHeight(int height){
        getElement().setProperty(PROPERTY_GRAPH_HEIGHT, height);
    }
     
    /**
    * Sets the background color of the graph.
    *
    * @param color the background color.
    */
    public void setBackgroundColor(String color){
        getElement().setProperty(PROPERTY_GRAPH_BACKGROUND_COLOR, color);
    }
    
    /**
    * Enables or disables the grid on the graph.
    *
    * @param grid true to enable the grid, false to disable it.
    */
    public void setGrid(boolean grid){
        getElement().setProperty(PROPERTY_GRAPH_GRID, grid);
    }
    
    /**
    * Enables or disables panning functionality for the graph.
    *
    * @param panning true to enable panning, false to disable it.
    */
    public void setPanning(boolean panning){
        getElement().setProperty(PROPERTY_GRAPH_PANNING, panning);
    }
    
    /**
    * Enables or disables mouse wheel zooming for the graph.
    *
    * @param mouseWheel true to enable mouse wheel zooming, false to disable it.
    */
    public void setMouseWheel(boolean mouseWheel){
        getElement().setProperty(PROPERTY_GRAPH_MOUSE_WHEEL, mouseWheel);
    }
    
    /**
    * Sets the background color for node labels.
    *
    * @param color the background color.
    */
    public void setNodesLabelBgColor(String color){
        getElement().setProperty(PROPERTY_NODES_LABEL_BGCOLOR, color);
    }
    
     /**
    * Sets the padding for exporting the graph as a JPEG image.
    *
    * @param padding the padding value in pixels.
    */
    public void setPaddingExportGraphJPEG(int padding){
        getElement().setProperty(PROPERTY_PADDING_EXPORT_GRAPH_JPEG, padding);
    }
    
    /**
    * Sets the zoom level for the graph.
    *
    * @param zoom the zoom level as a double value.
    */
    public void setGraphZoom(double zoom){
        getElement().setProperty(PROPERTY_GRAPH_ZOOM, zoom);
    }
    
    /*
    * End of methods to set property X6 web component
    */
    
    /*
    * Canvas manipulation methods
    */
    
    /**
    * Clears all nodes and edges from the graph.
    * 
    * This method removes all nodes and edges from the local lists,
    * effectively resetting the graph's state.
    */
    public void cleanGraph(){
        nodes.clear();
        edges.clear();
        textNodes.clear();
        edges.clear();
        getElement().callJsFunction("cleanGraph");
    }
    
    public void removeCell(String id){
        removeX6Cell(id);
        getElement().callJsFunction("removeCell", id);
    }
    
    public boolean removeX6Cell(String id) {
        return removeX6Node(id) || removeX6NodeText(id) || removeX6Edge(id);
    }

    public boolean removeX6Node(String id) {
        return nodes.removeIf(node -> node.getId().equals(id));
    }

    public boolean removeX6NodeText(String id) {
        return textNodes.removeIf(text -> text.getId().equals(id));
    }

    public boolean removeX6Edge(String id) {
        return edges.removeIf(edge -> edge.getId().equals(id));
    }


    /**
    * Refreshes the graph.
    * 
    * Creates a backup of the current graph, clears it, and then 
    * re-populates it using the backup data.
    */
    public void refreshGraph(){
        getElement().callJsFunction("refreshGraph");
    }
    
    /*
    * End of canvas manipulation methods
    */
    
    /*
    * Methods to add X6 plugins
    */
    
    /**
    * Adds a scroller plugin
    *
    * @param enabled              Whether the scroller is enabled.
    * @param pannable             Whether the scroller is pannable.
    * @param pageVisible          Whether the page is visible.
    * @param pageBreak            Whether the page breaks are enabled.
    * @param width                The width of the scroller.
    * @param height               The height of the scroller.
    * @param pageWidth            The width of the page.
    * @param pageHeight           The height of the page.
    * @param padding              The padding around the scroller.
    * @param autoResize           Whether the scroller should auto-resize.
    * @param scrollerPositionLeft The left position of the scroller.
    * @param scrollerPositionTop  The top position of the scroller.
    */
    public void addScrollerPlugin(boolean enabled, boolean pannable, boolean pageVisible, boolean pageBreak,
        double width, double height, double pageWidth, double pageHeight, double padding, boolean autoResize,
        double scrollerPositionLeft, int scrollerPositionTop)
    {
        getElement().callJsFunction(
            "addScrollerPlugin",enabled, pannable, pageVisible, pageBreak, 
                                width, height, pageWidth, pageHeight, padding, autoResize, 
                                scrollerPositionLeft, scrollerPositionTop
        );
    }
    
    /**
    * Adds an export plugin to the element, enabling the ability to export the graph as an image.
    */
    public void addExportPlugin(){
        getElement().callJsFunction("addExportPlugin");
    }

    /**
     * Adds a snapline plugin to nodes, enabling or disabling snapline functionality for better alignment.
     *
     * @param enabled Whether the snapline functionality is enabled or disabled.
     */
    public void addSnaplinePlugin(boolean enabled){
        getElement().callJsFunction("addSnaplinePlugin", enabled);
    }
    
    /**
    * Adds a transform plugin, enabling transformation features like rotating and resizing.
    *
    * @param rotating                  Whether rotating is enabled.
    * @param resizingEnabled           Whether resizing is enabled.
    * @param resizingOrthogonal        Whether to display the intermediate adjustment points
    * @param resizingMinWidth          The minimum width allowed when resizing.
    * @param resizingMinHeight         The minimum height allowed when resizing.
    * @param resizingPreserveAspectRatio Whether the aspect ratio is preserved during resizing.
    */
    public void addTransformPlugin(boolean rotating, boolean resizingEnabled, boolean resizingOrthogonal, 
        int resizingMinWidth, int resizingMinHeight, boolean resizingPreserveAspectRatio)
    {
        getElement().callJsFunction(
            "addTransformPlugin", rotating, resizingEnabled, resizingOrthogonal, 
            resizingMinWidth, resizingMinHeight, resizingPreserveAspectRatio
        );
    }
    
    /**
    * Adds a selection plugin to the element, enabling various selection features like multiple selection, rubberband, and movement.
    *
    * @param enabled                Whether selection is enabled.
    * @param multiple               Whether multiple selection is allowed.
    * @param rubberband             Whether to enable the box selection node function.
    * @param movable                Whether selected nodes can be moved.
    * @param showNodeSelectionBox   Whether the node selection box is shown.
    */
    public void addSelectionPlugin(boolean enabled, boolean multiple,boolean rubberband ,boolean movable, boolean showNodeSelectionBox) {
        getElement().callJsFunction(
            "addSelectionPlugin", enabled, multiple, rubberband ,movable, showNodeSelectionBox
        );
    }
    
    /**
     * Adds a minimap plugin to the element, providing a smaller, overview version of the graph.
     *
     * @param width  The width of the minimap.
     * @param height The height of the minimap.
     */
    public void addMinimapPlugin(int width, int height){
        getElement().callJsFunction("addMinimapPlugin", width, height);
    }

    /*
    * End of methods to add X6 plugins
    */
    
    /*
    * Methods to manipulate nodes
    */
    
    /**
    * Draws the node background for a graph.
    * 
    * This method constructs a background object in the canvas
    * 
    * @param background the X6NodeBackground object
    */
    public void drawNodeBackground(X6NodeBackground background) {
        JsonObject backgroundData = JsonGenerator.generateJsonBackground(background);
        getElement().callJsFunction("drawBackground", backgroundData.toString());
        nodeBackground = background;
    }
 
    /**
    * Removes the node background of the the graph.
    */
    public void removeNodeBackground(){
        getElement().callJsFunction("removeBackground");
        nodeBackground.setId("");
        nodeBackground.setGeometry(new Geometry(0, 0 , 0, 0));
        nodeBackground.setImgUrl("");
        nodeBackground.setShape(X6Constants.SHAPE_IMAGE);
    }
    
    /**
    * Draws a visual representation of a node in the graph.
    * 
    * @param node the X6Node object to be draw.
    */
    public void drawNode(X6Node node) {
        JsonObject nodeData = JsonGenerator.generateJsonNode(node);
        getElement().callJsFunction("drawNode", nodeData.toString());
        nodes.add(node);
    }
    
    public void drawNodeCenter(X6Node node) {
        JsonObject nodeData = JsonGenerator.generateJsonNode(node);
        getElement().callJsFunction("drawNode", nodeData.toString());
    }

    /**
    * Draws a visual representation of a text for a node in the graph.
    * 
    * @param nodeText the X6NodeText object to be draw
    */
    public void drawText(X6NodeText nodeText) {
        JsonObject textData = JsonGenerator.generateJsonNodeText(nodeText);
        getElement().callJsFunction("drawText", textData.toString());
        textNodes.add(nodeText);
    }

    /**
    * Sets the style for a specific node.
    * 
    * @param id the ID of the node to which the style should be applied.
    * @param style the name of the style property to modify.
    * @param value the value to set for the specified style property.
    */
    public void setNodeStyle(String id, String style, String value){
        getElement().callJsFunction("setNodeStyle", id, style, value);
    }
    
    /**
    * Establishes a parent-child relationship between two nodes in the graph.
    *
    * @param idParent the id of the parent node
    * @param idChild the id of the child node
    */
    public void setParent(String idParent, String idChild){
        getElement().callJsFunction("setParent", idParent, idChild);
    }
    
    /**
    * Selects a specified node in the graph. (Make sure to add the Selection plugin first)
    *
    * @param id id of the X6Node to be select.
    */
    public void selectNode(String id) {
        getElement().callJsFunction("selectNode", id);
    }
    
    /**
    * Unselect a specified cell in the graph. (Make sure to add the Selection plugin first)
    *
    * @param id id of the X6Cell to be unselect.
    */
    public void unselectCell(String id){
        getElement().callJsFunction("unselectCell", id);
    }
    
    /*
    * End of methods to manipulate nodes
    */
    
    /*
    * Methods to manipulate edges
    */
    
    /**
    * Draws a visual representation of an edge in the graph with a single label.
    * 
    * @param edge the X6Edge object to be draw.
    */
    public void drawEdge(X6Edge edge) {
        JsonObject edgeData = JsonGenerator.generateJsonEdge(edge);
        getElement().callJsFunction("drawEdge", edgeData.toString());
        edges.add(edge);
    }

    /**
    * Sets the style for an edge in the graph.
    *
    * @param id The unique identifier of the edge whose style needs to be modified.
    * @param style The style property to be changed.
    * @param value The value to be applied to the style property.
    */
    public void setEdgeStyle(String id, String style, String value){
        getElement().callJsFunction("setEdgeStyle", id, style, value);
    }
    
    /**
    * Sets the style of an edge label in the graph.
    *
    * @param id The unique identifier of the edge whose style needs to be modified.
    * @param style The style property to be changed.
    * @param value The value to be applied to the style property.
    * @param labelPos The position of the label that you want to modify
    */
    public void setEdgeLabelStyle(String id, String style, String value, int labelPos){
        getElement().callJsFunction("setEdgeLabelStyle", id, style, value, labelPos);
    }
    
    /*
    * End of methods to manipulate edges
    */

    /*
    * These methods will be deleted very soon and implemented in Java, because they cover business rules that are not necessary or of interest. 
    */

    public void adjustNodeWidth(String id, int reserveSpace, int childSpacing, int heightIncrease){
        getElement().callJsFunction("adjustNodeWidth", id, reserveSpace, childSpacing, heightIncrease);
    }
    
    public void executeTree(String containerId, int spacing){
        getElement().callJsFunction("executeTree", containerId, spacing);
    }
    
    public void orderChildrenByName(String idContainer){
        getElement().callJsFunction("orderChildrenByName", idContainer);
    }
    
    public void adjustNodeHeight(String id, int childSpacing){
        getElement().callJsFunction("adjustNodeHeight", id, childSpacing);
    }
    
    public void centerChildrenHorizontally(String id, int startX, int childSpacing){
        getElement().callJsFunction("centerChildrenHorizontally", id, startX, childSpacing);
    }
    
    public void centerChildrenVertically(String id, int childSpacing){
        getElement().callJsFunction("centerChildrenVertically", id, childSpacing);
    }
    
    public void establishHierarchyThroughEdges(){
        getElement().callJsFunction("establishHierarchyThroughEdges");
    }
    
    public void setPositionAbsoluteParent(List<String> parentsId) {
        StringBuilder jsonArray = new StringBuilder("[");
        for (int i = 0; i < parentsId.size(); i++) {
            jsonArray.append("\"").append(parentsId.get(i)).append("\"");
            if (i < parentsId.size() - 1) {
                jsonArray.append(","); 
            }
        }
        jsonArray.append("]");
        getElement().callJsFunction("setPositionAbsoluteParent", jsonArray.toString());
    }
    
    public void showNode(String id){
        getElement().callJsFunction("showNode", id);
    }
    
    public void hideNode(String id){
        getElement().callJsFunction("hideNode", id);
    }
    
    /*
    * End of  methods that will be deleted very soon and implemented in Java. 
    */
    
    /*
    * Events of X6
    * These methods must be initialized if you need the listeners for these events to work.
    */
    
    public void initEventCellSelected() {
        getElement().callJsFunction("eventCellSelected");
    }

    public void initEventNodesConnected() {
        getElement().callJsFunction("eventNodesConnected");
    }

    public void initEventGetNodeNewPosition() {
        getElement().callJsFunction("eventGetNodeNewPosition");
    }

    public void initEventGetNodeBackgroundNewDimensions() {
        getElement().callJsFunction("eventGetNodeBackgroundNewDimensions");
    }

    public void initEventResizeNodeBackgroundDblClick() {
        getElement().callJsFunction("eventResizeNodeBackgroundDblClick");
    }
    
    public void initEventNodeChanged(){
        getElement().callJsFunction("eventNodeChanged");
    }
    
    public void initEventBackgroundChanged(){
        getElement().callJsFunction("eventBackgroundChanged");
    }
    
    public void initEventConfigureZIndexControls() {
        getElement().callJsFunction("configureZIndexControls");
    }

    public void initEventContextMenu() {
        getElement().callJsFunction("eventContextMenu");
    }

    public void initEventResizeNode() {
        getElement().callJsFunction("eventResizeNode");
    }

    public void initEventCellUnselect() {
        getElement().callJsFunction("eventCellUnselect");
    }

    public void initEventAddNodeButtonRemove() {
        getElement().callJsFunction("eventAddNodeButtonRemoveTool");
    }

    public void initEventRemoveNodeButtonRemoveTool() {
        getElement().callJsFunction("eventRemoveNodeButtonRemoveTool");
    }

    public void initEventAddEdgeVerticesTool() {
        getElement().callJsFunction("eventAddEdgeVerticesTool");
    }
    
    public void initEventAddEdgeButtonRemoveTool(){
        getElement().callJsFunction("eventAddEdgeButtonRemoveTool");
    }

    public void initEventRemoveEdgeTools() {
        getElement().callJsFunction("eventRemoveEdgeTools");
    }
    
    public void activateContextMenu(){
        getElement().callJsFunction("activateContextMenu");
    }
    
    public void initEventEdgeDblclick(){
        getElement().callJsFunction("eventDblClickEdge");
    }
    
    public void initEventEdgeChanged(){
        getElement().callJsFunction("eventEdgeChanged");
    }
    
    public void initEventCellRemoved(){
        getElement().callJsFunction("eventCellRemoved");
    }

    /*
    * End of X6 events
    */
    
    
    /*
    * Other methods 
    */
    
    /*
    * Center a point in the middle of the canvas, it is used to organize the canvas
    */
    public void centerGraph(String idNode){
        getElement().callJsFunction("centerGraph", idNode);
    }
    
    /**
    * Exports the current graph as a JPEG image.
    *
    * The function handles the conversion of the graph into a JPEG format and download the image.
    *
    * @param filename the desired name for the exported JPEG file.
    */
    public void exportGraphAsJPEG(String filename){
        getElement().callJsFunction("exportGraphToJPEG", filename);
    }
    
    /*
    * End of other methods
    */
    
    
    /*
    * Listeners
    */
    
    public Registration addNodesConnectedListener(ComponentEventListener<EdgeCreatedEvent> listener) {
        return addListener(EdgeCreatedEvent.class, listener);
    }
    
    public Registration addGraphCreatedListener(ComponentEventListener<GraphCreatedEvent> listener) {
        return addListener(GraphCreatedEvent.class, listener);
    }
    
    public Registration addGraphCleanedListener(ComponentEventListener<GraphCleanedEvent> listener) {
        return addListener(GraphCleanedEvent.class, listener);
    }
    
    public Registration addGraphRefreshedListener(ComponentEventListener<GraphRefreshedEvent> listener) {
        return addListener(GraphRefreshedEvent.class, listener);
    }
    
    public Registration addBringToFrontCellListener(ComponentEventListener<BringToFrontEvent> listener){
        return addListener(BringToFrontEvent.class, listener);
    }
    
    public Registration addSendToBackCellListener(ComponentEventListener<SendToBackEvent> listener){
        return addListener(SendToBackEvent.class, listener);
    }
    
    public Registration addNodeChangedListener(ComponentEventListener<NodeChangedEvent> listener){
        return addListener(NodeChangedEvent.class, listener);
    }
    
    public Registration addBackgroundChangedListener(ComponentEventListener<BackgroundChangedEvent> listener){
        return addListener(BackgroundChangedEvent.class, listener);
    }
   
    public Registration addNodeMovedListener(ComponentEventListener<NodeMovedEvent> listener) {
        return addListener(NodeMovedEvent.class, listener);
    }
    
    public Registration addNodeBackgroundResizedListener(ComponentEventListener<NodeBackgroundResizedEvent> listener) {
        return addListener(NodeBackgroundResizedEvent.class, listener);
    }

    public Registration addCellSelectedListener(ComponentEventListener<CellSelectedEvent> listener) {
        return addListener(CellSelectedEvent.class, listener);
    }
    
    public Registration addCellUnselectedListener(ComponentEventListener<CellUnselectedEvent> listener) {
        return addListener(CellUnselectedEvent.class, listener);
    }
    
    public Registration addEdgeDblClickListener(ComponentEventListener<EdgeDblClickEvent> listener) {
        return addListener(EdgeDblClickEvent.class, listener);
    }
    
    public Registration addEdgeChangedListener(ComponentEventListener<EdgeChangedEvent> listener) {
        return addListener(EdgeChangedEvent.class, listener);
    }
    
    public Registration addCellRemovedListener(ComponentEventListener<CellRemovedEvent> listener) {
        return addListener(CellRemovedEvent.class, listener);
    }

    /*
    * End of Listeners
    */
    
    public X6Node getNodeById(String id) {
        return nodes.stream()
                    .filter(node -> node.getId().equals(id))
                    .findFirst()
                    .orElse(null);
    }

    
    public X6Edge getEdgeById(String id) {
        return edges.stream()
                    .filter(edge -> edge.getId().equals(id))
                    .findFirst()
                    .orElse(null);
    }
 
}
