package org.gperon.familytree.genderviewer;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle;

/**
 *
 * @author gperon
 */
@NbBundle.Messages({
    "HINT_RootNode=Show all people",
    "LBL_RootNode=People"
})
public class RootNode extends AbstractNode {

    public RootNode() {
        super(Children.create(new GenderChildFactory(), false));
        setIconBaseWithExtension("org/gperon/familytree/genderviewer/resources/personIcon.png");
        setDisplayName(Bundle.LBL_RootNode());
        setShortDescription(Bundle.HINT_RootNode());
    }

}
