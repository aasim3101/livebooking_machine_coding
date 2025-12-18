package com.livebooking;

import com.livebooking.Models.Booking;
import com.livebooking.Models.ShowSlot;
import com.livebooking.Models.User;
import com.livebooking.enums.Genre;
import com.livebooking.services.BookingService;
import com.livebooking.services.SearchService;
import com.livebooking.services.ShowService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShowService showService = new ShowService();
        BookingService bookingService = new BookingService();
        SearchService searchService = new SearchService();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String[] tokens = input.split(" ", 2);
            String command = tokens[0];

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting system");
                break;
            }

            switch (command) {
                case "registerShow": {
                    String[] parts = tokens[1].split("->");
                    String showName = parts[0].trim();
                    Genre genre = Genre.valueOf(parts[1].trim().toUpperCase());
                    showService.registerShow(showName, genre);
                    break;
                }
                case "onboardSlots": {
                    String[] parts = tokens[1].split(" ", 2);
                    String showName = parts[0];
                    String[] slotInputs = parts[1].split(",");

                    List<ShowSlot> slots = new ArrayList<>();

                    for (String slotInput : slotInputs) {
                        slotInput = slotInput.trim();
                        String[] slotInputParts = slotInput.split(" ");
                        String[] times = slotInputParts[0].split("-");
                        int capacity = Integer.parseInt(slotInputParts[1]);

                        slots.add(new ShowSlot(times[0], times[1], capacity));
                    }

                    showService.onboardSlots(showName, slots);
                    break;
                }
                case "showAvailByGenre": {
                    Genre genre = Genre.valueOf(tokens[1].trim().toUpperCase());
                    searchService.showByGenre(showService.getAllShows(), genre);
                    break;
                }
                case "bookTicket": {
                    String[] parts = tokens[1].split(",");
                    String userName = parts[0].trim();
                    String showName = parts[1].trim();
                    String startTime = parts[2].trim();
                    int persons = Integer.parseInt(parts[3].trim());

                    User user = new User(userName);
                    Booking booking = bookingService.book(user, showService.getShow(showName), startTime, persons);

                    if (booking != null) {
                        System.out.println("Booked. Booking id: " + booking.getBookingId());
                    }

                    break;
                }
                case "cancelBookingId": {
                    String bookingId = tokens[1].trim();
                    bookingService.cancel(bookingId);
                    break;
                }
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
