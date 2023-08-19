package com.piotrowska.roomreserve.controller;

import com.piotrowska.roomreserve.entity.Room;
import com.piotrowska.roomreserve.service.RoomService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@Validated
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
    public String editRoom(Model model, @Valid Room room, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("mode", "edit");
            return "editRoom";
        }
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
    public String addRoom(RedirectAttributes redirectAttributes, Model model, @Valid Room room, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("mode", "add");
            return "editRoom";
        }
        System.out.println(room.toString());
        this.roomService.addRoom(room);
        redirectAttributes.addFlashAttribute("message", "Successfully added room");
        return "redirect:/rooms";
    }

    @GetMapping("/search")
    public String getFilteredReservations(@RequestParam(name = "fromDate") @NotNull @FutureOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                          @RequestParam(name = "toDate") @NotNull @Future @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
                                          @RequestParam(name = "numberOfAdults") @Min(1) int numberOfAdults,
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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingRequestParams(MissingServletRequestParameterException ex, Model model) {
        String field = ex.getParameterName();
        List<String> messages = new ArrayList<>();
        if (field.equals("fromDate") || field.equals("toDate")) {
            model.addAttribute("error", Arrays.asList("Arrival and departure date is required"));
        }
        return "rooms";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleIncorrectRequestParams(ConstraintViolationException ex, Model model) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> messages = new ArrayList<>(constraintViolations.size());
        for (ConstraintViolation cv : constraintViolations) {
            if (cv.getPropertyPath().toString().contains("fromDate")) {
                messages.add("Arrival date must be present or future");
            }
            if (cv.getPropertyPath().toString().contains("toDate")) {
                messages.add("Departure date must be future");
            }
            if (cv.getPropertyPath().toString().contains("numberOfAdults")) {
                messages.add("Number of guests should be at least 1");
            }
        }

        model.addAttribute("errors", messages);
        return "rooms";
    }
}
