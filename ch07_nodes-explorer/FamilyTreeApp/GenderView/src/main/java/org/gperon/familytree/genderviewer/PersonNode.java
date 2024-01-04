package org.gperon.familytree.genderviewer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gperon.familytree.model.Person;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author gperon
 */
@NbBundle.Messages({"HINT_PersonNode=Person"})
public class PersonNode extends AbstractNode implements PropertyChangeListener {

    private static final Logger logger = Logger.getLogger(PersonNode.class.getName());

    public PersonNode(Person person) {
        super(Children.LEAF, Lookups.singleton(person));
        setIconBaseWithExtension("org/gperon/familytree/genderviewer/resources/PersonIcon.png");
        setName(String.valueOf(person.getId()));
        setDisplayName(person.toString());
        setShortDescription(Bundle.HINT_PersonNode());
        logger.log(Level.INFO, "Creating new PersonNode for {0}", person);
    }

    public PersonNode(Children children) {
        super(children);
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
        logger.log(Level.INFO, "PropChangeListener for {0}", person);
        fireDisplayNameChange(null, getDisplayName());
    }

}
