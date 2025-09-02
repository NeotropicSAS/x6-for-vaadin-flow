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

package com.neotropic.flow.component.antvx6.demo;

import com.neotropic.flow.component.antvx6.demo.components.HeaderComponent;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
 */
@Route
public class MainView extends VerticalLayout {

    private static final String HEADER_TEXT = "Vaddin Flow X6 Examples";
    
    public MainView(){
        initComponents();
    }  
    
    private void initComponents(){
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.START); 
        
        // Header of the View
        createHeader();
        
        //Basic example
        createBasicExamples();
        //Event examples
        createEventExamples();
        //UI components examples
        createUIComponentsExamples();
    }
    
    private void createHeader(){
        HeaderComponent header = new HeaderComponent(HEADER_TEXT, null);
        add(header);
    }
    
    private void createBasicExamples(){
        OrderedList basicExamples = new OrderedList();

        Anchor canvas = new Anchor("/basic/examples/canvas", "Canvas");
        canvas.setTarget("_blank");
        
        Anchor nodes = new Anchor("/basic/examples/nodes", "Nodes");
        nodes.setTarget("_blank");
        
        Anchor edges = new Anchor("/basic/examples/edges", "Edges");
        edges.setTarget("_blank");
        
        Anchor styles = new Anchor("/basic/examples/styles", "Styles");
        styles.setTarget("_blank");
        
        basicExamples.add(new ListItem(canvas));
        basicExamples.add(new ListItem(nodes));
        basicExamples.add(new ListItem(edges));
        basicExamples.add(new ListItem(styles));

        add(new H3("Basic"), basicExamples);
    }
    
    private void createEventExamples(){
        OrderedList eventExamples = new OrderedList();

        Anchor events = new Anchor("/examples/events", "Events");
        events.setTarget("_blank");
        
        eventExamples.add(new ListItem(events));
        
        add(new H3("Events"), eventExamples);
    }
    
    private void createUIComponentsExamples(){
        OrderedList UIComponents = new OrderedList();

        Anchor tools = new Anchor("/UI/components/examples/tools", "Tools");
        tools.setTarget("_blank");
        
        Anchor plugins = new Anchor("/UI/components/examples/plugins", "Plugins");
        plugins.setTarget("_blank");
        
        UIComponents.add(new ListItem(tools));
        UIComponents.add(new ListItem(plugins));
        
        add(new H3("UI Components"), UIComponents);
    }
}

