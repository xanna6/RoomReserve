package com.piotrowska.roomreserve.controller;

import com.piotrowska.roomreserve.entity.RoomGuest;
import com.piotrowska.roomreserve.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReservationController {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservations")
    public String getReservations() {
        List<RoomGuest> reservations = this.reservationRepository.findAllReservations();
        for (RoomGuest rg: reservations) {
            System.out.println(rg.toString());
        }
        return "reservations";
    }
}
