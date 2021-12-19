package sii.itconference.services.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sii.itconference.model.Block;
import sii.itconference.model.Lecture;
import sii.itconference.model.Reservation;
import sii.itconference.model.User;
import sii.itconference.model.dto.ReservationDto;
import sii.itconference.repository.IReservationRepository;
import sii.itconference.services.IBlockService;
import sii.itconference.services.ILectureService;
import sii.itconference.services.IReservationService;
import sii.itconference.services.IUserService;

import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    private IReservationRepository reservationRepository;
    private IUserService userService;
    private ILectureService lectureService;
    private IBlockService blockService;
    private ModelMapper modelMapper;

    @Autowired
    public ReservationServiceImpl(IReservationRepository reservationRepository, IUserService userService,
                                  ILectureService lectureService, IBlockService blockService, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.lectureService = lectureService;
        this.blockService = blockService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Reservation> findReservationByUser(User user) {
        return this.reservationRepository.findReservationByUser(user);
    }

    @Override
    public void cancelReservation(ReservationDto reservationDto) {
        this.reservationRepository.deleteReservationByReservationId(reservationDto.getReservationId().longValue()); //TODO sprawdzić longValue() - potrzebne?
    }


    @Override
    public void saveReservation(ReservationDto reservationDto) {
    //TODO sprawdzić czy blokuje baze
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class); //TODO ZOSTAJE?
        userService.saveUser(reservationDto);

        User user = userService.getUserByUsername(reservationDto.getUsername());
        Block block = blockService.getBlockByBlockId(reservationDto.getBlockId());

        if (!reservationRepository.existsReservationByUserAndBlock(user, block)) {

            Lecture lecture = lectureService.getLectureByLectureId(reservationDto.getLectureId());
            int seats = lecture.getSeats();

            //SPRAWDZ CZY  W REZERWACJACH NIE MA JUZ TAKIEGO BLOKU

            if (seats > 0) {
                if(user.getEmail().equals(reservationDto.getEmail())) {
                    reservation.setUser(user);
                    // TODO NPE !
                    reservation.setLecture(lectureService.getLectureByLectureId(reservationDto.getLectureId()));

                    this.reservationRepository.save(reservation);
                    lecture.setSeats(seats - 1);
                    this.lectureService.saveLecture(lecture);
                }
                //TODO ZAPIS DO PLIKU (POWIADOMIENIE EMAIL)
            } else {
                // TODO RESPONSE zamiast sout
                System.out.println("Nie ma miejsc");
            }
        } else {
            System.out.println("Już sie zapisałeś");
        }
    }
}


