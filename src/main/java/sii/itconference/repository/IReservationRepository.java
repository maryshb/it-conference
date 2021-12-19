package sii.itconference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sii.itconference.model.Block;
import sii.itconference.model.Reservation;
import sii.itconference.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findReservationByUser(User user);// TODO name - reservations

    void deleteReservationByReservationId(Long reservationId);

    boolean existsReservationByUserAndBlock(User user, Block block);
}