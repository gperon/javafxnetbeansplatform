package org.gperon.familytree.genderview;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

    public PersonNode(Person person) {
        super(Children.LEAF, Lookups.singleton(person));
        setIconBaseWithExtension("org/gperon/familytree/genderview/resources/PersonIcon.png");
        setName(String.valueOf(person.getId()));
        setDisplayName(person.toString());
        setShortDescription(Bundle.HINT_PersonNode());
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
        sb.append("<font color='#5588FF'><b>");
        switch (person.getGender()) {
            case MALE:
                sb.append("| ");
                break;
            case FEMALE:
                sb.append("* ");
                break;
            case UNKNOWN:
                sb.append("? ");
                break;
        }
        sb.append(person.toString()).append("</b></font>");
        return sb.toString();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        fireDisplayNameChange(null, getDisplayName());
    }
    
}
