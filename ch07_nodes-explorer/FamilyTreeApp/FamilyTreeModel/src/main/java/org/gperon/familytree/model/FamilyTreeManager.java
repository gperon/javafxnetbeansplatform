/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gperon.familytree.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gail
 */
public interface FamilyTreeManager {

    // FamilyTreeManager property change names
    public static final String PROP_PERSON_DESTROYED = "removePerson";
    public static final String PROP_PERSON_ADDED = "addPerson";
    public static final String PROP_PERSON_UPDATED = "updatePerson";
    
    
    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

    
    public void addPerson(Person p);

    public void updatePerson(Person p);

    public void deletePerson(Person p);
    
    public List<Person> getAllPeople();
}
