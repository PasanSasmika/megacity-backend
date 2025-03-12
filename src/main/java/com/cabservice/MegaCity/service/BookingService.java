package com.cabservice.MegaCity.service;

import com.cabservice.MegaCity.model.Booking;
import com.cabservice.MegaCity.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EmailService emailService;

    /**
     * Creates a new booking.
     *
     * @param booking The booking details to create.
     * @return The created booking.
     * @throws IllegalStateException If there is a conflict (e.g., overlapping bookings).
     */
    public Booking createBooking(Booking booking) {
        // Check for conflicts (e.g., overlapping bookings for the same driver)
        List<Booking> conflictingBookings = bookingRepository.findByDriverIDAndDateAndTimeAndBookingStatus(
                booking.getDriverID(),
                booking.getDate(),
                booking.getTime(),
                "ACCEPTED"
        );

        if (!conflictingBookings.isEmpty()) {
            throw new IllegalStateException("Driver already has an accepted booking at this time.");
        }

        // Set default status if not provided
        if (booking.getBookingStatus() == null) {
            booking.setBookingStatus("PENDING");
        }

        return bookingRepository.save(booking);
    }

    /**
     * Updates an existing booking.
     *
     * @param bookingId      The ID of the booking to update.
     * @param bookingDetails The updated booking details.
     * @return The updated booking.
     * @throws RuntimeException If the booking is not found.
     * @throws IllegalStateException If there is a conflict (e.g., overlapping bookings).
     */
    public Booking updateBooking(String bookingId, Booking bookingDetails) {
        return bookingRepository.findById(bookingId).map(existingBooking -> {
            // Check for conflicts if the booking is being updated to "ACCEPTED"
            if ("ACCEPTED".equalsIgnoreCase(bookingDetails.getBookingStatus())) {
                List<Booking> conflictingBookings = bookingRepository.findByDriverIDAndDateAndTimeAndBookingStatus(
                        existingBooking.getDriverID(),
                        existingBooking.getDate(),
                        existingBooking.getTime(),
                        "ACCEPTED"
                );

                if (!conflictingBookings.isEmpty()) {
                    throw new IllegalStateException("Driver already has an accepted booking at this time.");
                }
            }

            // Update fields
            existingBooking.setDriverID(bookingDetails.getDriverID());
            existingBooking.setBookingStatus(bookingDetails.getBookingStatus());
            existingBooking.setPickupLocation(bookingDetails.getPickupLocation());
            existingBooking.setDropLocation(bookingDetails.getDropLocation());
            existingBooking.setDate(bookingDetails.getDate());
            existingBooking.setTime(bookingDetails.getTime());
            existingBooking.setCustomerName(bookingDetails.getCustomerName());
            existingBooking.setCustomerID(bookingDetails.getCustomerID());
            existingBooking.setEmail(bookingDetails.getEmail());
            existingBooking.setPhone(bookingDetails.getPhone());
            existingBooking.setDistance(bookingDetails.getDistance());
            existingBooking.setTotal(bookingDetails.getTotal());

            // Save the updated booking
            Booking updatedBooking = bookingRepository.save(existingBooking);

            // Send email if the booking is accepted
            if ("ACCEPTED".equalsIgnoreCase(updatedBooking.getBookingStatus())) {
                emailService.sendAcceptEmail(
                        updatedBooking.getEmail(),
                        updatedBooking.getCustomerName(),
                        updatedBooking.getPickupLocation(),
                        updatedBooking.getDropLocation(),
                        updatedBooking.getDistance(),
                        updatedBooking.getTotal()
                );
            }

            return updatedBooking;
        }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    /**
     * Retrieves a booking by its ID.
     *
     * @param bookingId The ID of the booking to retrieve.
     * @return The booking if found.
     * @throws RuntimeException If the booking is not found.
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
     * @throws RuntimeException If the booking is not found.
     */
    public void deleteBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Send cancellation email
        emailService.sendCancelEmail(
                booking.getEmail(),
                booking.getCustomerName(),
                booking.getPickupLocation(),
                booking.getDropLocation()
        );

        // Delete the booking
        bookingRepository.delete(booking);
    }
}