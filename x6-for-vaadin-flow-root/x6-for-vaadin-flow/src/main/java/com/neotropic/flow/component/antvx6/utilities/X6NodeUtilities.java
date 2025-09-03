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
package com.neotropic.flow.component.antvx6.utilities;

import com.neotropic.flow.component.antvx6.constants.X6Constants;
import com.neotropic.flow.component.antvx6.objects.X6AbstractNode;
import com.neotropic.flow.component.antvx6.styles.X6NodeStyles;
import java.util.HashMap;

/**
* Utilities to X6 nodes
* @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
*/
public class X6NodeUtilities {
    
    /**
    * Maps the styles defined in the class to the node's styles HashMap.
    * 
    * @param node the X6AbstractNode instance whose styles will be mapped
    */
    public static void setStylesToMap(X6AbstractNode node){
        if(node == null)
            return;
        
        if(node.getNodeStyles() == null)
            node.setNodeStyles(new X6NodeStyles());
        
        if(node.getStyles() == null)
            node.setStyles(new HashMap<>());
        
        //Copy X6NodeStyles to hashMap<String, String>
        //Stroke color
        String strokeColor = node.getNodeStyles().getStrokeColor();
        if (strokeColor == null || strokeColor.isBlank()) {
            strokeColor = "black";
            node.getNodeStyles().setStrokeColor(strokeColor);
        }
        node.setStyle(X6Constants.STYLE_STROKECOLOR, strokeColor);
        
        //Fill color
        String fillColor = node.getNodeStyles().getFillColor();
        if (fillColor == null || fillColor.isBlank()) {
            fillColor = X6Constants.GRAPH_BACKGROUND_COLOR;
            node.getNodeStyles().setFillColor(fillColor);
        }
        node.setStyle(X6Constants.STYLE_FILLCOLOR, fillColor);
            
        //Dash
        String dash = node.getNodeStyles().getDash();
        if (dash == null || dash.isBlank()) {
            dash = "0";
            node.getNodeStyles().setDash(dash);
        }
        node.setStyle(X6Constants.STYLE_DASHED, dash);
        
        //Border radius
        int borderRadius = node.getNodeStyles().getBorderRadius();
        node.setStyle(X6Constants.STYLE_ROUNDED, borderRadius + "");
        
        //Stroke width
        double strokeWidth = node.getNodeStyles().getStrokeWidth();
        node.setStyle(X6Constants.STYLE_STROKEWIDTH, strokeWidth + "");
        
        //Font size
        double fontSize = node.getNodeLabelStyles().getFontSize();
        node.setStyle(X6Constants.STYLE_FONTSIZE, fontSize + "");
        
        //Font color
        String fontColor = node.getNodeLabelStyles().getFontColor();
        if (fontColor == null || fontColor.isBlank()) {
            fontColor = "black";
            node.getNodeLabelStyles().setFontColor(fontColor);
        }
        node.setStyle(X6Constants.STYLE_FONTCOLOR, fontColor);
        
        //Font family
        String fontFamily = node.getNodeLabelStyles().getFontFamily();
        if (fontFamily == null || fontFamily.isBlank()) {
            fontFamily = "Arial";
            node.getNodeLabelStyles().setFontFamily(fontFamily);
        }
        node.setStyle(X6Constants.STYLE_FONTFAMILY, fontFamily);
    }
    
    /**
    * Updates the styles of the class object from the provided styles HashMap.
    *
    * @param node the X6AbstractNode instance to update with the styles.
    * @param stylePropertyMap a HashMap containing style properties.
    */
    public static void setNodeStyles(X6AbstractNode node, HashMap<String, String> stylePropertyMap){
        if (stylePropertyMap.containsKey(X6Constants.STYLE_STROKECOLOR) && !stylePropertyMap.get(X6Constants.STYLE_STROKECOLOR).isBlank())
            node.getNodeStyles().setStrokeColor(stylePropertyMap.get(X6Constants.STYLE_STROKECOLOR));

        if (stylePropertyMap.containsKey(X6Constants.STYLE_FILLCOLOR) && !stylePropertyMap.get(X6Constants.STYLE_FILLCOLOR).isBlank()) {
            String fillColor = stylePropertyMap.get(X6Constants.STYLE_FILLCOLOR);
            if ("none".equals(fillColor))
                node.getNodeStyles().setFillColor(X6Constants.GRAPH_BACKGROUND_COLOR);
            else
                node.getNodeStyles().setFillColor(fillColor);
        }

        if (stylePropertyMap.containsKey(X6Constants.STYLE_DASHED) && !stylePropertyMap.get(X6Constants.STYLE_DASHED).isBlank()) {
            if (!"0".equals(stylePropertyMap.get(X6Constants.STYLE_DASHED)))
                node.getNodeStyles().setDash("5.5");
        }

        if (stylePropertyMap.containsKey(X6Constants.STYLE_ROUNDED) && !stylePropertyMap.get(X6Constants.STYLE_ROUNDED).isBlank()) {
            if ("0".equals(stylePropertyMap.get(X6Constants.STYLE_ROUNDED)))
                node.getNodeStyles().setBorderRadius(0);
            else
                node.getNodeStyles().setBorderRadius(8); 
        }

        if (stylePropertyMap.containsKey(X6Constants.STYLE_STROKEWIDTH) && !stylePropertyMap.get(X6Constants.STYLE_STROKEWIDTH).isBlank()){
            double strokeWidth = Double.parseDouble(stylePropertyMap.get(X6Constants.STYLE_STROKEWIDTH));
            node.getNodeStyles().setStrokeWidth(strokeWidth);
        }
        
        if (stylePropertyMap.containsKey(X6Constants.STYLE_FONTSIZE) && !stylePropertyMap.get(X6Constants.STYLE_FONTSIZE).isBlank()) {
            double fontSize = Double.parseDouble(stylePropertyMap.get(X6Constants.STYLE_FONTSIZE));
            node.getNodeLabelStyles().setFontSize(fontSize);
        }

        if (stylePropertyMap.containsKey(X6Constants.STYLE_FONTCOLOR) && !stylePropertyMap.get(X6Constants.STYLE_FONTCOLOR).isBlank())
            node.getNodeLabelStyles().setFontColor(stylePropertyMap.get(X6Constants.STYLE_FONTCOLOR));

        if (stylePropertyMap.containsKey(X6Constants.STYLE_FONTFAMILY) && !stylePropertyMap.get(X6Constants.STYLE_FONTFAMILY).isBlank())
            node.getNodeLabelStyles().setFontFamily(stylePropertyMap.get(X6Constants.STYLE_FONTFAMILY));
    }
}
