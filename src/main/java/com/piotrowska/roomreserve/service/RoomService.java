package com.piotrowska.roomreserve.service;

import com.piotrowska.roomreserve.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    Room getRoomById(Long id);
    void editRoom(Room room);
    void deleteRoom(Long id);
    void addRoom(Room room);
}
