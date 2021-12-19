package sii.itconference.model.dto;

import lombok.Data;
import sii.itconference.model.Lecture;
import sii.itconference.model.User;

@Data
public class ReservationDto {
    private Long reservationId;
    private User user;
    private Lecture lecture;
    private Long lectureId;
    private Long blockId;
    private String username;
    private String email;
}
