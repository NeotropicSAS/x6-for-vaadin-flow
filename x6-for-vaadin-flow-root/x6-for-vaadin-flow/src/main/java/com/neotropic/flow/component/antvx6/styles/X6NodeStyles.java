/*
 * Copyright 2024 Neotropic SAS.
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
package com.neotropic.flow.component.antvx6.styles;

import com.neotropic.flow.component.antvx6.constants.X6Constants;
import lombok.Data;

/**
 * Represents the styles of a node
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Data
public class X6NodeStyles {
    private String fillColor;
    private String strokeColor;
    private double strokeWidth;
    private String dash;
    private int borderRadius;
    private int zIndex;
    
    public X6NodeStyles(){
        this.fillColor = X6Constants.GRAPH_BACKGROUND_COLOR;
        this.strokeColor = "black";
        this.strokeWidth = 1.0;
        this.dash = "0";
        this.borderRadius = 0;
        this.zIndex = 1;
    }
}
