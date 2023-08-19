package com.piotrowska.roomreserve.service.impl;

import com.piotrowska.roomreserve.entity.Room;
import com.piotrowska.roomreserve.repository.RoomRepository;
import com.piotrowska.roomreserve.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAllRooms() {
        return this.roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return this.roomRepository.getReferenceById(id);
    }

    @Override
    public void editRoom(Room room) {
        this.roomRepository.save(room);
    }

    @Override
    public boolean deleteRoom(Long id) {
        int roomReservations = this.roomRepository.countReservationsForRoom(id);
        if (roomReservations == 0) {
            this.roomRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addRoom(Room room) {
        this.roomRepository.save(room);
    }

    @Override
    public List<Room> getAvailableRooms(LocalDate fromDate, LocalDate toDate, int numberOfAdults, int numberOfChildren) {
        return this.roomRepository.findFilteredRooms(fromDate, toDate, numberOfAdults + numberOfChildren);
    }

}
