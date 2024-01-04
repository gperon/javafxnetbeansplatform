package org.gperon.familytree.genderviewer;

import org.gperon.familytree.model.Person.Gender;
import static org.gperon.familytree.model.Person.Gender.FEMALE;
import static org.gperon.familytree.model.Person.Gender.MALE;
import static org.gperon.familytree.model.Person.Gender.UNKNOWN;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author gperon
 */
@Messages({
    "HINT_GenderNode=Gender Node"
})
public class GenderNode extends AbstractNode {

    public GenderNode(Gender gender) {
        super(Children.create(new PersonChildFactory(gender), false), Lookups.singleton(gender));
        setGenderStuff(gender);
        setShortDescription(Bundle.HINT_GenderNode());
    }

    private void setGenderStuff(Gender gender) {
        StringBuilder sb = new StringBuilder();
        StringBuilder iconString = new StringBuilder("org/gperon/familytree/genderviewer/resources/");
        switch (gender) {
            case MALE:
                sb.append("Male");
                iconString.append("maleIcon.png");
                break;
            case FEMALE:
                sb.append("Female");
                iconString.append("femaleIcon.png");
                break;
            case UNKNOWN:
                sb.append("Unknown");
                iconString.append("unknownIcon.png");
                break;
        }
        setName(sb.toString());
        setDisplayName(sb.toString());
        setIconBaseWithExtension(iconString.toString());
    }
}
