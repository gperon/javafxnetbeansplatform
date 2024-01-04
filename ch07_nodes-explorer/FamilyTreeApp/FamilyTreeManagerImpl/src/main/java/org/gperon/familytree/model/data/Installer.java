package org.gperon.familytree.model.data;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.gperon.familytree.model.FamilyTreeManager;
import org.gperon.familytree.model.Person;
import org.openide.LifecycleManager;
import org.openide.modules.OnStart;
import org.openide.util.Lookup;

/**
 *
 * @author gperon
 */
@OnStart
public final class Installer implements Runnable {

    private static final Logger logger = Logger.getLogger(Installer.class.getName());

    @Override
    public void run() {
        FamilyTreeManager ftm = Lookup.getDefault().lookup(FamilyTreeManager.class);
        if (ftm == null) {
            logger.log(Level.SEVERE, "Cannot get FamilyTreeManager object");
            LifecycleManager.getDefault().exit();
        }
        populateMap(ftm);
    }

    private void populateMap(FamilyTreeManager ftm) {
        // put some dummy data into the FamilyTreeManager map
        ftm.addPerson(new Person("Homer", "Simpson", Person.Gender.MALE));
        ftm.addPerson(new Person("Marge", "Simpson", Person.Gender.FEMALE));
        ftm.addPerson(new Person("Bart", "Simpson", Person.Gender.MALE));
        ftm.addPerson(new Person("Lisa", "Simpson", Person.Gender.FEMALE));
        ftm.addPerson(new Person("Maggie", "Simpson", Person.Gender.FEMALE));
        logger.log(Level.INFO, "Map populated: {0}", ftm.getAllPeople());
    }
}
