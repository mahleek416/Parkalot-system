package com.parkalot.service;

import com.parkalot.model.Reservation;
import com.parkalot.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByEmail(String email) {
        return reservationRepository.findByEmail(email);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}