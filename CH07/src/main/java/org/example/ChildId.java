package org.example;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChildId implements Serializable {

    public String parentId;

    @Column(name = "CHILD_ID")
    private String id;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public ChildId() {

    }
}
