package org.example;

import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locker_id")
    private Long id;
    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

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
}