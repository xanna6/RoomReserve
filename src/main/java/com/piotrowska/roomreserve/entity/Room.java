package com.piotrowska.roomreserve.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Set;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Min(value = 1, message = "Value should be equal or grater than 1")
    @Max(value = 8 , message = "Value should be equal or less than 8")
    private int people;
    @Min(value = 0, message = "Value should be equal or grater than 0")
    @Max(value = 8 , message = "Value should be equal or less than 8")
    private int singleBed;
    @Min(value = 0, message = "Value should be equal or grater than 0")
    @Max(value = 8 , message = "Value should be equal or less than 8")
    private int doubleBed;
    @Min(value = 0, message = "Value should be equal or grater than 0")
    @Max(value = 1000, message = "Value should be equal or less than 1000")
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
