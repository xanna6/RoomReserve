package com.piotrowska.roomreserve.repository;

import com.piotrowska.roomreserve.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Guest findGuestByFirstnameAndSurnameAndPhoneNumberAndMail(String firstname, String lastname, String phoneNumber, String mail);
}
