package com.piotrowska.roomreserve.service.impl;

import com.piotrowska.roomreserve.entity.Guest;
import com.piotrowska.roomreserve.repository.GuestRepository;
import com.piotrowska.roomreserve.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {

    private GuestRepository guestRepository;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public Guest findGuest(String firstname, String surname, String phoneNumber, String mail) {
        return this.guestRepository.findGuestByFirstnameAndSurnameAndPhoneNumberAndMail(firstname, surname, phoneNumber, mail);
    }
}
