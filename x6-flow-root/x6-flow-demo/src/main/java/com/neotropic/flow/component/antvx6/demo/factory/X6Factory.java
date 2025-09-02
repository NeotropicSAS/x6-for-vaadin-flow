package com.neotropic.flow.component.antvx6.demo.factory;

import com.neotropic.flow.component.antvx6.AntvX6;
import com.neotropic.flow.component.antvx6.constants.X6Constants;
import org.springframework.stereotype.Service;

/**
* @author Julian David Camacho Erazo {@literal <julian.camacho@kuwaiba.org>}
*/
@Service
public class X6Factory {

    public X6Factory() {}

    //create the canvas
    private AntvX6 createCanvas(int graphType, int width, int height, String bgColor) {
        //Create the canvas
        AntvX6 x6Canvas = new AntvX6();
        //Graph type (0 = basic canvas, 1 = interactions canvas)
        x6Canvas.setGraphType(graphType);
        //Dimensions
        x6Canvas.setGraphWidth(width);
        x6Canvas.setGraphHeight(height);
        //Color
        x6Canvas.setBackgroundColor(bgColor);
        //Another configuration
        x6Canvas.setGrid(true); 
        return x6Canvas;
    }

    //basic canvas its value is 0 (GRAPH_TYPE)
    public AntvX6 getBasicCanvas(int width, int height, String bgColor) {
        return createCanvas(X6Constants.BASIC_GRAPH_TYPE, width, height, bgColor);
    }

    //interactions canvas its value is 1 (GRAPH_TYPE)
    public AntvX6 getInteractionsCanvas(int width, int height, String bgColor) {
        return createCanvas(X6Constants.INTERACTIONS_GRAPH_TYPE, width, height, bgColor);
    } 
}

