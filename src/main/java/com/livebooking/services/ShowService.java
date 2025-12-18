package com.livebooking.services;

import com.livebooking.Models.LiveShow;
import com.livebooking.Models.ShowSlot;
import com.livebooking.enums.Genre;

import java.util.*;

public class ShowService {
    private final Map<String, LiveShow> shows = new HashMap<>();

    public void registerShow(String name, Genre genre) {
        shows.put(name, new LiveShow(name, genre));
        System.out.println(name + " show is generated !!");
    }

    public void onboardSlots(String showName, List<ShowSlot> slots) {
        LiveShow show = shows.get(showName);

        for (ShowSlot slot : slots) {
            show.addSlot(slot);
        }

        System.out.println("Done!");
    }

    public Collection<LiveShow> getAllShows() {
        return shows.values();
    }

    public LiveShow getShow(String name) {
        return shows.get(name);
    }
}
