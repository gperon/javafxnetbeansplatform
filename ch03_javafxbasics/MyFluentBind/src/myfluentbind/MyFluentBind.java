/*
 * @(#)MyFluentBind.java   2021-11-05
 *
 * Copyright 2021 Giorgio Peron <giorgio.peron@gmail.com>, Belluno, Italy
 * All rights reserved.
 *
 * Redistribution and use of this script, with or without modification, is
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of this script must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 *  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 *  EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 *  OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 *  OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */



/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package myfluentbind;

import javafx.application.Application;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.Stage;

/**
 *
 * @author gperon
 */
public class MyFluentBind extends Application {
    @Override
    public void start(Stage primaryStage) {
//      Button btn = new Button();
//      btn.setText("Say 'Hello World'");
//      btn.setOnAction(new EventHandler<ActionEvent>() {
//          
//          @Override
//          public void handle(ActionEvent event) {
//              System.out.println("Hello World!");
//          }
//      });
//      
//      StackPane root = new StackPane();
//      root.getChildren().add(btn);
//      
//      Scene scene = new Scene(root, 300, 250);
//      
//      primaryStage.setTitle("Hello World!");
//      primaryStage.setScene(scene);
//      primaryStage.show();
        DropShadow dropShadow = new DropShadow(10.0, Color.rgb(150, 50, 50, .688));
        dropShadow.setOffsetX(4);
        dropShadow.setOffsetY(6);
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setEffect(dropShadow);
        Rectangle rectangle = new Rectangle(200, 100, Color.LEMONCHIFFON);
        rectangle.setArcWidth(30);
        rectangle.setArcHeight(30);
        stackPane.getChildren().add(rectangle);
        Scene scene = new Scene(stackPane, 400, 200, Color.LIGHTSKYBLUE);
        primaryStage.setTitle("Fluent Binding");
        rectangle.widthProperty().bind(scene.widthProperty().divide(2));
        rectangle.heightProperty().bind(scene.heightProperty().divide(2));
        rectangle.opacityProperty().bind(
            scene.widthProperty().add(scene.heightProperty()).divide(1000));

        /*  */
        DoubleBinding opacityBinding = new DoubleBinding() {

            /* specify the dependency with super.bind() */
            {
                super.bind(scene.widthProperty(), scene.heightProperty());
            }
            @Override
            protected double computeValue() {

                /* return the computed value */
                return (scene.getWidth() + scene.getHeight()) / 1000;
            }
        };
        Rectangle rectangle2 = new Rectangle(140, 70, Color.ALICEBLUE);
        rectangle2.setArcWidth(10);
        rectangle2.setArcHeight(10);
        stackPane.getChildren().add(rectangle2);
        rectangle2.widthProperty().bind(scene.widthProperty().divide(3));
        rectangle2.heightProperty().bind(scene.heightProperty().divide(3));
        rectangle2.opacityProperty().bind(opacityBinding);
        Rectangle rectangle3 = new Rectangle(80, 50, Color.CHOCOLATE);
        rectangle3.setArcWidth(20);
        rectangle3.setArcHeight(20);
        Text text = new Text();
        text.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        stackPane.getChildren().addAll(rectangle3, text);
        rectangle3.widthProperty().bind(scene.widthProperty().divide(4));
        rectangle3.heightProperty().bind(scene.heightProperty().divide(4));

        /*  */
        DoubleBinding opacityBinding3 = new DoubleBinding() {

            /* specify the dependency with super.bind() */
            {
                super.bind(scene.widthProperty(), scene.heightProperty());
            }
            @Override
            protected double computeValue() {

                /* return the computed value */
                double opacity = (scene.getWidth() + scene.getHeight()) / 1000;

                return (opacity > 1) ? 1 : opacity;
            }
        };
        rectangle3.opacityProperty().bind(opacityBinding3);
        text.textProperty().bind(Bindings.format("opacity = %.2f", opacityBinding3));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
