package org.example.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import io.ebean.Ebean;
import org.junit.Test;

public class SoftDeleteTest {

    /**
     * Build a one-to-one relationship with a parent to a child
     */
    @Test
    public void testSoftDelete() {
        // Insert + link records
        Parent parent = new Parent();
        parent.save();

        Child child = new Child(parent);
        child.save();

        verifyBeforeDelete(parent, child);

        Ebean.delete(child);

        verifyAfterDelete(parent, child);
    }

    private void verifyBeforeDelete(Parent parent, Child child) {
        assertThat("Parent should be linked to child before delete",
            Parent.find.byId(parent.getId()).getChild().getId(),
            is(child.getId())
        );

        assertThat("Child should be linked to parent before delete",
            Child.find.byId(child.getId()).getParent().getId(),
            is(parent.getId())
        );
    }

    private void verifyAfterDelete(Parent parent, Child child) {
        // After delete, finding child by id should return null
        assertThat("Child should not be found after delete",
            Child.find.byId(child.getId()),
            is(nullValue())
        );

        // After delete, getting linked child from parent should return null
        assertThat("Parent should not be linked to child since child was deleted",
            Parent.find.byId(parent.getId()).getChild(),
            is(nullValue())
        );
    }
}
