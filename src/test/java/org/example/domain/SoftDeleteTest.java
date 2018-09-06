package org.example.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import io.ebean.Ebean;
import org.junit.Test;

public class SoftDeleteTest {

    /**
     * Build a one-to-one relationship with a parent to a child
     */
    @Test
    public void testSoftDelete_refreshByRequery() {
        // Insert + link records
        Parent parent = new Parent();
        parent.save();

        Child child = new Child(parent);
        child.save();

        verifyBeforeDelete(parent, child);

        Ebean.delete(parent);

        Child refreshedChild = Child.find.byId(child.getId());
        verifyAfterDelete(refreshedChild);
    }

    @Test
    public void testSoftDelete_refreshByRefreshMethod() {
        // Insert + link records
        Parent parent = new Parent();
        parent.save();

        Child child = new Child(parent);
        child.save();

        verifyBeforeDelete(parent, child);

        Ebean.delete(parent);

        child.refresh();
        verifyAfterDelete(child);
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

    private void verifyAfterDelete(Child refreshedChild) {

        assertThat("Child should be found after delete",
            refreshedChild,
            not(nullValue())
        );

        // This assert should pass
        // But instead, child.getParent() is not null and
        // child.getParent().isDeleted() is true
        assertThat("Child should not load parent since parent is deleted",
            refreshedChild.getParent(),
            is(nullValue())
        );
    }
}
