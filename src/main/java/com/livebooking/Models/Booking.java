package com.livebooking.Models;

import java.util.UUID;

public class Booking {
    private final String bookingId;
    private final User user;
    private final LiveShow show;
    private final ShowSlot slot;
    private final int persons;

    public Booking(User user, LiveShow show, ShowSlot slot, int persons) {
        this.bookingId = UUID.randomUUID().toString();
        this.user = user;
        this.show = show;
        this.slot = slot;
        this.persons = persons;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public LiveShow getShow() {
        return show;
    }

    public ShowSlot getSlot() {
        return slot;
    }

    public int getPersons() {
        return persons;
    }
}
