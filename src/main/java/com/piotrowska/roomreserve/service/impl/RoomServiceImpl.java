package com.piotrowska.roomreserve.service.impl;

import com.piotrowska.roomreserve.entity.Room;
import com.piotrowska.roomreserve.repository.RoomRepository;
import com.piotrowska.roomreserve.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
