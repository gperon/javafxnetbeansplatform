/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

/**
 *
 * @author gail
 */
public class FamilyTreeManager {

    private final ObservableMap<Long, Person> observableMap = FXCollections.observableHashMap();
    private static FamilyTreeManager instance = null;

    protected FamilyTreeManager() {
        // Exists only to defeat instantiation.
    }

    public static FamilyTreeManager getInstance() {
        if (instance == null) {
            instance = new FamilyTreeManager();
        }
        return instance;
    }
    
    public void addListener(MapChangeListener<? super Long, ? super Person> ml) {
        observableMap.addListener(ml);
    }
    
    public void removeListener(MapChangeListener<? super Long, ? super Person> ml) {
        observableMap.removeListener(ml);
    }
   
    public void addListener(InvalidationListener il) {
        observableMap.addListener(il);
    }
    
    public void removeListener(InvalidationListener il) {
        observableMap.removeListener(il);
    }
    public void addPerson(Person p) {
        Person person = new Person(p);
        observableMap.put(person.getId(), person);
    }

    public void updatePerson(Person p) {
        Person person = new Person(p);
        observableMap.put(person.getId(), person);
    }

    public void deletePerson(Person p) {
        observableMap.remove(p.getId());
    }
           
    public List<Person> getAllPeople() {
        List<Person> copyList = new ArrayList<>();
        observableMap.values().stream().forEach((p) -> 
            copyList.add(new Person(p)));
        return copyList;
    }
}
