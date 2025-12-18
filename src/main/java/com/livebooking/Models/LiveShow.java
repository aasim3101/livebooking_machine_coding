package com.livebooking.Models;

import com.livebooking.enums.Genre;

import java.util.HashMap;
import java.util.Map;

public class LiveShow {
    private final String name;
    private final Genre genre;
    private final Map<String, ShowSlot> slots = new HashMap<>();

    public LiveShow(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
    }

    public void addSlot(ShowSlot slot) {
        if (slots.containsKey(slot.getStartTime())) {
            throw new IllegalArgumentException("Overlapping slot not");
        }

        slots.put(slot.getStartTime(), slot);
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Map<String, ShowSlot> getSlots() {
        return slots;
    }
}
