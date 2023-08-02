package com.piotrowska.roomreserve.service;

import com.piotrowska.roomreserve.entity.RoomGuest;

import java.util.List;

public interface ReservationService {
    List<RoomGuest> getAllReservations();
    RoomGuest getReservationById(Long id);
    void editReservation(RoomGuest roomGuest);
    void deleteReservation(Long id);
    List<RoomGuest> getFilteredReservations(String fromDate, String toDate, List<Long> roomIds);
    void addReservation(RoomGuest reservation);
}
