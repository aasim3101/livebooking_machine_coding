package com.livebooking.strategy;

import com.livebooking.Models.ShowSlot;

import java.util.List;

public interface SlotRankingStrategy {
    List<ShowSlot> rank(List<ShowSlot> slots);
}
