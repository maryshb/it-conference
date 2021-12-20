package sii.itconference.controller;

import org.springframework.web.bind.annotation.*;
import sii.itconference.model.dto.ReservationDto;
import sii.itconference.services.IReservationService;

@RestController
@RequestMapping("/api/conference")
public class ReservationController {

    private IReservationService reservationService;

    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/lectures/reserve")
    public void saveReservation(@RequestBody ReservationDto reservationDto) {
        this.reservationService.saveReservation(reservationDto);
    }
}
