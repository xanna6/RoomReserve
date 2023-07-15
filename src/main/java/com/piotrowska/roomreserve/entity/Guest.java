package com.piotrowska.roomreserve.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String surname;
    private String phoneNumber;
    private String mail;

    @OneToMany(mappedBy = "guest")
    private Set<RoomGuest> reservations;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<RoomGuest> getReservations() {
        return reservations;
    }

    public void setReservations(Set<RoomGuest> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
