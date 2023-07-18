package com.piotrowska.roomreserve.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private int people;
    private int singleBed;
    private int doubleBed;
    private int price;

    @OneToMany(mappedBy = "room")
    private Set<RoomGuest> reservations;

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

    public Set<RoomGuest> getReservations() {
        return reservations;
    }

    public void setReservations(Set<RoomGuest> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", people=" + people +
                ", singleBed=" + singleBed +
                ", doubleBed=" + doubleBed +
                ", price=" + price +
                ", reservations=" + reservations +
                '}';
    }
}
