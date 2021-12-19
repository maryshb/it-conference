package sii.itconference.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sii.itconference.model.Block;
import sii.itconference.model.Lecture;
import sii.itconference.model.dto.ReservationDto;
import sii.itconference.model.dto.UserDto;
import sii.itconference.services.IBlockService;
import sii.itconference.services.ILectureService;
import sii.itconference.services.IReservationService;
import sii.itconference.services.IUserService;

import java.util.List;

@RestController
@RequestMapping("/conference/")
public class CommonController {

    private ILectureService lectureService;
    private IReservationService reservationService;
    private IBlockService blockService;
    private IUserService userService;

    @Autowired
    public CommonController(ILectureService lectureService, IReservationService reservationService, IBlockService blockService, IUserService userService) {
        this.lectureService = lectureService;
        this.reservationService = reservationService;
        this.blockService = blockService;
        this.userService = userService;
    }

    @GetMapping
    public List<Block> landingPage() {
        return this.blockService.findAll();
    }

    @GetMapping("/lectures")
    public List<Lecture> getLectures() {
        return this.lectureService.findAll();
    }
    //TODO mapping for lectures of each block

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping("/lectures/reserve")
    public void saveReservation(@RequestBody ReservationDto reservationDto) {
        //TODO check if seats > 0
        this.reservationService.saveReservation(reservationDto);
    }

}
