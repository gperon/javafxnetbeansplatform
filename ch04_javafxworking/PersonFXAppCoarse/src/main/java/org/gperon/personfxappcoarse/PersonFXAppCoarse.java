/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gperon.personfxappcoarse;

import com.asgteach.familytree.model.FamilyTreeManager;
import com.asgteach.familytree.model.Person;
import javafx.collections.MapChangeListener;

/**
 *
 * @author gail
 */
public class PersonFXAppCoarse {

    public static void main(String[] args) {

        final FamilyTreeManager ftm = FamilyTreeManager.getInstance();

        // Attach a change listener to FamilyTreeManager
        ftm.addListener(mapChangeListener);

        final Person homer = new Person("Homer", "Simpson", Person.Gender.MALE);
        final Person marge = new Person("Marge", "Simpson", Person.Gender.FEMALE);

        // Add Person objects
        ftm.addPerson(homer);
        ftm.addPerson(marge);
        // add marge again, no change event
        ftm.addPerson(marge);

        homer.setMiddlename("Chester");
        homer.setSuffix("Junior");
        // Update homer
        ftm.updatePerson(homer);
        marge.setMiddlename("Louise");
        marge.setLastname("Bouvier-Simpson");
        // Update marge
        ftm.updatePerson(marge);
        ftm.deletePerson(marge);
        // delete marge again, no change event
        ftm.deletePerson(marge);
    }

    // MapChangeListener<Long, Person> for FamilyTreeManager
    private static final MapChangeListener<Long, Person> mapChangeListener = (change) -> {
        if (change.wasAdded() && change.wasRemoved()) {
            System.out.println("\tUPDATED");
        } else if (change.wasAdded()) {
            System.out.println("\tADDED");
        } else if (change.wasRemoved()) {
            System.out.println("\tREMOVED");
        }
        System.out.println("\tmap = " + change.getMap());
        System.out.println("\t\t" + change.getValueAdded()
                + " was added [" + change.getKey() + "].");
        System.out.println("\t\t" + change.getValueRemoved()
                + " was removed [" + change.getKey() + "].");
    };
}
