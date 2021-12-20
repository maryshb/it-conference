package sii.itconference.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sii.itconference.model.Lecture;
import sii.itconference.repository.ILectureRepository;
import sii.itconference.services.ILectureService;

import java.util.List;

@Service
public class LectureServiceImpl implements ILectureService {

    private ILectureRepository lectureRepository;

    @Autowired
    public LectureServiceImpl(ILectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Override
    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    @Override
    public Lecture getLectureByLectureId(Long id) {
       return lectureRepository.findLectureByLectureId(id);
    }

    @Override
    public void saveLecture(Lecture lecture) {
        lectureRepository.save(lecture);
    }
}