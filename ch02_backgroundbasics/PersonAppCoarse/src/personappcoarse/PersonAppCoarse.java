/*
 * @(#)PersonAppCoarse.java   2014-10-02
 * 
 * Copyright (c) 2005-2014 Luxottica Group
 * All Rights Reserved.
 * This program contains proprietary and trade secret information of Luxottica Group.
 *
 *
 *
 */



/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package personappcoarse;

import com.asgteach.familytree.model.FamilyTreeManager;
import com.asgteach.familytree.model.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author GiorgioP
 */
public class PersonAppCoarse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        final PropertyChangeListener pcl = new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                System.out.println("" + evt.getPropertyName() + " for " + evt.getNewValue());
//            }
//        };
        final PropertyChangeListener pcl = (PropertyChangeEvent evt) -> {
            System.out.println("" + evt.getPropertyName() + " for " + evt.getNewValue());
        };
        FamilyTreeManager ftm = FamilyTreeManager.getInstance();
        ftm.addPropertyChangeListener(pcl);
        Person homer = new Person("Homer", "Simpson", Person.Gender.MALE);
        Person marge = new Person("Marge", "Simpson", Person.Gender.FEMALE);
        ftm.addPerson(homer);
        ftm.addPerson(marge);
        System.out.println(ftm.getAllPeople());
        homer.setMiddlename("Chester");
        homer.setSuffix("Junior");
        ftm.updatePerson(homer);
        marge.setMiddlename("Louise");
        marge.setLastname("Bouvier-Simpson");
        ftm.updatePerson(marge);
        System.out.println(ftm.getAllPeople());
        ftm.deletePerson(marge);
        System.out.println(ftm.getAllPeople());

        /* delete marge again */
        ftm.deletePerson(marge);
        ftm.removePropertyChangeListener(pcl);
    }
}
