package org.gperon.familytree.genderviewer;

import java.awt.BorderLayout;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.OutlineView;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.gperon.familytree.genderviewer//Gender//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "GenderTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "org.gperon.familytree.genderviewer.GenderTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_GenderAction",
        preferredID = "GenderTopComponent"
)
@Messages({
    "CTL_GenderAction=Gender",
    "CTL_GenderTopComponent=Gender Window",
    "HINT_GenderTopComponent=This is a Gender window",
    "HINT_FilterTextField=Provide a search string to filter names and hit <RETURN>"
})
public final class GenderTopComponent extends TopComponent implements ExplorerManager.Provider {

    private final ExplorerManager em = new ExplorerManager();

    public GenderTopComponent() {
        initComponents();
        setName(Bundle.CTL_GenderTopComponent());
        setToolTipText(Bundle.HINT_GenderTopComponent());
        OutlineView view = new OutlineView("People");
        view.setPropertyColumns(
                "gender", "Gender",
                "notes", "Notes"
        );
        add(view, BorderLayout.CENTER);
        associateLookup(ExplorerUtils.createLookup(em, this.getActionMap()));
        em.setRootContext(new RootNode());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        ExplorerUtils.activateActions(em, true);
    }

    @Override
    public void componentClosed() {
        ExplorerUtils.activateActions(em, false);
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

    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }
}
