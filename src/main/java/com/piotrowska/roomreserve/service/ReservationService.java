package com.piotrowska.roomreserve.service;

import com.piotrowska.roomreserve.entity.RoomGuest;

import java.util.List;

public interface ReservationService {
    List<RoomGuest> getAllReservations();
    RoomGuest getReservationById(Long id);
}
