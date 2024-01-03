/*
 * @(#)FamilyTreeManagerFXImpl.java   2024-01-02
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



package org.gperon.familytree.managerfximpl;

import javafx.beans.InvalidationListener;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import org.gperon.familytree.model.FamilyTreeManager;
import org.gperon.familytree.model.Person;

import org.openide.util.lookup.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gperon
 */
@ServiceProvider(service = FamilyTreeManager.class)
public class FamilyTreeManagerFXImpl implements FamilyTreeManager {
    private final ObservableMap<Long, Person> observableMap = FXCollections.observableHashMap();

    @Override
    public void addListener(MapChangeListener<? super Long, ? super Person> ml) {
        observableMap.addListener(ml);
    }

    @Override
    public void removeListener(MapChangeListener<? super Long, ? super Person> ml) {
        observableMap.removeListener(ml);
    }

    @Override
    public void addListener(InvalidationListener il) {
        observableMap.addListener(il);
    }

    @Override
    public void removeListener(InvalidationListener il) {
        observableMap.removeListener(il);
    }

    @Override
    public void addPerson(Person p) {
        Person person = new Person(p);
        observableMap.put(person.getId(), person);
    }

    @Override
    public void updatePerson(Person p) {
        Person person = new Person(p);
        observableMap.put(person.getId(), person);
    }

    @Override
    public void deletePerson(Person p) {
        observableMap.remove(p.getId());
    }

    @Override
    public List<Person> getAllPeople() {
        List<Person> copyList = new ArrayList<>();
        observableMap.values().stream().forEach((p) -> copyList.add(new Person(p)));

        return copyList;
    }
}
