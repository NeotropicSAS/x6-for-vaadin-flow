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
import com.neotropic.flow.component.antvx6.objects.Geometry;

/**
* Utilities to X6NodeText
* @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
*/
public class X6NodeTextUtilities {
    
    /**
    * Calculates and sets the dimensions of a node label based on its text and font size.
    *
    * @param geometryLabel The geometry of the label to update dimensions for.
    * @param labelText     The text of the label used to calculate its width.
    * @param fontSize      The font size of the label text, used for height calculation.
    */
    public static void calculateLabelDimensions(Geometry geometryLabel, String labelText, double fontSize) {
        if (geometryLabel != null && labelText != null && fontSize >= 0) {
            int padding = 10;
            double width = labelText.length() * fontSize / 2 + padding;
            double height = fontSize + padding; 

            geometryLabel.getDimensions().setWidth(width);
            geometryLabel.getDimensions().setHeight(height);
        }
    }

    /**
    * Calculates the position of a node label relative to its parent geometry, 
    *
    * @param geometryParent The geometry of the parent node.
    * @param geometryLabel  The geometry of the label to position.
    * @param position       The position of the label ("TOP" or "BOTTOM").
    */
    public static void calculateLabelPosition(Geometry geometryParent, Geometry geometryLabel, String position, double displacementY){
        if(geometryParent != null && geometryLabel != null && !position.isBlank()){
            double xCenter = geometryParent.getCoordinates().getX() + (geometryParent.getDimensions().getWidth() / 2);
            double x,y = 0;
            if(position.equals(X6Constants.TOP)){
                x = xCenter - (geometryLabel.getDimensions().getWidth() / 2);
                y = geometryParent.getCoordinates().getY() - displacementY;
                geometryLabel.getCoordinates().setX(x);
                geometryLabel.getCoordinates().setY(y);
            }else if(position.equals(X6Constants.BOTTOM)){
                x = xCenter - (geometryLabel.getDimensions().getWidth() / 2);
                y = geometryParent.getCoordinates().getY() + geometryParent.getDimensions().getHeight() + displacementY;
                geometryLabel.getCoordinates().setX(x);
                geometryLabel.getCoordinates().setY(y);
            }
        }
    }
}
