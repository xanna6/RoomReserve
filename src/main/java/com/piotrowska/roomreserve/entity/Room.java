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

}
