package sii.itconference.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sii.itconference.model.Reservation;
import sii.itconference.model.User;
import sii.itconference.model.dto.ReservationDto;
import sii.itconference.model.dto.UserDto;
import sii.itconference.services.IReservationService;
import sii.itconference.services.IUserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IReservationService reservationService;
    private final IUserService userService;

    @Autowired
    public UserController(IReservationService reservationService, IUserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @PostMapping("/schedule")
    public List<Reservation> getSchedule(@RequestBody UserDto userDto) {
        User user = this.userService.getUserByUsername(userDto.getUsername());

        return this.reservationService.findReservationsByUser(user);
    }

    @DeleteMapping("/schedule/cancel")
    public ResponseEntity<String> cancelReservation(@RequestBody ReservationDto reservationDto) {
        try {
            if (this.reservationService.existsReservationByReservationId(reservationDto.getReservationId())) {
                this.reservationService.cancelReservation(reservationDto);
                return new ResponseEntity<>("Reservation cancelled!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Reservation doesn't exist!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Failed to cancel the reservation!");
        }
        return new ResponseEntity<>(
                "Failed to cancel the reservation!",
                HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/edit-form")
    public ResponseEntity<String> editMail(@RequestBody UserDto userDto) {
        try {
            userService.updateEmail(userDto);

            return new ResponseEntity<>(
                    "User email has been modified!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The email address could not be modified!");
        }
        return new ResponseEntity<>(
                "The email address could not be modified!",
                HttpStatus.NOT_FOUND);
    }
}