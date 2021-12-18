package sii.itconference.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "t_reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    @OneToOne
    private User user;
    @OneToOne
    private Lecture lecture;
}

