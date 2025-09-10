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
package com.neotropic.flow.component.antvx6.constants;

/**
 * Provides constants for configuring the Antv X6 graph instance and its objects.
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>
 */
public class X6Constants {
    /**
    * Canvas with no control on human-node interactions
    */
    public static final int BASIC_GRAPH_TYPE = 0;
    
    /**
    * Canvas with human-node control on interactions 
    */
    public static final int INTERACTIONS_GRAPH_TYPE = 1;
    
    /**
    * The width of the graph in pixels.
    */
    public static final int GRAPH_WIDTH_OBJECT_VIEW = 1000;

    /**
     * The height of the graph in pixels.
     */
    public static final int GRAPH_HEIGHT_OBJECT_VIEW = 600;

    /**
     * The width of the graph in pixels for fiber splitter view.
     */
    public static final int GRAPH_WIDTH_FIBER_SPLITTER = 1500;

    /**
     * The height of the graph in pixels for fiber splitter view.
     */
    public static final int GRAPH_HEIGHT_FIBER_SPLITTER = 700;

    /**
     * The width of the graph in pixels for splice box view.
     */
    public static final int GRAPH_WIDTH_SPLICE_BOX = 1500;

    /**
     * The height of the graph in pixels for splice box view.
     */
    public static final int GRAPH_HEIGHT_SPLICE_BOX = 700;

    /**
     * The width of the graph in pixels for physical path view.
     */
    public static final int GRAPH_WIDTH_PHYSICAL_PATH = 1300;

    /**
     * The height of the graph in pixels for physical path view.
     */
    public static final int GRAPH_HEIGHT_PHYSICAL_PATH = 600;

    /**
    * The width of the graph in pixels for physical tree view.
    */
    public static final int GRAPH_WIDTH_PHYSICAL_TREE = 1500;

    /**
    * The height of the graph in pixels for physical tree view.
    */
    public static final int GRAPH_HEIGHT_PHYSICAL_TREE = 500;

    /**
    * The background color of the graph.
    */
    public static final String GRAPH_BACKGROUND_COLOR = "#f8f9fa";

    /**
    * Indicates whether the graph should display a grid.
    */
    public static final boolean GRAPH_GRID = true;

    /**
    * Indicates whether panning is enabled for the graph.
    */
    public static final boolean GRAPH_PANNING = false;

    /**
    * Indicates whether mouse wheel zooming is enabled for the graph.
    */
    public static final boolean GRAPH_MOUSE_WHEEL = true;

    /**
    * The zoom factor applied to the graph.
    */
    public static final double GRAPH_ZOOM = -0.1;

    /**
    * The string identifier for image shape types.
    */
    public static final String SHAPE_IMAGE = "image";

    /**
    * The string identifier for rect shape types.
    */
    public static final String SHAPE_RECT = "rect";

    /**
    * The string identifier for circle shape types.
    */
    public static final String SHAPE_CIRCLE = "circle";

    /**
    * The string identifier for ellipse shape types.
    */
    public static final String SHAPE_ELLIPSE = "ellipse";

    /**
    * The string identifier for text-block shape types.
    */
    public static final String SHAPE_TEXT_BLOCK = "text-block";

    /**
    * The string identifier for cell node types.
    */
    public static final String CELL_NODE = "node";
    
    /**
    * The string identifier for cell edge types.
    */
    public static final String CELL_EDGE = "edge";

    /**
    * Represents the position of the label at the bottom of the node.
    */
    public static final String LABEL_NODE_POSITION_BOTTOM = "bottom";

    /**
    * Represents the default (center) position of the label on the node.
    */
    public static final String LABEL_NODE_POSITION_DEFAULT = "default";
    
    /*
    * Represents whether the node should be hidden
    */
    public static final String LABEL_NODE_HIDDEN = "hidden";

    /*
    * Represents whether the node should be visible
    */
    public static final String LABEL_NODE_VISIBLE = "visible";
    
    public static final String BOTTOM = "bottom";
    
    public static final String TOP = "top";
    
    /*
    * Tool node editor
    */
    public static final String NODE_EDITOR = "node-editor";
   
    /**
    * Represents the border color of the element.
    */
    public static final String STYLE_STROKECOLOR = "strokeColor";

    /**
     * Represents the fill color of the element.
     */
    public static final String STYLE_FILLCOLOR = "fillColor";

    /**
     * Represents whether the border is dashed.
     */
    public static final String STYLE_DASHED = "dashed";

    /**
     * Represents whether the corners are rounded.
     */
    public static final String STYLE_ROUNDED = "rounded";

    /**
     * Represents the width of the border.
     */
    public static final String STYLE_STROKEWIDTH = "strokeWidth";

    /**
     * Represents the font size of the text.
     */
    public static final String STYLE_FONTSIZE = "fontSize";

    /**
     * Represents the font color of the text.
     */
    public static final String STYLE_FONTCOLOR = "fontColor";

    /**
     * Represents the font family of the text.
     */
    public static final String STYLE_FONTFAMILY = "fontFamily";

    /**
     * Represents the z-index of the element.
     */
    public static final String STYLE_ZINDEX = "zIndex";

    /**
     * Represents no value or none.
     */
    public static String NONE = "none";
}
