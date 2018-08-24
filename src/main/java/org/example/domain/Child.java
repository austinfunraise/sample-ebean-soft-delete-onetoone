package org.example.domain;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.SoftDelete;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Child extends Model {

    private static final long serialVersionUID = 738194912181571389L;

    @Id
    private Long id;

    @SoftDelete
    private boolean deleted;

    @OneToOne
    private Parent parent;

    public static Finder<Long, Child> find = new Finder<>(Child.class);

    public Child() {}

    public Child(Parent parent) {
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
