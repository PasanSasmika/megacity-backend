package com.cabservice.MegaCity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cabservice.MegaCity.model.Booking;
import com.cabservice.MegaCity.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    
    
   @Autowired
    private BookingService bookingService;

    /**
     * Creates a new booking.
     * @param booking The booking details from the request body.
     * @return The created booking wrapped in a ResponseEntity.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(createdBooking);
    }

    /**
     * Updates an existing booking.
     * @param bookingId The ID of the booking to be updated.
     * @param bookingDetails The updated booking details from the request body.
     * @return The updated booking if found, otherwise returns 404 Not Found.
     */

     
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") String bookingId, @RequestBody Booking bookingDetails) {
        Booking updatedBooking = bookingService.updateBooking(bookingId, bookingDetails);
        if (updatedBooking != null) {
            return ResponseEntity.ok(updatedBooking);
        }
        return ResponseEntity.notFound().build(); // Return 404 if not found
    }

    /**
     * Retrieves a booking by its ID.
     * @param bookingId The ID of the booking to retrieve.
     * @return The booking details if found, otherwise returns 404 Not Found.
     */


    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") String bookingId) {
        Optional<Booking> booking = bookingService.getBookingById(bookingId);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all bookings.
     * @return A list of all bookings.
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/driver/{driverID}")
    public ResponseEntity<List<Booking>> getBookingsByDriverID(@PathVariable String driverID) {
        List<Booking> bookings = bookingService.getBookingsByDriverID(driverID);
        if (bookings.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no bookings found
        }
        return ResponseEntity.ok(bookings); // Return 200 OK with the list of bookings
    }

    /**
     * Deletes a booking by its ID.
     * @param bookingId The ID of the booking to delete.
     * @return A 204 No Content response if successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") String bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }
}
