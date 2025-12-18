package com.livebooking.services;

import com.livebooking.Models.LiveShow;
import com.livebooking.Models.ShowSlot;
import com.livebooking.enums.Genre;
import com.livebooking.strategy.SlotRankingStrategy;
import com.livebooking.strategy.StartTimeRankingStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchService {
    private final SlotRankingStrategy rankingStrategy = new StartTimeRankingStrategy();

    public void showByGenre(Collection<LiveShow> shows, Genre genre) {
        for (LiveShow show : shows) {
            if (show.getGenre() == genre) {
                List<ShowSlot> slots = new ArrayList<>(show.getSlots().values());
                rankingStrategy.rank(slots);

                for (ShowSlot slot : slots) {
                    if (slot.getCapacity() > 0) {
                        System.out.println(show.getName() + ": " + slot.getTimeRange() + " " +slot.getCapacity());
                    }
                }
            }
        }
    }
}
