/*
 * Copyright 2025 Neotropic SAS.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.neotropic.flow.component.antvx6.jsonGenerator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.neotropic.flow.component.antvx6.objects.Vertex;
import com.neotropic.flow.component.antvx6.objects.X6Edge;
import com.neotropic.flow.component.antvx6.objects.X6EdgeLabel;
import com.neotropic.flow.component.antvx6.objects.X6Node;
import com.neotropic.flow.component.antvx6.objects.X6NodeBackground;
import com.neotropic.flow.component.antvx6.objects.X6NodeText;

/**
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
public class JsonGenerator {
    
    public static JsonObject generateJsonNode(X6Node node){
        JsonObject nodeData = new JsonObject();

        nodeData.addProperty("id", node.getId() != null ? node.getId() : "");

        JsonObject geometry = new JsonObject();
        JsonObject coordinates = new JsonObject();
        coordinates.addProperty("x", node.getGeometry() != null ? node.getGeometry().getCoordinates().getX() : 0);
        coordinates.addProperty("y", node.getGeometry() != null ? node.getGeometry().getCoordinates().getY() : 0);
        geometry.add("coordinates", coordinates);

        JsonObject dimensions = new JsonObject();
        dimensions.addProperty("width", node.getGeometry() != null ? node.getGeometry().getDimensions().getWidth() : 0);
        dimensions.addProperty("height", node.getGeometry() != null ? node.getGeometry().getDimensions().getHeight() : 0);
        geometry.add("dimensions", dimensions);

        nodeData.add("geometry", geometry);

        JsonArray toolsArray = new JsonArray();
        if (node.getTools() != null) {
            for (String tool : node.getTools()) 
                toolsArray.add(tool != null ? tool : "");
        }
        nodeData.add("tools", toolsArray);

        nodeData.addProperty("shape", node.getShape() != null ? node.getShape() : "");
        nodeData.addProperty("imgUrl", node.getImgUrl() != null ? node.getImgUrl() : "");
        nodeData.addProperty("movable", node.isMovable());
        nodeData.addProperty("parentId", node.getParentId() != null ? node.getParentId() : "");
        nodeData.addProperty("label", node.getLabel() != null ? node.getLabel() : "");

        JsonObject nodeStyles = new JsonObject();
        nodeStyles.addProperty("fillColor", node.getNodeStyles() != null && node.getNodeStyles().getFillColor() != null ? node.getNodeStyles().getFillColor() : "");
        nodeStyles.addProperty("strokeColor", node.getNodeStyles() != null && node.getNodeStyles().getStrokeColor() != null ? node.getNodeStyles().getStrokeColor() : "");
        nodeStyles.addProperty("strokeWidth", node.getNodeStyles() != null ? node.getNodeStyles().getStrokeWidth() : 0);
        nodeStyles.addProperty("dash", node.getNodeStyles() != null && node.getNodeStyles().getDash() != null ? node.getNodeStyles().getDash() : "");
        nodeStyles.addProperty("borderRadius", node.getNodeStyles() != null ? node.getNodeStyles().getBorderRadius() : 0);
        nodeStyles.addProperty("zIndex", node.getNodeStyles() != null ? node.getNodeStyles().getZIndex() : 0);
        nodeData.add("nodeStyles", nodeStyles);

        JsonObject labelStyles = new JsonObject();
        labelStyles.addProperty("fontColor", node.getNodeLabelStyles() != null && node.getNodeLabelStyles().getFontColor() != null ? node.getNodeLabelStyles().getFontColor() : "");
        labelStyles.addProperty("fontSize", node.getNodeLabelStyles() != null ? node.getNodeLabelStyles().getFontSize() : 0);
        labelStyles.addProperty("fontFamily", node.getNodeLabelStyles() != null && node.getNodeLabelStyles().getFontFamily() != null ? node.getNodeLabelStyles().getFontFamily() : "");
        labelStyles.addProperty("labelPosition", node.getNodeLabelStyles() != null && node.getNodeLabelStyles().getLabelPosition() != null ? node.getNodeLabelStyles().getLabelPosition() : "");
        labelStyles.addProperty("visibility", node.getNodeLabelStyles() != null && node.getNodeLabelStyles().getVisibility() != null ? node.getNodeLabelStyles().getVisibility() : "");
        nodeData.add("nodeLabelStyles", labelStyles);

        nodeData.addProperty("port", node.isPort());
        
        return nodeData;
    }
    
    public static JsonObject generateJsonNodeText(X6NodeText nodeText){
        JsonObject textData = new JsonObject();

        textData.addProperty("id", nodeText.getId() != null ? nodeText.getId() : "");

        JsonObject geometry = new JsonObject();
        JsonObject coordinates = new JsonObject();
        coordinates.addProperty("x", nodeText.getGeometry() != null ? nodeText.getGeometry().getCoordinates().getX() : 0);
        coordinates.addProperty("y", nodeText.getGeometry() != null ? nodeText.getGeometry().getCoordinates().getY() : 0);
        geometry.add("coordinates", coordinates);

        JsonObject dimensions = new JsonObject();
        dimensions.addProperty("width", nodeText.getGeometry() != null ? nodeText.getGeometry().getDimensions().getWidth() : 0);
        dimensions.addProperty("height", nodeText.getGeometry() != null ? nodeText.getGeometry().getDimensions().getHeight() : 0);
        geometry.add("dimensions", dimensions);

        textData.add("geometry", geometry);

        textData.addProperty("shape", nodeText.getShape() != null ? nodeText.getShape() : "");
        textData.addProperty("imgUrl", nodeText.getImgUrl() != null ? nodeText.getImgUrl() : "");
        textData.addProperty("movable", nodeText.isMovable());
        textData.addProperty("parentId", nodeText.getParentId() != null ? nodeText.getParentId() : "");
        textData.addProperty("label", nodeText.getLabel() != null ? nodeText.getLabel() : "");

        JsonObject nodeStyles = new JsonObject();
        nodeStyles.addProperty("borderRadius", nodeText.getNodeStyles() != null ? nodeText.getNodeStyles().getBorderRadius() : 0);
        nodeStyles.addProperty("fillColor", nodeText.getNodeStyles() != null && nodeText.getNodeStyles().getFillColor() != null ? nodeText.getNodeStyles().getFillColor() : "");
        nodeStyles.addProperty("strokeColor", nodeText.getNodeStyles() != null && nodeText.getNodeStyles().getStrokeColor() != null ? nodeText.getNodeStyles().getStrokeColor() : "");
        nodeStyles.addProperty("strokeWidth", nodeText.getNodeStyles() != null ? nodeText.getNodeStyles().getStrokeWidth() : 0);
        nodeStyles.addProperty("dash", nodeText.getNodeStyles() != null && nodeText.getNodeStyles().getDash() != null ? nodeText.getNodeStyles().getDash() : "");
        nodeStyles.addProperty("zIndex", nodeText.getNodeStyles() != null ? nodeText.getNodeStyles().getZIndex() : 0);
        textData.add("nodeStyles", nodeStyles);

        JsonObject labelStyles = new JsonObject();
        labelStyles.addProperty("fontColor", nodeText.getNodeLabelStyles() != null && nodeText.getNodeLabelStyles().getFontColor() != null ? nodeText.getNodeLabelStyles().getFontColor() : "");
        labelStyles.addProperty("fontSize", nodeText.getNodeLabelStyles() != null ? nodeText.getNodeLabelStyles().getFontSize() : 0);
        labelStyles.addProperty("fontFamily", nodeText.getNodeLabelStyles() != null && nodeText.getNodeLabelStyles().getFontFamily() != null ? nodeText.getNodeLabelStyles().getFontFamily() : "");
        labelStyles.addProperty("labelPosition", nodeText.getNodeLabelStyles() != null && nodeText.getNodeLabelStyles().getLabelPosition() != null ? nodeText.getNodeLabelStyles().getLabelPosition() : "");
        labelStyles.addProperty("visibility", nodeText.getNodeLabelStyles() != null && nodeText.getNodeLabelStyles().getVisibility() != null ? nodeText.getNodeLabelStyles().getVisibility() : "");
        textData.add("nodeLabelStyles", labelStyles);
        
        return textData;
    }
    
    public static JsonObject generateJsonBackground(X6NodeBackground background){
        JsonObject backgroundData = new JsonObject();

        backgroundData.addProperty("id", background.getId() != null ? background.getId() : "");

        JsonObject geometry = new JsonObject();
        JsonObject coordinates = new JsonObject();
        coordinates.addProperty("x", background.getGeometry() != null ? background.getGeometry().getCoordinates().getX() : 0);
        coordinates.addProperty("y", background.getGeometry() != null ? background.getGeometry().getCoordinates().getY() : 0);
        geometry.add("coordinates", coordinates);

        JsonObject dimensions = new JsonObject();
        dimensions.addProperty("width", background.getGeometry() != null ? background.getGeometry().getDimensions().getWidth() : 0);
        dimensions.addProperty("height", background.getGeometry() != null ? background.getGeometry().getDimensions().getHeight() : 0);
        geometry.add("dimensions", dimensions);

        backgroundData.add("geometry", geometry);

        backgroundData.addProperty("shape", background.getShape() != null ? background.getShape() : "");
        backgroundData.addProperty("imgUrl", background.getImgUrl() != null ? background.getImgUrl() : "");
        backgroundData.addProperty("movable", background.isMovable());
        backgroundData.addProperty("parentId", background.getParentId() != null ? background.getParentId() : "");
        backgroundData.addProperty("label", background.getLabel() != null ? background.getLabel() : "");

        JsonObject nodeStyles = new JsonObject();
        nodeStyles.addProperty("fillColor", background.getNodeStyles() != null && background.getNodeStyles().getFillColor() != null ? background.getNodeStyles().getFillColor() : "");
        nodeStyles.addProperty("strokeColor", background.getNodeStyles() != null && background.getNodeStyles().getStrokeColor() != null ? background.getNodeStyles().getStrokeColor() : "");
        nodeStyles.addProperty("strokeWidth", background.getNodeStyles() != null ? background.getNodeStyles().getStrokeWidth() : 0);
        nodeStyles.addProperty("dash", background.getNodeStyles() != null && background.getNodeStyles().getDash() != null ? background.getNodeStyles().getDash() : "");
        nodeStyles.addProperty("borderRadius", background.getNodeStyles() != null ? background.getNodeStyles().getBorderRadius() : 0);
        nodeStyles.addProperty("zIndex", background.getNodeStyles() != null ? background.getNodeStyles().getZIndex() : 0);
        backgroundData.add("nodeStyles", nodeStyles);

        JsonObject labelStyles = new JsonObject();
        labelStyles.addProperty("fontColor", background.getNodeLabelStyles() != null && background.getNodeLabelStyles().getFontColor() != null ? background.getNodeLabelStyles().getFontColor() : "");
        labelStyles.addProperty("fontSize", background.getNodeLabelStyles() != null ? background.getNodeLabelStyles().getFontSize() : 0);
        labelStyles.addProperty("fontFamily", background.getNodeLabelStyles() != null && background.getNodeLabelStyles().getFontFamily() != null ? background.getNodeLabelStyles().getFontFamily() : "");
        labelStyles.addProperty("labelPosition", background.getNodeLabelStyles() != null && background.getNodeLabelStyles().getLabelPosition() != null ? background.getNodeLabelStyles().getLabelPosition() : "");
        labelStyles.addProperty("visibility", background.getNodeLabelStyles() != null && background.getNodeLabelStyles().getVisibility() != null ? background.getNodeLabelStyles().getVisibility() : "");
        backgroundData.add("nodeLabelStyles", labelStyles);
        
        return backgroundData;
    } 
    
    public static JsonObject generateJsonEdge(X6Edge edge){
        JsonObject edgeData = new JsonObject();

        edgeData.addProperty("id", edge.getId() != null ? edge.getId() : "");
        edgeData.addProperty("idSource", edge.getIdSource() != null ? edge.getIdSource() : "");
        edgeData.addProperty("idTarget", edge.getIdTarget() != null ? edge.getIdTarget() : "");

        JsonArray verticesArray = new JsonArray();
        if (edge.getVertices() != null) {
            for (Vertex vertex : edge.getVertices()) {
                JsonObject vertexObj = new JsonObject();
                vertexObj.addProperty("x", vertex != null ? vertex.getX() : 0);
                vertexObj.addProperty("y", vertex != null ? vertex.getY() : 0);
                verticesArray.add(vertexObj);
            }
        }
        edgeData.add("vertices", verticesArray);

        JsonArray labelsArray = new JsonArray();
        if (edge.getEdgeLabels() != null) {
            for (X6EdgeLabel label : edge.getEdgeLabels()) {
                JsonObject labelObj = new JsonObject();
                labelObj.addProperty("label", label.getLabel() != null ? label.getLabel() : "");
                labelObj.addProperty("distance", label != null ? label.getDistance() : 0);

                JsonObject edgeLabelStyles = new JsonObject();
                edgeLabelStyles.addProperty("fillColor", label != null && label.getEdgeLabelStyles() != null && label.getEdgeLabelStyles().getFillColor() != null ? label.getEdgeLabelStyles().getFillColor() : "");
                edgeLabelStyles.addProperty("fontColor", label != null && label.getEdgeLabelStyles() != null && label.getEdgeLabelStyles().getFontColor() != null ? label.getEdgeLabelStyles().getFontColor() : "");
                edgeLabelStyles.addProperty("fontSize", label != null && label.getEdgeLabelStyles() != null ? label.getEdgeLabelStyles().getFontSize() : 0);
                edgeLabelStyles.addProperty("fontFamily", label != null && label.getEdgeLabelStyles() != null && label.getEdgeLabelStyles().getFontFamily() != null ? label.getEdgeLabelStyles().getFontFamily() : "");
                edgeLabelStyles.addProperty("borderRadius", label != null && label.getEdgeLabelStyles() != null ? label.getEdgeLabelStyles().getBorderRadius() : 0);
                labelObj.add("edgeLabelStyles", edgeLabelStyles);

                labelsArray.add(labelObj);
            }
        }
        edgeData.add("edgeLabels", labelsArray);

        JsonObject edgeStyles = new JsonObject();
        edgeStyles.addProperty("strokeColor", edge.getEdgeStyles() != null && edge.getEdgeStyles().getStrokeColor() != null ? edge.getEdgeStyles().getStrokeColor() : "");
        edgeStyles.addProperty("strokeWidth", edge.getEdgeStyles() != null ? edge.getEdgeStyles().getStrokeWidth() : 0);
        edgeStyles.addProperty("dash", edge.getEdgeStyles() != null ? edge.getEdgeStyles().getDash() : 0.0);
        edgeStyles.addProperty("borderRadius", edge.getEdgeStyles() != null ? edge.getEdgeStyles().getBorderRadius() : 0);
        edgeStyles.addProperty("zIndex", edge.getEdgeStyles() != null ? edge.getEdgeStyles().getZIndex() : 0);
        edgeData.add("edgeStyles", edgeStyles);
        
        return edgeData;
    }
}
