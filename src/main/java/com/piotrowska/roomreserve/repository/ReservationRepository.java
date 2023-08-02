package com.piotrowska.roomreserve.repository;

import com.piotrowska.roomreserve.entity.RoomGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<RoomGuest, Long> {

    @Query("SELECT rg FROM RoomGuest rg JOIN Guest g ON rg.guest.id=g.id ORDER BY rg.fromDate ASC")
    List<RoomGuest> findAllReservations();

    @Transactional
    @Modifying
    @Query("UPDATE RoomGuest rg " +
            "SET rg.fromDate = :fromDate, rg.toDate = :toDate, rg.price = :price, rg.numberOfAdults = :numberOfAdults, rg.numberOfChildren = :numberOfChildren " +
            "WHERE rg.id = :id")
    void updateReservationDatesPriceNumberOfGuests(@Param("id") Long id, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate,
                                                   @Param("price") int price, @Param("numberOfAdults") int numberOfAdults, @Param("numberOfChildren") int numberOfChildren);

    List<RoomGuest> findRoomGuestByFromDateGreaterThanEqualAndToDateLessThanEqualAndRoomIdIn(LocalDate fromDate, LocalDate toDate, List<Long> roomIds);

    List<RoomGuest> findRoomGuestByFromDateGreaterThanEqualAndToDateLessThanEqual(LocalDate fromDate, LocalDate toDate);
}
