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
    private EmailSenderServiceImpl emailSenderService;

    @Autowired
    public ReservationServiceImpl(IReservationRepository reservationRepository, IUserService userService,
                                  ILectureService lectureService, IBlockService blockService, ModelMapper modelMapper, EmailSenderServiceImpl emailSenderService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.lectureService = lectureService;
        this.blockService = blockService;
        this.modelMapper = modelMapper;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public List<Reservation> findReservationsByUser(User user) {
        return this.reservationRepository.findReservationsByUser(user);
    }

    @Override
    public boolean existsReservationByReservationId(Long reservationId) {
        return this.reservationRepository.existsReservationByReservationId(reservationId);
    }

    @Override
    public void cancelReservation(ReservationDto reservationDto) {
        this.reservationRepository.deleteReservationByReservationId(reservationDto.getReservationId());
    }

    @Override
    public void saveReservation(ReservationDto reservationDto) {
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);

        User user = getUserForReservation(reservationDto);

        if (blockService.isLectureInBlock(reservationDto)) {
            Block block = blockService.getBlockByBlockId(reservationDto.getBlockId());

            if (!reservationRepository.existsReservationByUserAndBlock(user, block)) {

                Lecture lecture = lectureService.getLectureByLectureId(reservationDto.getLectureId());

                int seats = lecture.getSeats();

                if (seats > 0) {
                    if (user.getEmail().equals(reservationDto.getEmail())) {
                        reservation.setUser(user);
                        reservation.setLecture(lectureService.getLectureByLectureId(reservationDto.getLectureId()));

                        this.reservationRepository.save(reservation);
                        lecture.setSeats(seats - 1);
                        this.lectureService.saveLecture(lecture);
                        this.emailSenderService.sendMail(user, reservation);
                    }
                } else {
                    System.out.println("No seats left");
                }
            } else {
                System.out.println("You are already signed up to this block");
            }
        } else {
            System.out.println("There is no such a lecture in this block");
        }
    }

    private User getUserForReservation(ReservationDto reservationDto) {
        if (!userService.isUserExistByUsername(reservationDto.getUsername())) {
            userService.saveUser(reservationDto);
        }
        return userService.getUserByUsername(reservationDto.getUsername());
    }
}