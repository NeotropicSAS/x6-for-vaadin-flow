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
import com.neotropic.flow.component.antvx6.events.ButtonRemoveCustomToolClicked;
import com.neotropic.flow.component.antvx6.events.CellRemovedEvent;
import com.neotropic.flow.component.antvx6.events.CellSelectedEvent;
import com.neotropic.flow.component.antvx6.events.CellUnselectedEvent;
import com.neotropic.flow.component.antvx6.events.EdgeChangedEvent;
import com.neotropic.flow.component.antvx6.events.EdgeCreatedEvent;
import com.neotropic.flow.component.antvx6.events.EdgeDblClickEvent;
import com.neotropic.flow.component.antvx6.events.GraphCleanedEvent;
import com.neotropic.flow.component.antvx6.events.GraphCreatedEvent;
import com.neotropic.flow.component.antvx6.events.GraphLoadedEvent;
import com.neotropic.flow.component.antvx6.events.GraphLoadingEvent;
import com.neotropic.flow.component.antvx6.events.GraphRefreshedEvent;
import com.neotropic.flow.component.antvx6.events.NodeBackgroundResizedEvent;
import com.neotropic.flow.component.antvx6.events.NodeChangedEvent;
import com.neotropic.flow.component.antvx6.events.NodeMovedEvent;
import com.neotropic.flow.component.antvx6.events.SendToBackEvent;
import com.neotropic.flow.component.antvx6.jsonGenerator.JsonGenerator;
import com.neotropic.flow.component.antvx6.objects.X6NodeText;
import com.neotropic.flow.component.antvx6.utilities.X6NodeTextUtilities;
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
    private static final String PROPERTY_GRAPH_BACKGROUND_COLOR = "graph_background_color";
    private static final String PROPERTY_GRAPH_GRID = "graph_grid";
    private static final String PROPERTY_GRAPH_PANNING = "graph_panning";
    private static final String PROPERTY_GRAPH_MOUSE_WHEEL = "graph_mouse_wheel";
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
    
    // <editor-fold desc="Set properties in AntV X6 web component">
    
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
    
    // </editor-fold>
    
    // <editor-fold desc="Custom Tools">
    
    /**
    * Registers the custom remove button tool for nodes.
    */
    public void registerConfirmRemoveToolNode(){
        getElement().callJsFunction("registerConfirmRemoveToolNode");
    }
    
    /**
    * Registers the custom remove button tool for edges.
    */
    public void registerConfirmRemoveToolEdge(){
        getElement().callJsFunction("registerConfirmRemoveToolEdge");
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Graph Appearance">
    
    /**
    * Resizes the canvas to the specified width and height.
    *
    * @param width  the new width of the canvas
    * @param height the new height of the canvas
    */
    public void resizeCanvas(int width, int height){
        getElement().callJsFunction("resizeCanvas", width, height);
    }
    
    /**
     * Shows the grid on the graph with the given configuration.
     *
     * @param size The grid size (distance between lines or dots).
     * @param mainColor The main grid color.
     * @param mainThickness The thickness of the main grid lines.
     * @param subColor The secondary grid color.
     * @param subThickness The thickness of the secondary grid lines.
     * @param factor The spacing factor between main and secondary lines.
     */
    public void showGrid(int size, String mainColor, double mainThickness,
                         String subColor, double subThickness, int factor) {
        getElement().callJsFunction("showGrid", size, mainColor, mainThickness, subColor, subThickness, factor);
    }

    /**
     * Hides the grid on the graph.
     */
    public void hideGrid() {
        getElement().callJsFunction("hideGrid");
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Graph View Management">
    
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
    
    /**
    * Clears all nodes and edges from the web component (not from the local lists).
    */
    private void cleanElements(){
        getElement().callJsFunction("cleanGraph");
    }
    
    /**
    * Centers the graph view on the specified node.
    *
    * @param idNode The ID of the node to center the graph on.
    */
    public void centerGraph(String idNode){
        getElement().callJsFunction("centerGraph", idNode);
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
    
    /**
    * Refreshes the entire canvas by clearing existing elements and redrawing all components,
    * including the background node, nodes, text nodes, and edges.
    * 
    * This method ensures the graph is updated and visually consistent with the current data.
    */
    public void refreshCanvas() {
        cleanElements();
        if (nodeBackground != null && nodeBackground.getId() != null && !nodeBackground.getId().isBlank()) 
            drawNodeBackground(nodeBackground);

        for (X6Node node : nodes)
            drawNodeCenter(node); 

        for (X6NodeText textNode : textNodes){
            X6Node parent = getNodeById(textNode.getParentId());
            Geometry textGeometry =  new Geometry();
            X6NodeTextUtilities.calculateLabelDimensions(textGeometry, textNode.getLabel(), 12);
            X6NodeTextUtilities.calculateLabelPosition(parent.getGeometry(), textGeometry, X6Constants.BOTTOM, 10);
            textNode.setGeometry(textGeometry);
            drawText(textNode);
        }

        for (X6Edge edge : edges) 
            drawEdge(edge);
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Remove Nodes/Edges">
    
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
    * Removes a cell (node, text, or edge) from both the internal data and the visual canvas.
    *
    * @param id the unique identifier of the cell to remove
    */
    public void removeCell(String id){
        removeX6Cell(id);
        getElement().callJsFunction("removeCell", id);
    }
    
    /**
    * Removes a cell from internal collections.
    *
    * @param id the unique identifier of the cell
    * @return true if the cell was found and removed; false otherwise
    */
    public boolean removeX6Cell(String id) {
        return removeX6Node(id) || removeX6NodeText(id) || removeX6Edge(id);
    }

    /**
    * Removes a node from the node list.
    *
    * @param id the unique identifier of the node
    * @return true if the node was removed; false otherwise
    */
    public boolean removeX6Node(String id) {
        return nodes.removeIf(node -> node.getId().equals(id));
    }

    /**
    * Removes a text node from the text node list.
    *
    * @param id the unique identifier of the text node
    * @return true if the text node was removed; false otherwise
    */
    public boolean removeX6NodeText(String id) {
        return textNodes.removeIf(text -> text.getId().equals(id));
    }

    /**
    * Removes an edge from the edge list.
    *
    * @param id the unique identifier of the edge
    * @return true if the edge was removed; false otherwise
    */
    public boolean removeX6Edge(String id) {
        return edges.removeIf(edge -> edge.getId().equals(id));
    }
    
    // </editor-fold>
    
    // <editor-fold desc="AntV X6 Plugins">
    
    /**
    * Adds a scroller plugin to the graph, allowing users to pan and scroll within the canvas.
    *
    * @param padding the padding (in pixels) applied around the graph content when scrolling
    */
    public void addScrollerPlugin(int padding) {
        getElement().callJsFunction("addScrollerPlugin", padding);
    }
    
    /**
     * Adds an export plugin to the graph, enabling users to export the current canvas view
     * as an image.
     */
    public void addExportPlugin(){
        getElement().callJsFunction("addExportPlugin");
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

    /**
    * Adds a snapline plugin to the graph, providing visual alignment guides when moving or placing nodes.
    *
    * @param enabled true to enable the snapline functionality; false to disable it
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
     * @param showEdgeSelectionBox Whether the edge selection box is shown.
    */
    public void addSelectionPlugin(boolean enabled, boolean multiple,boolean rubberband ,boolean movable, boolean showNodeSelectionBox,
            boolean showEdgeSelectionBox) {
        getElement().callJsFunction(
            "addSelectionPlugin", enabled, multiple, rubberband ,movable, showNodeSelectionBox, showEdgeSelectionBox
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
    
    /**
    * Adds a dynamic minimap plugin to the graph, providing a small overview of the entire canvas.
    *
    * @param widthMinimap  the width of the minimap in pixels
    * @param heightMinimap the height of the minimap in pixels
    */
    public void addMinimapPluginDinamic(int widthMinimap, int heightMinimap){
        getElement().callJsFunction("addMinimapPluginDinamic", widthMinimap, heightMinimap);
    }
    
    /**
    * Removes the dynamic minimap plugin from the graph, disabling the minimap overview functionality.
    */
    public void removeMinimapPluginDimanic(){
        getElement().callJsFunction("removeMinimapDinamic");
    }   

    // </editor-fold>
    
    // <editor-fold desc="Draw Elements">
    
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
        if(nodeBackground == null && nodeBackground.getId().isBlank())
            nodeBackground = background;
    }
    
    /**
    * Draws a visual representation of a node in the graph.
    * 
    * @param node the X6Node object to be draw.
    */
    public void drawNode(X6Node node) {
        JsonObject nodeData = JsonGenerator.generateJsonNode(node);
        getElement().callJsFunction("drawNode", nodeData.toString());
        if(getNodeById(node.getId()) == null)
            nodes.add(node);
    }
    
    /**
    * Draws a node as a center.
    *
    * @param node the instance to be drawn on the graph
    */
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
        if(this.getNodeTextById(nodeText.getId()) == null)
            textNodes.add(nodeText);
    }
    
    /**
    * Draws a visual representation of an edge in the graph with a single label.
    * 
    * @param edge the X6Edge object to be draw.
    */
    public void drawEdge(X6Edge edge) {
        JsonObject edgeData = JsonGenerator.generateJsonEdge(edge);
        getElement().callJsFunction("drawEdge", edgeData.toString());
        if(getEdgeById(edge.getId()) == null)
            edges.add(edge);
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
    
    // </editor-fold>
    
    // <editor-fold desc="Object Styles">
    
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
    
    /**
    * Changes the label text of a specific node in the graph.
    *
    * @param nodeId the ID of the node whose label will be updated
    * @param newText the new text to set as the node's label
    */
    public void changeNodeLabel(String nodeId, String newText){
        getElement().callJsFunction("changeNodeLabel", nodeId, newText);
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Node Selection Functionalities">
    
    /**
    * Selects a specified cell in the graph. (Make sure to add the Selection plugin first)
    *
    * @param id id of the cell to be select.
    */
    public void selectCell(String id) {
        getElement().callJsFunction("selectCell", id);
    }
    
    /**
    * Unselect a specified cell in the graph. (Make sure to add the Selection plugin first)
    *
    * @param id id of the X6Cell to be unselect.
    */
    public void unselectCell(String id){
        getElement().callJsFunction("unselectCell", id);
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Node Visibility Management">
    
    /**
    * Displays a specific node in the graph by its ID.
    *
    * @param id the ID of the node to be shown
    */
    public void showNode(String id){
        getElement().callJsFunction("showNode", id);
    }
    
    /**
    * Hides a specific node in the graph by its ID.
    *
    * @param id the ID of the node to be hidden
    */
    public void hideNode(String id){
        getElement().callJsFunction("hideNode", id);
    }
    
    // </editor-fold>
        
    // <editor-fold desc="Init Events">
    
    /**
    * Initializes the event for when a cell is selected.
    */
    public void initEventCellSelected() {
        getElement().callJsFunction("eventCellSelected");
    }

    /**
    * Initializes the event for when nodes are connected.
    */
    public void initEventNodesConnected() {
        getElement().callJsFunction("eventNodesConnected");
    }

    /**
    * Initializes the event for getting the new position of a node.
    */
    public void initEventGetNodeNewPosition() {
        getElement().callJsFunction("eventGetNodeNewPosition");
    }

    /**
    * Initializes the event for getting the new dimensions of the background node.
    */
    public void initEventGetNodeBackgroundNewDimensions() {
        getElement().callJsFunction("eventGetNodeBackgroundNewDimensions");
    }

    /**
    * Initializes the event for resizing the background node on double-click.
    */
    public void initEventResizeNodeBackgroundDblClick() {
        getElement().callJsFunction("eventResizeNodeBackgroundDblClick");
    }
    
    /**
    * Initializes the event for when a node is changed.
    */
    public void initEventNodeChanged(){
        getElement().callJsFunction("eventNodeChanged");
    }
    
    /**
    * Initializes the event for when the background node changes.
    */
    public void initEventBackgroundChanged(){
        getElement().callJsFunction("eventBackgroundChanged");
    }
    
    /**
    * Initializes the event for configuring Z-index controls.
    */
    public void initEventConfigureZIndexControls() {
        getElement().callJsFunction("configureZIndexControls");
    }

    /**
    * Initializes the event for showing the context menu.
    */
    public void initEventContextMenu() {
        getElement().callJsFunction("eventContextMenu");
    }

    /**
    * Initializes the event for resizing a node.
    */
    public void initEventResizeNode() {
        getElement().callJsFunction("eventResizeNode");
    }

    /**
    * Initializes the event for when a cell is unselected.
    */
    public void initEventCellUnselect() {
        getElement().callJsFunction("eventCellUnselect");
    }

    /**
    * Initializes the event to add the remove button tool to nodes.
    */
    public void initEventAddNodeButtonRemove() {
        getElement().callJsFunction("eventAddNodeButtonRemoveTool");
    }

    /**
    * Initializes the event to remove the remove button tool from nodes.
    */
    public void initEventRemoveNodeButtonRemoveTool() {
        getElement().callJsFunction("eventRemoveNodeButtonRemoveTool");
    }

    /**
    * Initializes the event to add the vertices tool to edges.
    */
    public void initEventAddEdgeVerticesTool() {
        getElement().callJsFunction("eventAddEdgeVerticesTool");
    }
    
    /**
    * Initializes the event to add the remove button tool to edges.
    */
    public void initEventAddEdgeButtonRemoveTool(){
        getElement().callJsFunction("eventAddEdgeButtonRemoveTool");
    }

    /**
    * Initializes the event to remove tools from edges.
    */
    public void initEventRemoveEdgeTools() {
        getElement().callJsFunction("eventRemoveEdgeTools");
    }
    
    /**
    * Activates the context menu interaction.
    */
    public void activateContextMenu(){
        getElement().callJsFunction("activateContextMenu");
    }
    
    /**
    * Initializes the event for when an edge is double-clicked.
    */
    public void initEventEdgeDblclick(){
        getElement().callJsFunction("eventDblClickEdge");
    }
    
    /**
    * Initializes the event for when an edge is changed.
    */
    public void initEventEdgeChanged(){
        getElement().callJsFunction("eventEdgeChanged");
    }
    
    /**
    * Initializes the event for when a cell is removed.
    */
    public void initEventCellRemoved(){
        getElement().callJsFunction("eventCellRemoved");
    }
    
    /**
    * Fires the event for when the graph starts loading.
    */
    public void fireGraphLoading(){
        getElement().callJsFunction("eventGraphLoading");
    }
    
    /**
     * Fires the event for when the graph finishes loading.
     */
    public void fireGraphLoaded(){
        getElement().callJsFunction("eventGraphLoaded");
    }
    
    /**
    * Initializes the event that adds the custom remove button tool to edges when hovered.
    */
    public void initEventAddEdgeButtonRemoveCustomTool(){
        getElement().callJsFunction("eventAddEdgeButtonRemoveCustomTool");
    }
    
    /**
    * Initializes the event that adds the custom remove button tool to nodes when hovered.
    */
    public void initEventAddNodeButtonRemoveCustomTool(){
        getElement().callJsFunction("eventAddNodeButtonRemoveCustomTool");
    }
    
    /**
    * Initializes the event that removes the custom remove button tool from nodes when the mouse leaves.
    */
    public void initEventRemoveNodeButtonRemoveCustomTool(){
        getElement().callJsFunction("eventRemoveNodeButtonRemoveCustomTool");
    }
    
    // </editor-fold>
    
    // <editor-fold desc="Listeners">
    
    /**
    * Adds a listener for when nodes are connected by an edge.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addNodesConnectedListener(ComponentEventListener<EdgeCreatedEvent> listener) {
        return addListener(EdgeCreatedEvent.class, listener);
    }

   /**
    * Adds a listener for when the graph is created.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addGraphCreatedListener(ComponentEventListener<GraphCreatedEvent> listener) {
        return addListener(GraphCreatedEvent.class, listener);
    }

   /**
    * Adds a listener for when the graph starts loading.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addGraphLoadingListener(ComponentEventListener<GraphLoadingEvent> listener) {
        return addListener(GraphLoadingEvent.class, listener);
    }

   /**
    * Adds a listener for when the graph finishes loading.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addGraphLoadedListener(ComponentEventListener<GraphLoadedEvent> listener) {
        return addListener(GraphLoadedEvent.class, listener);
    }

   /**
    * Adds a listener for when the graph is cleaned.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addGraphCleanedListener(ComponentEventListener<GraphCleanedEvent> listener) {
        return addListener(GraphCleanedEvent.class, listener);
    }

   /**
    * Adds a listener for when the graph is refreshed.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addGraphRefreshedListener(ComponentEventListener<GraphRefreshedEvent> listener) {
        return addListener(GraphRefreshedEvent.class, listener);
    }

   /**
    * Adds a listener for when a cell is brought to front.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addBringToFrontCellListener(ComponentEventListener<BringToFrontEvent> listener){
        return addListener(BringToFrontEvent.class, listener);
    }

   /**
    * Adds a listener for when a cell is sent to back.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addSendToBackCellListener(ComponentEventListener<SendToBackEvent> listener){
        return addListener(SendToBackEvent.class, listener);
    }

   /**
    * Adds a listener for when a node is changed.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addNodeChangedListener(ComponentEventListener<NodeChangedEvent> listener){
        return addListener(NodeChangedEvent.class, listener);
    }

   /**
    * Adds a listener for when the background node changes.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addBackgroundChangedListener(ComponentEventListener<BackgroundChangedEvent> listener){
        return addListener(BackgroundChangedEvent.class, listener);
    }

   /**
    * Adds a listener for when a node is moved.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addNodeMovedListener(ComponentEventListener<NodeMovedEvent> listener) {
        return addListener(NodeMovedEvent.class, listener);
    }

   /**
    * Adds a listener for when the background node is resized.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addNodeBackgroundResizedListener(ComponentEventListener<NodeBackgroundResizedEvent> listener) {
        return addListener(NodeBackgroundResizedEvent.class, listener);
    }

   /**
    * Adds a listener for when a cell is selected.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addCellSelectedListener(ComponentEventListener<CellSelectedEvent> listener) {
        return addListener(CellSelectedEvent.class, listener);
    }

   /**
    * Adds a listener for when a cell is unselected.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addCellUnselectedListener(ComponentEventListener<CellUnselectedEvent> listener) {
        return addListener(CellUnselectedEvent.class, listener);
    }

   /**
    * Adds a listener for when an edge is double-clicked.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addEdgeDblClickListener(ComponentEventListener<EdgeDblClickEvent> listener) {
        return addListener(EdgeDblClickEvent.class, listener);
    }

   /**
    * Adds a listener for when an edge is changed.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addEdgeChangedListener(ComponentEventListener<EdgeChangedEvent> listener) {
        return addListener(EdgeChangedEvent.class, listener);
    }

   /**
    * Adds a listener for when a cell is removed.
    * 
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addCellRemovedListener(ComponentEventListener<CellRemovedEvent> listener) {
        return addListener(CellRemovedEvent.class, listener);
    }
    
    /**
    * Adds a listener for when the custom remove button tool is clicked.
    *
    * @param listener the listener to handle the event
    * @return a registration for removing the listener
    */
    public Registration addButtonRemoveCustomToolClicked(ComponentEventListener<ButtonRemoveCustomToolClicked> listener){
        return addListener(ButtonRemoveCustomToolClicked.class, listener);
    }

    // </editor-fold>
    
    /**
    * Retrieves a node from the list by its unique ID.
    *
    * @param id the ID of the node to retrieve
    * @return the X6Node with the specified ID, or null.
    */
    public X6Node getNodeById(String id) {
        return nodes.stream()
                    .filter(node -> node.getId().equals(id))
                    .findFirst()
                    .orElse(null);
    }

    /**
     * Retrieves an edge from the list by its unique ID.
     *
     * @param id the ID of the edge to retrieve
     * @return the X6Edge with the specified ID, or null.
     */
    public X6Edge getEdgeById(String id) {
        return edges.stream()
                    .filter(edge -> edge.getId().equals(id))
                    .findFirst()
                    .orElse(null);
    }
    
    /**
    * Retrieves a text node from the list by its unique ID.
    *
    * @param id the ID of the text node to retrieve
    * @return the X6NodeText with the specified ID, or null.
    */
    public X6NodeText getNodeTextById(String id) {
        return textNodes.stream()
                        .filter(text -> text.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }
 
}
