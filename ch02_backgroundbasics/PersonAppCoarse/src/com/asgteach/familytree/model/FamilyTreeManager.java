/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author GiorgioP
 */
public class FamilyTreeManager {
    private final Map<Long, Person> personMap = new HashMap<>();
    private PropertyChangeSupport pcs = null;
    private static FamilyTreeManager instance = null;

    protected FamilyTreeManager() {
        /* exist only to defect instantiation */
    }
    
    public static FamilyTreeManager getInstance () {
        if (null==instance) {
            instance = new FamilyTreeManager();
            instance.pcs = new PropertyChangeSupport(instance);
        }
        return instance;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    
    private static final String PROP_PERSON_DESTROYED = "removePerson";
    private static final String PROP_PERSON_ADDED = "addPerson";
    private static final String PROP_PERSON_UPDATED = "updatePerson";
    
    public void addPerson(Person p) {
        Person person = new Person(p);
        personMap.put(person.getId(), person);
        pcs.firePropertyChange(PROP_PERSON_ADDED, null, person);
    }
    public void updatePerson(Person p) {
        Person person = new Person(p);
        personMap.put(person.getId(), person);
        pcs.firePropertyChange(PROP_PERSON_UPDATED, null, person);
    }
    public void deletePerson(Person p) {
        Person person = personMap.remove(p.getId());
        if (person!=null) {
        pcs.firePropertyChange(PROP_PERSON_DESTROYED, null, person);
        }
    }
    public List<Person> getAllPeople() {
        List<Person> copyList = new ArrayList<>();
        for (Person person : personMap.values()) {
            copyList.add(new Person(person));
        }
        return copyList;
    }
}
