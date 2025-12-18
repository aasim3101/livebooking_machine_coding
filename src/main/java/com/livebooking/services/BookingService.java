package com.livebooking.services;

import com.livebooking.Models.Booking;
import com.livebooking.Models.LiveShow;
import com.livebooking.Models.ShowSlot;
import com.livebooking.Models.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BookingService {
    private final Map<String, Booking> bookings = new HashMap<>();
    private final Map<String, Set<String>> userSlotMap = new HashMap<>();

    public Booking book(User user, LiveShow show, String startTime, int persons) {
        ShowSlot slot = show.getSlots().get(startTime);
        userSlotMap.putIfAbsent(user.getName(), new HashSet<>());

        if (userSlotMap.get(user.getName()).contains(startTime)) {
            throw new IllegalArgumentException("User already registered in another show at this start time");
        }

        if (slot.canBook(persons)) {
            Booking booking = new Booking(user, show, slot, persons);
            bookings.put(booking.getBookingId(), booking);
            userSlotMap.get(user.getName()).add(startTime);
            return booking;
        } else {
            Booking waitBooking = new Booking(user, show, slot, persons);
            slot.getWaitlist().add(waitBooking);
            System.out.println("Added to waitlist");
            return null;
        }
    }

    public void cancel(String bookingId) {
        Booking booking = bookings.get(bookingId);

        if (booking == null) {
            return;
        }

        User user = booking.getUser();
        ShowSlot slot = booking.getSlot();
        int persons = booking.getPersons();

        slot.cancel(persons);
        userSlotMap.get(user.getName()).remove(slot.getStartTime());

        if (!slot.getWaitlist().isEmpty()) {
            Booking nextBooking = slot.getWaitlist().peek();
            if (slot.canBook(nextBooking.getPersons())) {
                slot.getWaitlist().poll();
                slot.book(nextBooking.getPersons());
                bookings.put(nextBooking.getBookingId(), nextBooking);
                userSlotMap.putIfAbsent(nextBooking.getUser().getName(), new HashSet<>());
                userSlotMap.get(nextBooking.getUser().getName()).add(slot.getStartTime());
                System.out.println("Waitlist booking confirmed");
            }
        }

        System.out.println("Booking cancelled");
    }
}
