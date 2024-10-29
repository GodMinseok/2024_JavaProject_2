package org.example;

import javax.persistence.*;

@Entity
public class Child {

    @Id @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;

//    @EmbeddedId
//    private ChildId id;
//
//    @MapsId("parentId") // ChildId.parentId 매핑
//    @ManyToOne
//    @JoinColumn(name = "PARENT_ID")
//    private Parent parent;

    private String name;
}
