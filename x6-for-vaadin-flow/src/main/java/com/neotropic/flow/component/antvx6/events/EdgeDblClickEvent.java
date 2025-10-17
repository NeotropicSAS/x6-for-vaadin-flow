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
* Event triggered when an edge was double-clicked.
* @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
*/
@DomEvent("edge-dblclick")
public class EdgeDblClickEvent extends ComponentEvent<AntvX6> {
    private final String edgeId;
    private final String idSource;
    private final String idTarget;

    public EdgeDblClickEvent(AntvX6 source, boolean fromClient,
                             @EventData("event.detail.edge.id") String edgeId,
                             @EventData("event.detail.edge.idSource") String idSource,
                             @EventData("event.detail.edge.idTarget") String idTarget) {
        super(source, fromClient);
        this.edgeId = edgeId;
        this.idSource = idSource;
        this.idTarget = idTarget;
    }

    public String getEdgeId() {
        return edgeId;
    }

    public String getIdSource() {
        return idSource;
    }

    public String getIdTarget() {
        return idTarget;
    }

}
