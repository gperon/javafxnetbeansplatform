/*
 * @(#)RaceTrackController.java   2021-11-05
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
package racetrackfxapp;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;

import javafx.beans.binding.When;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javafx.util.Duration;

import java.net.URL;

import java.util.ResourceBundle;

/**
 *
 * @author gperon
 */
public class RaceTrackController implements Initializable {

    /* constants to control the transaction's rate changes */
    final double maxRate = 7.0;
    final double minRate = .3;
    final double rateDelta = .3;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Path path;
    @FXML
    private Text text;
    @FXML
    private Button startPauseButton;
    @FXML
    private Button slowerButton;
    @FXML
    private Button fasterButton;
    private PathTransition pathTransition;

    @FXML
    private void startPauseAction(ActionEvent event) {
        if (pathTransition.getStatus() == Animation.Status.RUNNING) {
            pathTransition.pause();
        } else {
            pathTransition.play();
        }
    }

    @FXML
    private void slowerAction(ActionEvent event) {
        double currentRate = pathTransition.getRate();
        if (currentRate <= minRate) {
            return;
        }
        pathTransition.setRate(currentRate - rateDelta);
        System.out.printf("Slower rate = %.2f\n", pathTransition.getRate());
    }

    @FXML
    private void fasterAction(ActionEvent event) {
        double currentRate = pathTransition.getRate();
        if (currentRate >= maxRate) {
            return;
        }
        pathTransition.setRate(currentRate + rateDelta);
        System.out.printf("Faster rate = %.2f\n", pathTransition.getRate());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /* create the path transition */
        pathTransition = new PathTransition(Duration.seconds(6), path, rectangle);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Animation.INDEFINITE);
        pathTransition.setInterpolator(Interpolator.LINEAR);

        /*
         *  we count laps by noticing when the currentTimeProperty changes and the oldValue is greate rthan the newValue which is only true once per lap.
         * We then increment the lapCOunterProperty
         */
        final IntegerProperty lapCounterProperty = new SimpleIntegerProperty(0);
        pathTransition.currentTimeProperty().addListener(
            (ObservableValue<? extends Duration> ov, Duration oldValue, Duration newValue) -> {
                if (oldValue.greaterThan(newValue)) {
                    lapCounterProperty.set(lapCounterProperty.get() + 1);
                }
            } );

        /* bind the text's textProperty to the lapCOunterProperty and format it */
        text.textProperty().bind(lapCounterProperty.asString("Lap Counter: %s"));
        startPauseButton.textProperty().bind(
            new When(pathTransition.statusProperty().isEqualTo(Animation.Status.RUNNING)).then(
                "Pause").otherwise("Start"));
        fasterButton.disableProperty().bind(
            pathTransition.statusProperty().isNotEqualTo(Animation.Status.RUNNING));
        slowerButton.disableProperty().bind(
            pathTransition.statusProperty().isNotEqualTo(Animation.Status.RUNNING));
        fasterButton.setText(" >> ");
        slowerButton.setText(" << ");
    }
}
