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
package com.neotropic.flow.component.antvx6.events;

import com.neotropic.flow.component.antvx6.AntvX6;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

/**
* Event fired when a node background has been resized.
* @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
*/
@DomEvent("background-resized")
public class NodeBackgroundResizedEvent extends ComponentEvent<AntvX6> {
    private final String id;
    private final double width;
    private final double height;

    public NodeBackgroundResizedEvent(AntvX6 source, boolean fromClient,
                          @EventData("event.detail.node.id") String id,
                          @EventData("event.detail.node.width") double width,
                          @EventData("event.detail.node.height") double height) {
        super(source, fromClient);
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public String getId(){
        return id;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }
}
