package com.piotrowska.roomreserve.service;

import com.piotrowska.roomreserve.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    Room getRoomById(Long id);
    void editRoom(Room room);
    boolean deleteRoom(Long id);
    void addRoom(Room room);
    List<Room> getAvailableRooms(String fromDate, String toDate, int numberOfAdults, int numberOfChildren);
}
