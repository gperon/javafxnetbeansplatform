package org.gperon.familytree.genderviewer;

import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gperon.familytree.model.FamilyTreeManager;
import org.gperon.familytree.model.Person;
import org.openide.LifecycleManager;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author gperon
 */
@NbBundle.Messages({"HINT_PersonNode=Person"})
public class PersonNode extends BeanNode<Person> implements PropertyChangeListener {

    private static final Logger logger = Logger.getLogger(PersonNode.class.getName());

    public PersonNode(Person person) throws IntrospectionException {
        super(person, Children.LEAF, Lookups.singleton(person));
        setIconBaseWithExtension("org/gperon/familytree/genderviewer/resources/PersonIcon.png");
        setName(String.valueOf(person.getId()));
        setDisplayName(person.toString());
        setShortDescription(Bundle.HINT_PersonNode());
        logger.log(Level.INFO, "Creating new PersonNode for {0}", person);
    }

    @Override
    public String getHtmlDisplayName() {
        Person person = getLookup().lookup(Person.class);
        StringBuilder sb = new StringBuilder();
        if (person == null) {
            return null;
        }
        switch (person.getGender()) {
            case MALE:
                sb.append("<font color='#5588FF'><b>| ");
                break;
            case FEMALE:
                sb.append("<font color='#FF8855'><b>* ");
                break;
            case UNKNOWN:
                sb.append("<b>? ");
                break;
        }
        sb.append(person.toString()).append("</b></font>");
        return sb.toString();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
     Person person = (Person) evt.getSource();
        FamilyTreeManager ftm = Lookup.getDefault().lookup(FamilyTreeManager.class);
        if (ftm == null) {
            logger.log(Level.SEVERE, "Cannot get FamilyTreeManager object");
            LifecycleManager.getDefault().exit();
        } else {
            ftm.updatePerson(person);
            logger.log(Level.INFO, "PropChangeListener for {0}", person);
            fireDisplayNameChange(null, getDisplayName());
        }
    }

}
