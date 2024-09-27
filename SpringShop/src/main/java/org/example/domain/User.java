package org.example.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USERTB")
public class User {

    @Id
    @Column(name="ID")
    private String id;

    @Column(name="name", nullable = false, length = 10)
    private String name;

    private String city;

    private String street;

    private Integer zipcode;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<Order>();



    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Integer getZipcode() {
        return zipcode;
    }
}
