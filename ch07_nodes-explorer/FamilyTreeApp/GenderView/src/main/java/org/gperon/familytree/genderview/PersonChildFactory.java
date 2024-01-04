package org.gperon.familytree.genderview;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gperon.familytree.model.FamilyTreeManager;
import org.gperon.familytree.model.Person;
import org.openide.LifecycleManager;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.WeakListeners;

/**
 *
 * @author gperon
 */
public class PersonChildFactory extends ChildFactory<Person> {

    private final FamilyTreeManager ftm;
    private static final Logger logger = Logger.getLogger(PersonChildFactory.class.getName());

    public PersonChildFactory() {
        this.ftm = Lookup.getDefault().lookup(FamilyTreeManager.class);
        if (ftm == null) {
            logger.log(Level.SEVERE, "Cannot get FamilyTreeManager object");
            LifecycleManager.getDefault().exit();
        }
        ftm.addPropertyChangeListener(familyTreeListener);
    }

    @Override
    protected boolean createKeys(List<Person> list) {
        list.addAll(ftm.getAllPeople());
        logger.log(Level.FINER, "createKeys called: {0}", ftm.getAllPeople());
        return true;
    }

    @Override
    protected Node createNodeForKey(Person key) {
        logger.log(Level.FINER, "createNodeForKey: {0}", key);
        PersonNode node = new PersonNode(key);
        key.addPropertyChangeListener(WeakListeners.propertyChange(node, key));
        return node;
    }
    // PropertyChangeListener for FamilyTreeManager
    private final PropertyChangeListener familyTreeListener = (PropertyChangeEvent evt) -> {
        if (evt.getPropertyName().equals(FamilyTreeManager.PROP_PERSON_UPDATED)
                && evt.getNewValue() != null) {
            Person personChange = (Person) evt.getNewValue();
            logger.log(Level.INFO, "Person updated: {0}", personChange);
            this.refresh(true);
        }
    };
}
