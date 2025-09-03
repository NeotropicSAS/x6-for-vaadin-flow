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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Represents a cell in the X6 graph.
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Data
@SuperBuilder
public class X6Cell {
    private String id;
    private Geometry geometry;
    private String cellType;
    private HashMap<String, String> styles;
    private List<String> tools;

    public X6Cell(){
        this.styles = new HashMap<>();
        this.tools = new ArrayList<>();
    }
    
    public X6Cell(String id, double x, double y, double width, double height) {
        this.id = id;
        this.geometry = new Geometry(x, y, width, height);
        this.styles = new HashMap<>();
        this.tools = new ArrayList<>();
    }
    
    public X6Cell(String id){
        this.id = id;
        this.geometry = new Geometry();
        this.styles = new HashMap<>();
        this.tools = new ArrayList<>();
    }
    
    public boolean isEdge(){
        return this.cellType.equals(X6Constants.CELL_EDGE);
    }
    
    public boolean isNode(){
        return this.cellType.equals(X6Constants.CELL_NODE );
    }
    
    public void setStyle(String style, String value){
        styles.put(style, value);
    }
}
