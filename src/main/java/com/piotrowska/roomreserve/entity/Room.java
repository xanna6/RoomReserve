package com.piotrowska.roomreserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Room {

    @Id
    private Long id;
    private int people;
    private int singleBed;
    private int doubleBed;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getSingleBed() {
        return singleBed;
    }

    public void setSingleBed(int singleBed) {
        this.singleBed = singleBed;
    }

    public int getDoubleBed() {
        return doubleBed;
    }

    public void setDoubleBed(int doubleBed) {
        this.doubleBed = doubleBed;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
