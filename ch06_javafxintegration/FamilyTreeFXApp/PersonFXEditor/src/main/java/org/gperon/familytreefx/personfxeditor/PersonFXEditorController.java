/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gperon.familytreefx.personfxeditor;

import org.gperon.familytree.model.FamilyTreeManager;
import org.gperon.familytree.model.Person;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.openide.LifecycleManager;
import org.openide.util.Lookup;

/**
 *
 * @author gail
 */
public class PersonFXEditorController implements Initializable {

    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField middlenameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField suffixTextField;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private RadioButton unknownRadioButton;
    @FXML
    private TextArea notesTextArea;
    @FXML
    private Button updateButton;
    private static final Logger logger = Logger.getLogger(PersonFXEditorController.class.getName());
    private FamilyTreeManager ftm = null;

    private Person thePerson = null;
    private ObjectBinding<Person.Gender> genderBinding;
    private boolean changeOK = false;
    private BooleanProperty enableUpdateProperty;

    @FXML
    private void updateButtonAction(ActionEvent event) {
        enableUpdateProperty.set(false);
        ftm.updatePerson(thePerson);
    }

    @FXML
    private void handleKeyAction(KeyEvent ke) {
        if (changeOK) {
            enableUpdateProperty.set(true);
        }
    }

    @FXML
    private void genderSelectionAction(ActionEvent event) {
        if (changeOK) {
            enableUpdateProperty.set(true);
        }
    }

    private void configureEditPanelBindings(Person p) {
        firstnameTextField.textProperty().bindBidirectional(p.firstnameProperty());
        middlenameTextField.textProperty().bindBidirectional(p.middlenameProperty());
        lastnameTextField.textProperty().bindBidirectional(p.lastnameProperty());
        suffixTextField.textProperty().bindBidirectional(p.suffixProperty());
        notesTextArea.textProperty().bindBidirectional(p.notesProperty());
    }

    private void clearForm() {
        firstnameTextField.setText("");
        middlenameTextField.setText("");
        lastnameTextField.setText("");
        suffixTextField.setText("");
        notesTextArea.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        unknownRadioButton.setSelected(false);
    }

    // This is invoked from the LookupListener in the TopComponent
    public void doUpdate(Collection<? extends Person> people) {
        enableUpdateProperty.set(false);
        changeOK = false;
        if (people.isEmpty()) {
            logger.log(Level.FINE, "No selection");
            clearForm();
            return;
        }
        thePerson = people.iterator().next();
        logger.log(Level.FINE, "{0} selected", thePerson);
        configureEditPanelBindings(thePerson);
        // set the gender from Person, then configure the genderBinding
        if (thePerson.getGender().equals(Person.Gender.MALE)) {
            maleRadioButton.setSelected(true);
        } else if (thePerson.getGender().equals(Person.Gender.FEMALE)) {
            femaleRadioButton.setSelected(true);
        } else {
            unknownRadioButton.setSelected(true);
        }
        thePerson.genderProperty().bind(genderBinding);
        changeOK = true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Change level to Level.INFO to reduce console messages
        logger.setLevel(Level.FINE);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINE);
        logger.addHandler(handler);
        try {
            FileHandler fileHandler = new FileHandler();
            // records sent to file javaN.log in user's home directory
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
            logger.log(Level.FINE, "Created File Handler");
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, "Couldn't create FileHandler", ex);
        }

        ftm = Lookup.getDefault().lookup(FamilyTreeManager.class);
        if (ftm == null) {
            logger.log(Level.SEVERE, "Cannot get FamilyTreeManager object");
            LifecycleManager.getDefault().exit();
        }

        enableUpdateProperty = new SimpleBooleanProperty(this, "enableUpdate", false);
        updateButton.disableProperty().bind(enableUpdateProperty.not());
        // the radio button custom binding
        genderBinding = new ObjectBinding<Person.Gender>() {
            {
                super.bind(maleRadioButton.selectedProperty(),
                        femaleRadioButton.selectedProperty(),
                        unknownRadioButton.selectedProperty());
            }

            @Override
            protected Person.Gender computeValue() {
                if (maleRadioButton.isSelected()) {
                    return Person.Gender.MALE;
                } else if (femaleRadioButton.isSelected()) {
                    return Person.Gender.FEMALE;
                } else {
                    return Person.Gender.UNKNOWN;
                }
            }
        };

    }
}
