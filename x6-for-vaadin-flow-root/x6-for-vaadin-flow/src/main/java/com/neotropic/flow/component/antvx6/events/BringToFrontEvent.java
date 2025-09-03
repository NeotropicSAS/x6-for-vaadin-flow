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
* Event fired when a cell increases its z-index.
* @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
*/
@DomEvent("bring-to-front")
public class BringToFrontEvent extends ComponentEvent<AntvX6> {
    private final String id;
    private final int zIndex;

    public BringToFrontEvent(AntvX6 source, boolean fromClient,
                            @EventData("event.detail.cell.id") String id,
                            @EventData("event.detail.cell.zIndex") int zIndex){
        super(source, fromClient);
        this.id = id;
        this.zIndex = zIndex;
    }

    public String getId() {
        return id;
    }

    public int getZIndex(){
        return zIndex;
    }
}
