package com.hackathonNerds.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id = null;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "id")
    private List<Fulfillment> fulfillmentList;

    @OneToMany(mappedBy = "id")
    private List<Booking> bookingList;

    public Person() {

    }

    public Person(Integer id, @NotNull String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Fulfillment> getFulfillmentList() {
        return fulfillmentList;
    }

    public void setFulfillmentList(List<Fulfillment> fulfillmentList) {
        this.fulfillmentList = fulfillmentList;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }
}
