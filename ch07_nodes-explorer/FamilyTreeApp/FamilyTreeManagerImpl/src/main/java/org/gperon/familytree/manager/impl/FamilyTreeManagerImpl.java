/*
 * @(#)FamilyTreeManagerImpl.java   2023-12-30
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



package org.gperon.familytree.manager.impl;

import org.gperon.familytree.model.FamilyTreeManager;
import org.gperon.familytree.model.Person;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author gperon
 */
@ServiceProvider(service = FamilyTreeManager.class)
public class FamilyTreeManagerImpl implements FamilyTreeManager {
    private final Map<Long, Person> personMap = new HashMap<>();
    private PropertyChangeSupport propChangeSupport = null;

    public FamilyTreeManagerImpl() {
        // Exists only to defeat instantiation.
    }

    private PropertyChangeSupport getPropertyChangeSupport() {
        if (propChangeSupport==null)  {
            propChangeSupport= new PropertyChangeSupport(this);
        }
        return propChangeSupport;
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().removePropertyChangeListener(listener);
    }

    @Override
    public void addPerson(Person p) {
        Person person = new Person(p);
        personMap.put(person.getId(), person);
        getPropertyChangeSupport().firePropertyChange(PROP_PERSON_ADDED, null, person);
    }

    @Override
    public void updatePerson(Person p) {
        Person person = new Person(p);
        personMap.put(person.getId(), person);
        getPropertyChangeSupport().firePropertyChange(PROP_PERSON_UPDATED, null, person);
    }

    @Override
    public void deletePerson(Person p) {
        Person person = personMap.remove(p.getId());
        if (person != null) {
            getPropertyChangeSupport().firePropertyChange(PROP_PERSON_DESTROYED, null, person);
        }
    }

    @Override
    public List<Person> getAllPeople() {
        List<Person> copyList = new ArrayList<>();
        // Use lambdas
        personMap.values().forEach(p -> copyList.add(new Person(p)));

        return copyList;
    }

}
