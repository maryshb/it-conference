package sii.itconference.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "t_block")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blockId;
    private Date startTime;
    private Date endTime;
    private String duration;
}