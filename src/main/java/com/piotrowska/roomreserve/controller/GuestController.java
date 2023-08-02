package com.piotrowska.roomreserve.controller;

import com.piotrowska.roomreserve.entity.Guest;
import com.piotrowska.roomreserve.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String GetGuest(RedirectAttributes model, @RequestParam(name = "firstname") String firstname, @RequestParam(name = "surname") String surname,
                           @RequestParam(name = "mail") String mail, @RequestParam(name = "phoneNumber") String phoneNumber) {
        Guest guest = this.guestService.findGuest(firstname, surname, phoneNumber, mail);
        System.out.println(guest);
        model.addFlashAttribute("guest", guest);
        return "redirect:/reservations/new";
    }
}
