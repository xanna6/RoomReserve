package com.piotrowska.roomreserve.controller;

import com.piotrowska.roomreserve.entity.Guest;
import com.piotrowska.roomreserve.entity.RoomGuest;
import com.piotrowska.roomreserve.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        model.addAttribute("roomIds", loadRoomIdList());
        return "reservations";
    }

    @GetMapping("/edit/{id}")
    public String showEditReservation(Model model, @PathVariable String id) {
        Long reservationId = Long.valueOf(id);
        RoomGuest reservation = this.reservationService.getReservationById(reservationId);
        model.addAttribute("guest", reservation.getGuest());
        model.addAttribute("reservation", reservation);
        model.addAttribute("mode", "edit");
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

    @GetMapping("/filter")
    public String getFilteredReservations(@RequestParam(name = "fromDate", defaultValue = "2023-06-01") String fromDate,
                                          @RequestParam(name = "toDate", defaultValue = "2099-12-31") String toDate,
                                          @RequestParam(name = "roomIds", required = false) List<Long> roomIds, Model model) {
        List<RoomGuest> reservations = this.reservationService.getFilteredReservations(fromDate, toDate, roomIds);
        model.addAttribute("reservations", reservations);
        model.addAttribute("roomIds", loadRoomIdList());
        return "reservations";
    }

    @GetMapping("/new")
    public String showAddReservation(Model model) {
        model.addAttribute("mode", "add");
        if (!model.containsAttribute("guest")) {
            model.addAttribute("guest", new Guest());
            return "guestData";
        } else {
            RoomGuest reservation = new RoomGuest();
            reservation.setGuest((Guest) model.getAttribute("guest"));
            model.addAttribute("reservation", reservation);
            return "editReservation";
        }
    }

    @PostMapping("/new")
    public String addReservation(RoomGuest roomGuest) {
        System.out.println(roomGuest.toString());
        this.reservationService.addReservation(roomGuest);
        return "redirect:/reservations";
    }

    private List<Long> loadRoomIdList() {
        List<Long> roomIds = new ArrayList<>();
        for (long i = 1; i <= 7; i++) {
            roomIds.add(i);
        }
        return roomIds;
    }
}
