/*
 * @(#)FamilyTreeManager.java   2023-12-29
 * 
 * Copyright 2024 Giorgio Peron <giorgio.peron@gmail.com>, Belluno, Italy
 * All rights reserved.
 *
 * Redistribution and use of this script, with or without modification, is
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of this script must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 *  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 *  EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 *  OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 *  OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */



package com.asgteach.familytree.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.swing.event.SwingPropertyChangeSupport;

/**
 *
 * @author GiorgioP
 */
public class FamilyTreeManager {
    private static FamilyTreeManager instance = null;
    public static final String PROP_PERSON_DESTROYED = "removePerson";
    public static final String PROP_PERSON_ADDED = "addPerson";
    public static final String PROP_PERSON_UPDATED = "updatePerson";
    private final ConcurrentMap<Long, Person> personMap = new ConcurrentHashMap<>();
    private SwingPropertyChangeSupport pcs = null;

    protected FamilyTreeManager() {

        /* exist only to defect instantiation */
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

    /**
     * Method description
     *
     *
     * @param p
     */
    public void addPerson(Person p) {
        Person person;
        synchronized (this) {
            person = new Person(p);
            personMap.put(person.getId(), person);
        }
        pcs.firePropertyChange(PROP_PERSON_ADDED, null, person);
    }

    /**
     * Method description
     *
     *
     * @param p
     */
    public void updatePerson(Person p) {
        Person person;
        synchronized (this) {
            person = new Person(p);
            personMap.put(person.getId(), person);
        }
        pcs.firePropertyChange(PROP_PERSON_UPDATED, null, person);
    }

    /**
     * Method description
     *
     *
     * @param p
     */
    public void deletePerson(Person p) {
        Person person;
        synchronized (this) {
            person = personMap.remove(p.getId());
            if (person != null) {
                pcs.firePropertyChange(PROP_PERSON_DESTROYED, null, person);
            }
        }
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public synchronized static FamilyTreeManager getInstance() {
        if (null == instance) {
            instance = new FamilyTreeManager();
            instance.pcs = new SwingPropertyChangeSupport(instance);
        }

        return instance;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public synchronized List<Person> getAllPeople() {
        List<Person> copyList = new ArrayList<>();
//      for (Person person : personMap.values()) {
//          copyList.add(new Person(person));
//      }
//
//      personMap.values().forEach(new Consumer<Person>() {
//
//          @Override
//          public void accept(Person p) {
//              copyList.add(new Person(p));
//          }
//      });
        personMap.values().forEach(p -> copyList.add(new Person(p)));

        return copyList;
    }
}
