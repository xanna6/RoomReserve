package com.piotrowska.roomreserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

    @GetMapping("/reservations")
    public String getReservations() {
        return "reservations";
    }
}
