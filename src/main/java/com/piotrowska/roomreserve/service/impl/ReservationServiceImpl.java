package com.piotrowska.roomreserve.service.impl;

import com.piotrowska.roomreserve.entity.RoomGuest;
import com.piotrowska.roomreserve.repository.ReservationRepository;
import com.piotrowska.roomreserve.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<RoomGuest> getAllReservations() {
        return this.reservationRepository.findAllReservations();
    }

    @Override
    public RoomGuest getReservationById(Long id) {
        return this.reservationRepository.getReferenceById(id);
    }

    @Override
    public void editReservation(RoomGuest roomGuest) {
        this.reservationRepository.updateReservationDatesPriceNumberOfGuests(roomGuest.getId(), roomGuest.getFromDate(),
                roomGuest.getToDate(), roomGuest.getPrice(), roomGuest.getNumberOfAdults(), roomGuest.getNumberOfChildren());
    }

    @Override
    public void deleteReservation(Long id) {
        this.reservationRepository.deleteById(id);
    }

    @Override
    public List<RoomGuest> getFilteredReservations(String dateFrom, String dateTo, List<Long> roomIds) {
        LocalDate fromDate = LocalDate.parse(dateFrom);
        LocalDate toDate = LocalDate.parse(dateTo);
        if (roomIds == null || roomIds.size() == 0) {
            return this.reservationRepository.findRoomGuestByFromDateGreaterThanEqualAndToDateLessThanEqual(fromDate, toDate);
        } else {
            return this.reservationRepository.findRoomGuestByFromDateGreaterThanEqualAndToDateLessThanEqualAndRoomIdIn(fromDate, toDate, roomIds);
        }
    }

    @Override
    public void addReservation(RoomGuest reservation) {
        this.reservationRepository.save(reservation);
    }
}
