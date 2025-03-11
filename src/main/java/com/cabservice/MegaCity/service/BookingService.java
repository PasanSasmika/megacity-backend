package com.cabservice.MegaCity.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabservice.MegaCity.model.Booking;
import com.cabservice.MegaCity.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Creates a new booking.
     * If the booking status is "ACCEPTED", checks for conflicts.
     *
     * @param booking The booking to create.
     * @return The created booking.
     * @throws IllegalStateException If there is a conflict with an existing booking.
     */
    public Booking createBooking(Booking booking) {
        if ("ACCEPTED".equalsIgnoreCase(booking.getBookingStatus())) {
            checkForBookingConflict(booking.getDriverID(), booking.getDate(), booking.getTime(), null);
        }
        return bookingRepository.save(booking);
    }

    /**
     * Updates an existing booking.
     * If the booking status is updated to "ACCEPTED", checks for conflicts.
     *
     * @param bookingId      The ID of the booking to update.
     * @param bookingDetails The updated booking details.
     * @return The updated booking, or null if the booking does not exist.
     * @throws IllegalStateException If there is a conflict with an existing booking.
     */
    public Booking updateBooking(String bookingId, Booking bookingDetails) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
            Booking existingBooking = optionalBooking.get();

            // Update fields
            existingBooking.setPickupLocation(bookingDetails.getPickupLocation());
            existingBooking.setDropLocation(bookingDetails.getDropLocation());
            existingBooking.setDate(bookingDetails.getDate());
            existingBooking.setTime(bookingDetails.getTime());
            existingBooking.setCustomerID(bookingDetails.getCustomerID());
            existingBooking.setDriverID(bookingDetails.getDriverID());
            existingBooking.setBookingStatus(bookingDetails.getBookingStatus());

            // Check for conflicts if the booking is being accepted
            if ("ACCEPTED".equalsIgnoreCase(existingBooking.getBookingStatus())) {
                checkForBookingConflict(existingBooking.getDriverID(), existingBooking.getDate(), existingBooking.getTime(), bookingId);
            }

            return bookingRepository.save(existingBooking);
        }
        return null;
    }

    /**
     * Retrieves a booking by its ID.
     *
     * @param bookingId The ID of the booking to retrieve.
     * @return The booking, if found.
     */
    public Optional<Booking> getBookingById(String bookingId) {
        return bookingRepository.findById(bookingId);
    }

    /**
     * Retrieves all bookings.
     *
     * @return A list of all bookings.
     */
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Retrieves all bookings for a specific driver.
     *
     * @param driverID The ID of the driver.
     * @return A list of bookings for the driver.
     */
    public List<Booking> getBookingsByDriverID(String driverID) {
        return bookingRepository.findByDriverID(driverID);
    }

    /**
     * Deletes a booking by its ID.
     *
     * @param bookingId The ID of the booking to delete.
     */
    public void deleteBooking(String bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    /**
     * Checks for conflicts with existing bookings for the same driver, date, and time.
     * Throws an exception if a conflict is found.
     *
     * @param driverID        The ID of the driver.
     * @param date            The date of the booking.
     * @param time            The time of the booking.
     * @param currentBookingId The ID of the current booking (to exclude from conflict check).
     * @throws IllegalStateException If a conflict is found.
     */
    private void checkForBookingConflict(String driverID, LocalDate date, LocalTime time, String currentBookingId) {
        List<Booking> conflicts = bookingRepository.findByDriverIDAndDateAndTimeAndBookingStatus(driverID, date, time, "ACCEPTED");
        if (currentBookingId != null) {
            conflicts.removeIf(b -> b.getBookingId().equals(currentBookingId));
        }
        if (!conflicts.isEmpty()) {
            throw new IllegalStateException("Driver already has an accepted booking for this date and time.");
        }
    }
}