package sii.itconference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sii.itconference.model.Lecture;

@Repository
public interface ILectureRepository extends JpaRepository<Lecture, Long> {
}
