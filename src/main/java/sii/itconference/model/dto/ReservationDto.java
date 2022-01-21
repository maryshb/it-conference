package sii.itconference.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sii.itconference.model.Lecture;
import sii.itconference.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Long reservationId;
    private User user;
    private Lecture lecture;
    private Long lectureId;
    private Long blockId;
    private String username;
    private String email;
}
