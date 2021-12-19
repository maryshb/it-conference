package sii.itconference.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sii.itconference.model.Reservation;
import sii.itconference.model.User;
import sii.itconference.model.dto.ReservationDto;
import sii.itconference.model.dto.UserDto;
import sii.itconference.repository.IUserRepository;
import sii.itconference.services.IReservationService;
import sii.itconference.services.IUserService;

import java.util.List;


@RestController
public class UserController {


    private IUserRepository userRepository;
    private IReservationService reservationService;
    private IUserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(IUserRepository userRepository, IReservationService reservationService, IUserService userService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.reservationService = reservationService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/schedule")
    public List<Reservation> getSchedule(@RequestBody UserDto userDto) {
        User user = this.userRepository.findByUsername(userDto.getUsername()); //TODO DO POPRAWY (uzycie service)

        return this.reservationService.findReservationByUser(user);
    }

    @PatchMapping("/edit-form")
    public UserDto editMail(@RequestBody UserDto userDto) {

        //TODO NPE !
        userService.updateEmail(userDto);

        User user = this.userRepository.findByUsername(userDto.getUsername());
        userDto = modelMapper.map(user, UserDto.class);

        // userRepository.updateEmail(ofNullable(user).map(User::getEmail);
        //todo response entity
        return userDto;
    }

    //todo response entity
    //todo mapping z wykorzystaniem loginu i nazwy wykładu


    @DeleteMapping("/schedule/cancel")
    public void cancelReservation(@RequestBody ReservationDto reservationDto) {
        this.reservationService.cancelReservation(reservationDto);
        //todo response entity
    }
}
