package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String name;

    @OneToOne(mappedBy = "member")
//    @JoinColumn(name = "locker_id")
    private Locker locker;

    @ManyToMany
    @JoinTable(
            name = "MEMER_PRODUCT"
            , joinColumns = @JoinColumn(name = "member_id")
    )
    private List<Product> products = new ArrayList<Product>();

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
