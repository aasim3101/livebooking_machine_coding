package com.livebooking.strategy;

import com.livebooking.Models.ShowSlot;

import java.util.Comparator;
import java.util.List;

public class StartTimeRankingStrategy implements SlotRankingStrategy{
    @Override
    public List<ShowSlot> rank(List<ShowSlot> slots) {
        slots.sort(Comparator.comparingInt(slot -> toMinutes(slot.getStartTime())));
        return slots;
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hour * 60 + minutes;
    }
}
