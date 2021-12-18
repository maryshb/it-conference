package sii.itconference.controller;

import org.springframework.beans.factory.annotation.Autowired;
import sii.itconference.model.Block;
import sii.itconference.model.Lecture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sii.itconference.services.IBlockService;
import sii.itconference.services.ILectureService;

import java.util.List;

@RestController
@RequestMapping("/conference/")
public class CommonController {

    private ILectureService lectureService;
    private IBlockService blockService;

    @Autowired
    public CommonController(ILectureService lectureService, IBlockService blockService) {
        this.lectureService = lectureService;
        this.blockService = blockService;
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
}
