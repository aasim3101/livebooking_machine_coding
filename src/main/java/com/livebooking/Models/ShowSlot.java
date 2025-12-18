package com.livebooking.Models;

import java.util.LinkedList;
import java.util.Queue;

public class ShowSlot {
    private final String startTime;
    private final String endTime;
    private int capacity;
    private final Queue<Booking> waitlist = new LinkedList<>();

    public ShowSlot(String startTime, String endTime, int capacity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    public boolean canBook(int persons) {
        return capacity >= persons;
    }

    public void book(int persons) {
        capacity -= persons;
    }

    public void cancel(int persons) {
        capacity += persons;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getTimeRange() {
        return "(" + startTime + "-" + endTime + ")";
    }

    public Queue<Booking> getWaitlist() {
        return waitlist;
    }
}
