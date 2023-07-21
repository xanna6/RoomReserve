package com.piotrowska.roomreserve.controller;

import com.piotrowska.roomreserve.entity.Room;
import com.piotrowska.roomreserve.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public String getRooms(Model model) {
        model.addAttribute("roomList", this.roomService.getAllRooms());
        return "rooms";
    }

    @GetMapping("/rooms/edit/{id}")
    public String showEditRoom(Model model, @PathVariable String id) {
        Long roomId = Long.valueOf(id);
        model.addAttribute("room",this.roomService.getRoomById(roomId));
        model.addAttribute("mode", "edit");
        return "editRoom";
    }

    @PostMapping("/rooms/edit/{id}")
    public String editRoom(Room room) {
        System.out.println(room.toString());
        this.roomService.editRoom(room);
        return "redirect:/rooms";
    }

    @RequestMapping(value="/rooms/delete/{id}", method = RequestMethod.DELETE)
    public String deleteRoom(@PathVariable Long id) {
        this.roomService.deleteRoom(id);
        return "redirect:/rooms";
    }

    @GetMapping("/rooms/new")
    public String showAddRoom(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("mode", "add");
        return "editRoom";
    }

    @PostMapping("/rooms/new")
    public String addRoom(RedirectAttributes model, Room room) {
        System.out.println(room.toString());
        this.roomService.addRoom(room);
        model.addFlashAttribute("message", "Successfully added room");
        return "redirect:/rooms";
    }
}
