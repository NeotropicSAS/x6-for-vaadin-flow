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
package com.neotropic.flow.component.antvx6.objects;

import com.neotropic.flow.component.antvx6.styles.X6EdgeLabelStyles;
import lombok.Data;

/**
 * Represents a label of an edge.
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Data
public class X6EdgeLabel {
    private String label;
    //Value between 0 and 1, for example to place the label in the middle: 0.5
    private double distance;
    private X6EdgeLabelStyles edgeLabelStyles;
    
    //Sets the label in the middle of the edge
    public X6EdgeLabel(String label){
        this.label = label;
        this.distance = 0.5;
        this.edgeLabelStyles = new X6EdgeLabelStyles();
    }
    
    public X6EdgeLabel(String label, double distance){
        this.label = label;
        this.distance = distance;
        this.edgeLabelStyles = new X6EdgeLabelStyles();
    }
}
