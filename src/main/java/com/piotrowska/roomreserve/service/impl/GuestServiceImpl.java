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
    public Guest findOrInsertGuest(Guest guest) {
        Guest foundGuest = this.guestRepository.findGuestByFirstnameAndSurnameAndPhoneNumberAndMail(guest.getFirstname(),
                guest.getSurname(), guest.getPhoneNumber(), guest.getMail());
        if (foundGuest == null) {
            Guest savedGuest = this.guestRepository.save(guest);
            return savedGuest;
        } else {
            return foundGuest;
        }
    }
}
