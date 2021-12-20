package sii.itconference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sii.itconference.model.Block;
import sii.itconference.model.Lecture;

import java.util.List;

@Repository
public interface ILectureRepository extends JpaRepository<Lecture, Long> {
    Lecture findLectureByLectureId(Long lectureId);

    List<Lecture> getLecturesByBlock(Block block);
}
