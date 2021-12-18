package sii.itconference.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "t_lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;
    private String name;
    private String description;
    private int seats; // max 5 miejsc
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "t_block")
    private Block block;
}
