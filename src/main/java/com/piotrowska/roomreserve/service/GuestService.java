package com.piotrowska.roomreserve.service;

import com.piotrowska.roomreserve.entity.Guest;

public interface GuestService {
    Guest findGuest(String firstname, String surname, String phoneNumber, String mail);
}
