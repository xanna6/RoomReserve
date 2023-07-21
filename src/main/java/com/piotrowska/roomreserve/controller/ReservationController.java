package com.piotrowska.roomreserve.controller;

import com.piotrowska.roomreserve.entity.RoomGuest;
import com.piotrowska.roomreserve.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReservations(Model model) {
        List<RoomGuest> reservations = this.reservationService.getAllReservations();
        for (RoomGuest rg: reservations) {
            System.out.println(rg.toString());
        }
        model.addAttribute("reservations", reservations);
        return "reservations";
    }

    @GetMapping("/edit/{id}")
    public String showEditReservation(Model model, @PathVariable String id) {
        Long reservationId = Long.valueOf(id);
        model.addAttribute("reservation", this.reservationService.getReservationById(reservationId));
        return "editReservation";
    }

    @PostMapping("/edit/{id}")
    public String editReservation(RoomGuest roomGuest) {
        this.reservationService.editReservation(roomGuest);
        return "redirect:/reservations";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public String deleteRoom(@PathVariable Long id) {
        this.reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }
}
