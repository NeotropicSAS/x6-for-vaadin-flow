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
package com.neotropic.flow.component.antvx6.objects;

import com.neotropic.flow.component.antvx6.constants.X6Constants;
import com.neotropic.flow.component.antvx6.styles.X6NodeLabelStyles;
import com.neotropic.flow.component.antvx6.styles.X6NodeStyles;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Represents a basic node in the X6 graph model.
 * This abstract class serves as a base for {@link X6Node}, {@link X6NodeBackground} and {@link X6NodeText}.
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public abstract class X6AbstractNode extends X6Cell { 
    private String shape;
    private String imgUrl;
    private boolean movable; 
    private String parentId;
    private String label;
    private X6NodeStyles nodeStyles;
    private X6NodeLabelStyles nodeLabelStyles;

    public X6AbstractNode(){
        super();
        this.setCellType(X6Constants.CELL_NODE);
        this.movable = true;
        this.nodeStyles = new X6NodeStyles();
        this.nodeLabelStyles = new X6NodeLabelStyles();
    }
    
    public X6AbstractNode(String id, double x, double y,double width, double height, String shape){
        super(id, x, y, width, height);
        this.setCellType(X6Constants.CELL_NODE);
        this.shape = shape;
        this.imgUrl = "";
        this.movable = true;
        this.parentId = "";
        this.label = "";
        this.nodeStyles = new X6NodeStyles();
        this.nodeLabelStyles = new X6NodeLabelStyles();
    }
}
