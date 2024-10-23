package org.example;

import java.io.Serializable;

public class GrandChildId implements Serializable {

    private ChildId childId;
    private String id;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
