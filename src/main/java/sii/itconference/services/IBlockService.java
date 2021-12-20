package sii.itconference.services;

import sii.itconference.model.Block;
import sii.itconference.model.dto.ReservationDto;

import java.util.List;

public interface IBlockService {
    List<Block> findAll();

    Block getBlockByBlockId(Long blockId);

    boolean isLectureInBlock(ReservationDto reservationDto);
}