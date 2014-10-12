/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personappbound;

import com.asgteach.familytree.model.Person;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author GiorgioP
 */
public class PersonAppBound {

    public static void main(String[] args) {
        Person homer = new Person("Homer", "Simpson", Person.Gender.MALE);
        Person marge = new Person("Marge", "Simpson", Person.Gender.FEMALE);
        final PropertyChangeListener pcl = new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("Property " + evt.getPropertyName() + " changed for " + evt.getSource());    
            }
        };
        homer.addPropertyChangeListener(pcl);
        marge.addPropertyChangeListener(pcl);
        homer.setMiddlename("Chester");
        marge.setMiddlename("Louise");
        homer.setSuffix("Junior");
        homer.setLastname("Jones");
        System.out.println("homer = " + homer);
        System.out.println("marge = " + marge);
        homer.setMiddlename("Chester");
        homer.removePropertyChangeListener(pcl);
        marge.removePropertyChangeListener(pcl);
    }
}
