package org.gperon.familytree.genderviewer;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gperon.familytree.model.Person.Gender;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author gperon
 */
public class GenderChildFactory extends ChildFactory<Gender> {

    private static final Logger logger = Logger.getLogger(GenderChildFactory.class.getName());

    @Override
    protected boolean createKeys(List<Gender> list) {
        list.addAll(Arrays.asList(Gender.values()));
        logger.log(Level.FINER, "createKeys called: {0}", list);
        return true;
    }

    @Override
    protected Node createNodeForKey(Gender key) {
        logger.log(Level.FINER, "createNodeForKey: {0}", key);
        return new GenderNode(key);
    }
   
}
