package org.example;

import javax.persistence.*;

@Entity
public class Parent {


    @Id @Column(name = "PARENT_ID")
    private String id;

    private String name;

    @OneToOne
    @JoinTable(name = "PARENT_CHILD",
            joinColumns = @JoinColumn(name = "PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHILD_ID"))
    private Child child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}


//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;

//@Entity
//public class Parent {
//
//    @EmbeddedId
//    private ParentId parentId;
//    private String name;
//
//    public ParentId getParentId() {
//        return parentId;
//    }
//
//    public void setParentId(ParentId parentId) {
//        this.parentId = parentId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//}

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.IdClass;

//@Entity
//@IdClass(ParentId.class)
//public class Parent {
//
//    @Id
//    @Column(name = "PARENT_ID1")
//    private String id1;
//
//    @Id
//    @Column(name = "PARENT_ID2")
//    private String id2;
//
//    public String getId1() {
//        return id1;
//    }
//
//    public void setId1(String id1) {
//        this.id1 = id1;
//    }
//
//    public String getId2() {
//        return id2;
//    }
//
//    public void setId2(String id2) {
//        this.id2 = id2;
//    }
//}
