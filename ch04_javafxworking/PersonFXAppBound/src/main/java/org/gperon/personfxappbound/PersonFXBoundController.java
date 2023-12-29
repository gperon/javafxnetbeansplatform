/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gperon.personfxappbound;

import org.gperon.familytree.model.Person;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author gail
 */
public class PersonFXBoundController implements Initializable {

    @FXML
    private Label margeLabel;
    
    final Person marge = new Person("Marge", "Simpson", Person.Gender.FEMALE);

    @FXML
    private void changeButtonAction(ActionEvent event) {
        marge.setMiddlename("Louise");
    }

    @FXML
    private void resetButtonAction(ActionEvent event) {
        marge.setMiddlename("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // margeLabel uses binding to keep the text field synchronized 
        // with Person marge
        margeLabel.textProperty().bind(marge.fullnameProperty()); 
    }
}
