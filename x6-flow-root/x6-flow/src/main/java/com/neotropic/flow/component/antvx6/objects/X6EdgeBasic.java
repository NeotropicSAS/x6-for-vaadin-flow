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

import com.neotropic.flow.component.antvx6.constants.X6Constants;
import com.neotropic.flow.component.antvx6.styles.X6EdgeStyles;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a connection between two or more nodes in the X6 graph (with no label).
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
public class X6EdgeBasic extends X6Cell{
    private String idSource;
    private String idTarget;
    private X6EdgeStyles edgeStyles;
    private List<Vertex> vertices;
    
    public X6EdgeBasic(){
        super();
        super.setCellType(X6Constants.CELL_EDGE);
        this.vertices = new ArrayList<>();
        this.edgeStyles = new X6EdgeStyles();
    }

    public X6EdgeBasic(String id, String idSource, String idTarget) {
        super(id);
        super.setCellType(X6Constants.CELL_EDGE);
        this.idSource = idSource;
        this.idTarget = idTarget;
        this.vertices = new ArrayList<>();
        this.edgeStyles = new X6EdgeStyles();
    }
    
    public String getIdSource() {
        return idSource;
    }

    public void setIdSource(String idSource) {
        this.idSource = idSource;
    }

    public String getIdTarget() {
        return idTarget;
    }

    public void setIdTarget(String idTarget) {
        this.idTarget = idTarget;
    }

    public X6EdgeStyles getEdgeStyles() {
        return edgeStyles;
    }

    public void setEdgeStyles(X6EdgeStyles edgeStyles) {
        this.edgeStyles = edgeStyles;
    }
    
    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }
    
    public void addVertex(Vertex vertex){
        this.vertices.add(vertex);
    }
    
}
