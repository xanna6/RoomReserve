package com.piotrowska.roomreserve.service;

import com.piotrowska.roomreserve.entity.Guest;

public interface GuestService {

    Guest findOrInsertGuest(Guest guest);
}
