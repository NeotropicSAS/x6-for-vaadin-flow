package com.neotropic.flow.component.antx6;

import com.neotropic.flow.component.antvx6.AntvX6;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

/**
 * Test View for our {@link AntvX6} add-on class. This class and others in the
 * test folder will not be included in the final JAR.
 */
@Route("")
public class TestView extends Div {

    public TestView() {
        add(new AntvX6());
    }
}
