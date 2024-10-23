package org.example;

import javax.persistence.*;

@Entity
@IdClass(ChildId.class)
public class Child {

    @EmbeddedId
    private ChildId id;

    @MapsId("parentId") // ChildId.parentId 매핑
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    private String name;
}
