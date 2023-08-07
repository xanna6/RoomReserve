package com.piotrowska.roomreserve.repository;

import com.piotrowska.roomreserve.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.people >= :numberOfGuests AND r.id NOT IN " +
            "(SELECT rg.room.id FROM RoomGuest rg WHERE rg.fromDate BETWEEN :fromDate AND :toDate " +
            "OR rg.toDate BETWEEN :fromDate AND :toDate)")
    List<Room> findFilteredRooms(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate,
                                 @Param("numberOfGuests") int numberOfGuests);

    @Query("SELECT COUNT (rg.room) FROM RoomGuest rg WHERE rg.room.id = :roomId")
    int countReservationsForRoom(@Param("roomId") Long roomId);
}
