package sii.itconference.services;

import sii.itconference.model.Reservation;
import sii.itconference.model.User;
import sii.itconference.model.dto.ReservationDto;

import java.util.List;

public interface IReservationService {
    List<Reservation> findReservationByUser(User user);
    void cancelReservation(ReservationDto reservationDto);
    void saveReservation(ReservationDto reservationDto);
}
