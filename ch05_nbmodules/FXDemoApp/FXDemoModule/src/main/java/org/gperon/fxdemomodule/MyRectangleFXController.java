/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gperon.fxdemomodule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author gail
 */
public class MyRectangleFXController implements Initializable {
     
    @FXML
    Rectangle rectangle;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rectangle.setStrokeWidth(5.0);
        rectangle.setStroke(Color.GOLDENROD);
    }    
    
}
