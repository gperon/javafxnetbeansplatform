package org.gperon.familytree.genderviewer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gperon.familytree.model.FamilyTreeManager;
import org.gperon.familytree.model.Person;
import org.openide.ErrorManager;
import org.openide.LifecycleManager;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Lookup;
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
        setIconBaseWithExtension("org/gperon/familytree/genderviewer/resources/personIcon.png");
        setName(String.valueOf(person.getId()));
        setDisplayName(person.toString());
        setShortDescription(Bundle.HINT_PersonNode());
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

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Person person = getLookup().lookup(Person.class);

        // create a property set for the names (first, middle, last, suffix)
        Sheet.Set setNames = Sheet.createPropertiesSet();
        setNames.setDisplayName("Names");

        // create a property set read-only id
        Sheet.Set readOnlySet = new Sheet.Set();
        readOnlySet.setDisplayName("Identification");
        // put it under its own tab
        readOnlySet.setValue("tabName", " Id Info ");

        // create a property set for gender and notes
        Sheet.Set infoSet = new Sheet.Set();
        infoSet.setName("Additional Information");

        sheet.put(setNames);
        sheet.put(infoSet);
        sheet.put(readOnlySet);

        try {
            // create read-only property support for the Names
            Property<String> firstnameProp = new PropertySupport.ReadOnly<String>(
                    "firstname", // Name of the property 
                    String.class, // Type of property value 
                    "first name", // Display name
                    "The person's first name") { // Description                   
                @Override
                public String getValue() throws IllegalAccessException, InvocationTargetException {
                    return person.getFirstname();
                }
            };

            Property<String> middlenameProp = new PropertySupport.ReadOnly<String>(
                    "middlename", // Name of the property 
                    String.class, // Type of property value 
                    "middle name", // Display name
                    "The person's middle name") { // Description                   
                @Override
                public String getValue() throws IllegalAccessException, InvocationTargetException {
                    return person.getMiddlename();
                }
            };
            Property<String> lastnameProp = new PropertySupport.ReadOnly<String>(
                    "lastname", // Name of the property 
                    String.class, // Type of property value 
                    "last name", // Display name
                    "The person's last name") { // Description                   
                @Override
                public String getValue() throws IllegalAccessException, InvocationTargetException {
                    return person.getLastname();
                }
            };
            Property<String> suffixProp = new PropertySupport.ReadOnly<String>(
                    "suffix", // Name of the property 
                    String.class, // Type of property value 
                    "name suffix", // Display name
                    "The person's name suffix") { // Description                   
                @Override
                public String getValue() throws IllegalAccessException, InvocationTargetException {
                    return person.getSuffix();
                }
            };

            setNames.put(firstnameProp);
            setNames.put(middlenameProp);
            setNames.put(lastnameProp);
            setNames.put(suffixProp);

            // create read-only property support for gender 
            Property<Person.Gender> genderProp = new PropertySupport.ReadOnly<Person.Gender>(
                    "gender", // Name of the property 
                    Person.Gender.class, // Type of property value 
                    "gender", // Display name
                    "The person's gender") { // Description                   
                @Override
                public Person.Gender getValue() throws IllegalAccessException, InvocationTargetException {
                    return person.getGender();
                }
            };
            // create read-write property support for notes
            Property<String> notesProp = new PropertySupport.Reflection<String>(person, String.class, "notes");

            infoSet.put(genderProp);
            infoSet.put(notesProp);

            // create read-only property support for id (the setter is null)
            Property<Long> idProp = new PropertySupport.Reflection<Long>(person, Long.class, "getId", null);
            readOnlySet.put(idProp);
        } catch (NoSuchMethodException ex) {
            ErrorManager.getDefault();
        }
        return sheet;
    }
}
