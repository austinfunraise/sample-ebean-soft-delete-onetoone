package org.example.domain;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.SoftDelete;
import io.ebean.annotation.Where;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Parent extends Model {

    private static final long serialVersionUID = 4116545858939406149L;

    @Id
    private Long id;

    @SoftDelete
    private boolean deleted;

    @OneToOne(mappedBy = "parent")
    //Does not work
//    @Where(clause = "deleted=false")
    private Child child;

    public static Finder<Long, Parent> find = new Finder<>(Parent.class);

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

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

}
