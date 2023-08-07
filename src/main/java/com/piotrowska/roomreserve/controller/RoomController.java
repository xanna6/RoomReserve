package com.piotrowska.roomreserve.controller;

import com.piotrowska.roomreserve.entity.Room;
import com.piotrowska.roomreserve.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String getRooms(Model model) {
        model.addAttribute("roomList", this.roomService.getAllRooms());
        return "rooms";
    }

    @GetMapping("/edit/{id}")
    public String showEditRoom(Model model, @PathVariable String id) {
        Long roomId = Long.valueOf(id);
        model.addAttribute("room",this.roomService.getRoomById(roomId));
        model.addAttribute("mode", "edit");
        return "editRoom";
    }

    @PostMapping("/edit/{id}")
    public String editRoom(Room room) {
        System.out.println(room.toString());
        this.roomService.editRoom(room);
        return "redirect:/rooms";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public String deleteRoom(@PathVariable Long id, RedirectAttributes model) {
        boolean deleted = this.roomService.deleteRoom(id);
        if (deleted) {
            model.addFlashAttribute("message", "Successfully deleted room");
        } else {
            model.addFlashAttribute("message", "Could not delete room, because there are reservations for this room");
        }
        return "redirect:/rooms";
    }

    @GetMapping("/new")
    public String showAddRoom(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("mode", "add");
        return "editRoom";
    }

    @PostMapping("/new")
    public String addRoom(RedirectAttributes model, Room room) {
        System.out.println(room.toString());
        this.roomService.addRoom(room);
        model.addFlashAttribute("message", "Successfully added room");
        return "redirect:/rooms";
    }

    @GetMapping("/search")
    public String getFilteredReservations(@RequestParam(name = "fromDate") String fromDate,
                                          @RequestParam(name = "toDate") String toDate,
                                          @RequestParam(name = "numberOfAdults") int numberOfAdults,
                                          @RequestParam(name = "numberOfChildren") int numberOfChildren,
                                          Model model) {
        List<Room> roomList = this.roomService.getAvailableRooms(fromDate, toDate, numberOfAdults, numberOfChildren);
        model.addAttribute("roomList", roomList);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("numberOfAdults", numberOfAdults);
        model.addAttribute("numberOfChildren", numberOfChildren);
        return "rooms";
    }
}
