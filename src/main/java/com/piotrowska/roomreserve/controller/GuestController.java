package com.piotrowska.roomreserve.controller;

import com.piotrowska.roomreserve.entity.Guest;
import com.piotrowska.roomreserve.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/guest")
public class GuestController {

    private GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping("/save")
    public String GetGuest(RedirectAttributes model, Guest guest) {
        Guest savedGuest = this.guestService.findOrInsertGuest(guest);
        System.out.println(savedGuest);
        model.addFlashAttribute("guest", savedGuest);
        return "redirect:/reservations/new";
    }
}
