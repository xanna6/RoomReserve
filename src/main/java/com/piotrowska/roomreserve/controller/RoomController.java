package com.piotrowska.roomreserve.controller;

import com.piotrowska.roomreserve.entity.Room;
import com.piotrowska.roomreserve.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "editRoom";
    }

    @PostMapping("/rooms/edit/{id}")
    public String editRoom(Room room) {
        System.out.println(room.toString());
        this.roomService.editRoom(room);
        return "redirect:/rooms";
    }
}
