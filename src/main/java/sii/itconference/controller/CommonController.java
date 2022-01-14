package sii.itconference.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sii.itconference.model.Block;
import sii.itconference.model.Lecture;
import sii.itconference.model.dto.UserDto;
import sii.itconference.services.IBlockService;
import sii.itconference.services.ILectureService;
import sii.itconference.services.IUserService;

import java.util.List;

@RestController
@RequestMapping("/api/conference")
public class CommonController {

    private final ILectureService lectureService;
    private final IBlockService blockService;
    private final IUserService userService;

    @Autowired
    public CommonController(ILectureService lectureService, IBlockService blockService, IUserService userService) {
        this.lectureService = lectureService;
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

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return this.userService.getAllUsers();
    }
}