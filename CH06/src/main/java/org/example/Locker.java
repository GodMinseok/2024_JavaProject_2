package org.example;

import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locker_id")
    private Long id;
    private String name;

//    @OneToOne(mappedBy = "locker") // 연관관계 주인이 멤버에 있다

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMember(Member member) {
    }
}
