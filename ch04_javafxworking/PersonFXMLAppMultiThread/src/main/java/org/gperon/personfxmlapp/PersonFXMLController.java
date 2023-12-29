/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gperon.personfxmlapp;

import org.gperon.familytree.model.FamilyTreeManager;
import org.gperon.familytree.model.Person;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.MapChangeListener;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author gail
 */
public class PersonFXMLController implements Initializable {

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
    private TreeView<Person> personTreeView;
    @FXML
    private Button updateButton;
    @FXML
    private ProgressIndicator updateProgressIndicator;
    private static final Logger logger = Logger.getLogger(PersonFXMLController.class.getName());
    private final FamilyTreeManager ftm = FamilyTreeManager.getInstance();
    private Person thePerson = null;
    private ObjectBinding<Person.Gender> genderBinding;
    private boolean changeOK = false;
    private BooleanProperty enableUpdateProperty;
    private IntegerProperty backgroundActive = new SimpleIntegerProperty(0);

    @FXML
    private void updateButtonAction(ActionEvent event) {
        // update the family tree manager on a background thread
        final Person person = new Person(thePerson);
        enableUpdateProperty.set(false);
        backgroundActive.set(backgroundActive.get() + 1);

        Task<Person> updateTask = new Task<Person>() {
            @Override
            protected Person call() throws Exception {
                Thread.sleep(3000);
                ftm.updatePerson(person);
                return person;
            }
        };
        updateTask.setOnSucceeded((t) -> {
            backgroundActive.set(backgroundActive.get() - 1);
            logger.log(Level.FINE, "Update task finished: {0}",
                    t.getSource().getValue());
            logger.log(Level.FINE, "tasks stillInBackground = {0}",
                    backgroundActive.get());
        });
        updateTask.setOnFailed((t) -> {
            backgroundActive.set(backgroundActive.get() - 1);
            // t.getSource().getException() could be null
            logger.log(Level.WARNING, "Update task failed {0}",
                    t.getSource().getException() != null
                    ? t.getSource().getException()
                    : "Unknown failure");
            logger.log(Level.FINE, "tasks stillInBackground = {0}",
                    backgroundActive.get());
        });
        new Thread(updateTask).start();
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

    private void buildData() {
        ftm.addPerson(new Person("Homer", "Simpson", Person.Gender.MALE));
        ftm.addPerson(new Person("Marge", "Simpson", Person.Gender.FEMALE));
        ftm.addPerson(new Person("Bart", "Simpson", Person.Gender.MALE));
        ftm.addPerson(new Person("Lisa", "Simpson", Person.Gender.FEMALE));
        ftm.addPerson(new Person("Maggie", "Simpson", Person.Gender.FEMALE));
        logger.log(Level.FINE, ftm.getAllPeople().toString());
    }

    private void buildTreeView(TreeItem<Person> root) {
        // listen for changes to the familytreemanager's map
        ftm.addListener(familyTreeListener);
        // Populate the TreeView control
        ftm.getAllPeople().stream().forEach((p) -> {
            root.getChildren().add(new TreeItem<>(p));
        });
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
        updateProgressIndicator.visibleProperty().bind(backgroundActive.greaterThan(0));

        buildData();
        // Create a root node and populate the TreeView control
        TreeItem<Person> rootNode = new TreeItem<>(new Person("People", "", Person.Gender.UNKNOWN));
        buildTreeView(rootNode);
        // Configure the TreeView control
        personTreeView.setRoot(rootNode);
        personTreeView.getRoot().setExpanded(true);
        personTreeView.getSelectionModel().selectedItemProperty().addListener(treeSelectionListener);
    }
    
    // MapChangeListener when underlying FamilyTreeManager changes
    // Check to see if on FXT, if not call Platform.runLater()
    private final MapChangeListener<Long, Person> familyTreeListener = change -> {
        if (Platform.isFxApplicationThread()) {
            logger.log(Level.FINE, "Is FX Application Thread");
            updateTree(change);
        } else {
            logger.log(Level.FINE, "Is BACKGROUND Thread");
            Platform.runLater(()-> updateTree(change));
        }    
    };
    
    private void updateTree(MapChangeListener.Change<? extends Long, ? extends Person> change) {
        if (change.getValueAdded() != null) {
            logger.log(Level.FINE, "changed value = {0}", change.getValueAdded());
            // Find the treeitem that this matches and replace it
            for (TreeItem<Person> node : personTreeView.getRoot().getChildren()) {
                if (change.getKey().equals(node.getValue().getId())) {
                    // an update returns the new value in getValueAdded()
                    node.setValue(change.getValueAdded());
                    return;
                }
            }
        }        
    }

    // TreeView selected item event handler
    private final ChangeListener<TreeItem<Person>> treeSelectionListener = 
            (ov, oldValue, newValue) -> {
        TreeItem<Person> treeItem = newValue;
        logger.log(Level.FINE, "selected item = {0}", treeItem);
        enableUpdateProperty.set(false);
        changeOK = false;
        if (treeItem == null || treeItem.equals(personTreeView.getRoot())) {
            clearForm();
            return;
        }
        // set thePerson to the selected treeItem value
        thePerson = new Person(treeItem.getValue());
        logger.log(Level.FINE, "selected person = {0}", thePerson);
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
    };
}
