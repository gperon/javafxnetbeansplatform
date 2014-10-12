/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybind;

import javafx.scene.shape.Circle;

/**
 *
 * @author gperon
 */
public class MyBind {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Circle circle1 = new Circle(10.5);
        Circle circle2 = new Circle(15.5);
        System.out.println("circle1 = " + circle1.getRadius());
        System.out.println("circle2 = " + circle2.getRadius());
        /* bind circle1 radiuus to circle2 radius */
        circle1.radiusProperty().bind(circle2.radiusProperty());
        if (circle1.radiusProperty().isBound()) {
            System.out.println("circle1 radiusProperty is bound");
        }
        /* radius property are now the same */
        System.out.println("circle1 = " + circle1.getRadius());
        System.out.println("circle2 = " + circle2.getRadius());
        /* both radius properties will now update */
        circle2.setRadius(20.5);
        System.out.println("circle1 = " + circle1.getRadius());
        System.out.println("circle2 = " + circle2.getRadius());
        /* circle1 radius no longer bound to circle2 radius */
        circle1.radiusProperty().unbind();
        if (!circle1.radiusProperty().isBound())  {
            System.out.println("circle1 radiusPorperty is unbound");
        }
        /* radius properties are now no longer the same */
        circle2.setRadius(30.5);
        System.out.println("circle1 = " + circle1.getRadius());
        System.out.println("circle2 = " + circle2.getRadius());
        
        /* bidirectional bind */
        System.out.println("#### Bind Bidirectional ####");
        Circle circle3 = new Circle(10.5);
        Circle circle4 = new Circle(15.5);
        System.out.println("circle3 = " + circle3.getRadius());
        System.out.println("circle4 = " + circle4.getRadius());
        /* circle3 takes on value of circle2 radius */
        circle3.radiusProperty().bindBidirectional(circle4.radiusProperty());
        System.out.println("circle3 = " + circle3.getRadius());
        System.out.println("circle4 = " + circle4.getRadius());
        circle4.setRadius(20.5);
        System.out.println("circle3 = " + circle3.getRadius());
        System.out.println("circle4 = " + circle4.getRadius());
        circle3.setRadius(30.5);
        System.out.println("circle3 = " + circle3.getRadius());
        System.out.println("circle4 = " + circle4.getRadius());
        circle3.radiusProperty().unbindBidirectional(circle4.radiusProperty());
    }
    
}
