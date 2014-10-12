/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myinvalidationlistener;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Circle;

/**
 *
 * @author gperon
 */
public class MyInvalidationListener {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* define some circles */
        final Circle circle1 = new Circle(10.5);
        final Circle circle2 = new Circle(15.5);
        /* add an invalidation listener to circles2's radius property */
        circle2.radiusProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                System.out.println("Invalidation detected for " + o);
                System.out.println("new value = " + ((ObservableValue<Number>)o).getValue().doubleValue());
                circle1.setRadius(circle2.getRadius());
            }
        });
        System.out.println("circle1 = " + circle1.getRadius());
        System.out.println("circle2 = " + circle2.getRadius());
        circle2.setRadius(20.5);
        System.out.println("circle1 = " + circle1.getRadius());
        System.out.println("circle2 = " + circle2.getRadius());
        
    }
    
}
