/*
 * @(#)FamilyTreeManager.java   2014-10-02
 * 
 * Copyright (c) 2005-2014 Luxottica Group
 * All Rights Reserved.
 * This program contains proprietary and trade secret information of Luxottica Group.
 *
 *
 *
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

    /**
     * Method description
     *
     *
     * @return
     */
    public static FamilyTreeManager getInstance() {
        if (null == instance) {
            /* thread safe */
            instance = new FamilyTreeManager();
            instance.pcs = new PropertyChangeSupport(instance);
        }

        return instance;
    }

    /**
     * Method description
     *
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Method description
     *
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public static final String PROP_PERSON_DESTROYED = "removePerson";
    public static final String PROP_PERSON_ADDED = "addPerson";
    public static final String PROP_PERSON_UPDATED = "updatePerson";

    /**
     * Method description
     *
     *
     * @param p
     */
    public void addPerson(Person p) {
        Person person = new Person(p);
        personMap.put(person.getId(), person);
        pcs.firePropertyChange(PROP_PERSON_ADDED, null, person);
    }

    /**
     * Method description
     *
     *
     * @param p
     */
    public void updatePerson(Person p) {
        Person person = new Person(p);
        personMap.put(person.getId(), person);
        pcs.firePropertyChange(PROP_PERSON_UPDATED, null, person);
    }

    /**
     * Method description
     *
     *
     * @param p
     */
    public void deletePerson(Person p) {
        Person person = personMap.remove(p.getId());
        if (person != null) {
            pcs.firePropertyChange(PROP_PERSON_DESTROYED, null, person);
        }
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<Person> getAllPeople() {
        List<Person> copyList = new ArrayList<>();
//        for (Person person : personMap.values()) {
//            copyList.add(new Person(person));
//        }
//
//        personMap.values().forEach(new Consumer<Person>() {
//
//            @Override
//            public void accept(Person p) {
//                copyList.add(new Person(p));
//            }
//        });
        personMap.values().forEach(p -> copyList.add(new Person(p)));
        return copyList;

    }
}
