/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/NetBeansModuleDevelopment-files/templateTopComponent637.java to edit this template
 */
package org.gperon.familytreefx.personfxeditor;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.gperon.familytree.model.Person;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.gperon.familytreefx.personfxeditor//PersonFXEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "PersonFXEditorTopComponent",
        iconBase = "org/gperon/familytreefx/personfxeditor/personIcon.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "org.gperon.familytreefx.personfxeditor.PersonFXEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_PersonFXEditorAction",
        preferredID = "PersonFXEditorTopComponent"
)
@Messages({
    "CTL_PersonFXEditorAction=PersonFXEditor",
    "CTL_PersonFXEditorTopComponent=PersonFXEditor Window",
    "HINT_PersonFXEditorTopComponent=This is a PersonFXEditor window"
})
public final class PersonFXEditorTopComponent extends TopComponent {

    private Lookup.Result<Person> lookupPerson = null;
    private PersonFXEditorController controller;
    private static final Logger logger = Logger.getLogger(PersonFXEditorTopComponent.class.getName());

    public PersonFXEditorTopComponent() {
        initComponents();
        setName(Bundle.CTL_PersonFXEditorTopComponent());
        setToolTipText(Bundle.HINT_PersonFXEditorTopComponent());
        init();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fxPanel = new javafx.embed.swing.JFXPanel();

        setLayout(new java.awt.BorderLayout());
        add(fxPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javafx.embed.swing.JFXPanel fxPanel;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        lookupPerson = Utilities.actionsGlobalContext().lookupResult(Person.class);
        lookupPerson.addLookupListener(lookupListener);
        checkLookup();
    }

    @Override
    public void componentClosed() {
        lookupPerson.removeLookupListener(lookupListener);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    private void init() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> createScene());
    }

    private void createScene() {
        try {
            URL location = getClass().getResource("PersonFXEditor.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = (Parent) fxmlLoader.load(location.openStream());
            Scene scene = new Scene(root);
            fxPanel.setScene(scene);
            controller = (PersonFXEditorController) fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    LookupListener lookupListener = ((le) -> {
        checkLookup();
    });

    private void checkLookup() {
        TopComponent tc = TopComponent.getRegistry().getActivated();
        if (tc != null && tc.equals(this)) {
            logger.log(Level.INFO, "PersonFXEditorTopComponent has focus");
            return;
        }
        Collection<? extends Person> allPeople = lookupPerson.allInstances();
        if (Platform.isFxApplicationThread()) {
            System.out.println("Already in JavaFX Application Thread");
            controller.doUpdate(allPeople);
        } else {
            System.out.println("NOT in JavaFX Application Thread");
            Platform.runLater(() -> controller.doUpdate(allPeople));

        }
    }
}
