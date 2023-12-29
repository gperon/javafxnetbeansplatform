/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gperon.familytree.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

/**
 *
 * @author gail
 */
public class FamilyTreeManager {

    private final ObservableMap<Long, Person> observableMap
            = FXCollections.observableMap(new ConcurrentHashMap<Long, Person>());
    private static FamilyTreeManager instance = null;

    protected FamilyTreeManager() {
        // Singleton class: prevent direct instantiation
    }

    // Thread-safe lazy initialization
    public synchronized static FamilyTreeManager getInstance() {
        if (instance == null) {
            instance = new FamilyTreeManager();
        }
        return instance;
    }

    public synchronized void addListener(MapChangeListener<? super Long, ? super Person> ml) {
        observableMap.addListener(ml);
    }

    public synchronized void removeListener(MapChangeListener<? super Long, ? super Person> ml) {
        observableMap.removeListener(ml);
    }

    public synchronized void addListener(InvalidationListener il) {
        observableMap.addListener(il);
    }

    public synchronized void removeListener(InvalidationListener il) {
        observableMap.removeListener(il);
    }

    public synchronized void addPerson(Person p) {
        final Person person = new Person(p);
        observableMap.put(person.getId(), person);
    }

    public void updatePerson(Person p) {
        // both addPerson and updatePerson use observableMap.put()
        addPerson(p);
    }

    public synchronized void deletePerson(Person p) {
        final Person person = new Person(p);
        observableMap.remove(person.getId());
    }

    public synchronized List<Person> getAllPeople() {
        List<Person> copyList = new ArrayList<>();
        observableMap.values().stream().forEach((p)
                -> copyList.add(new Person(p)));
        return copyList;
    }
}
