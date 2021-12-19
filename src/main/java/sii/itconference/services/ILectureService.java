package sii.itconference.services;

import sii.itconference.model.Lecture;

import java.util.List;

public interface ILectureService {
    List<Lecture> findAll();
    Lecture getLectureByLectureId(Long id);
    void saveLecture(Lecture lecture);
}