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
package com.neotropic.flow.component.antvx6.objects;

import com.neotropic.flow.component.antvx6.constants.X6Constants;
import com.neotropic.flow.component.antvx6.styles.X6EdgeStyles;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Represents a connection between two or more nodes in the X6 graph with one label.
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class X6Edge extends X6Cell{
    private String idSource;
    private String idTarget;
    private List<Vertex> vertices;
    private List<X6EdgeLabel> edgeLabels;
    private X6EdgeStyles edgeStyles;
    
    public X6Edge(){
        super();
        super.setCellType(X6Constants.CELL_EDGE);
        this.vertices = new ArrayList<>();
        this.edgeLabels = new ArrayList<>();
        this.edgeStyles = new X6EdgeStyles();
    }
    
    //Create an edge without labels
    public X6Edge(String id, String idSource, String idTarget){
        super(id);
        this.idSource = idSource;
        this.idTarget = idTarget;
        super.setCellType(X6Constants.CELL_EDGE);
        this.vertices = new ArrayList<>();
        this.edgeLabels = new ArrayList<>();
        this.edgeStyles = new X6EdgeStyles();
    }
    
    //Create an edge with one label
    public X6Edge(String id, String idSource, String idTarget, String label){
        super(id);
        this.idSource = idSource;
        this.idTarget = idTarget;
        super.setCellType(X6Constants.CELL_EDGE);
        this.vertices = new ArrayList<>();
        this.edgeLabels = new ArrayList<>();
        this.edgeStyles = new X6EdgeStyles();
        this.edgeLabels.add(new X6EdgeLabel(label, 0.5));
    }
    
    //Create and edge with multiple labels
    public X6Edge(String id, String idSource, String idTarget, List<X6EdgeLabel> edgeLabels){
        super(id);
        this.idSource = idSource;
        this.idTarget = idTarget;
        super.setCellType(X6Constants.CELL_EDGE);
        this.vertices = new ArrayList<>();
        this.edgeLabels = edgeLabels;
        this.edgeStyles = new X6EdgeStyles();
    }
    
    public void addLabel(String label, double distance){
        if(edgeLabels != null)
            edgeLabels.add(new X6EdgeLabel(label, distance));
    }
    
    public X6EdgeLabel getLabelAt(int labelPos){
        if(edgeLabels != null && edgeLabels.size() > labelPos && edgeLabels.get(labelPos) != null)
            return edgeLabels.get(labelPos);
        return null;
    }
    
}