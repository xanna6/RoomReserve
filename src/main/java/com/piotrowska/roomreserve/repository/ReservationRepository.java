package com.piotrowska.roomreserve.repository;

import com.piotrowska.roomreserve.entity.RoomGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<RoomGuest, Long> {

    @Query("SELECT rg FROM RoomGuest rg JOIN Guest g ON rg.guest.id=g.id ORDER BY rg.fromDate ASC")
    List<RoomGuest> findAllReservations();
}
