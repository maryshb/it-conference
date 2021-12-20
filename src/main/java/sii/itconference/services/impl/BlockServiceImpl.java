package sii.itconference.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sii.itconference.model.Block;
import sii.itconference.model.Lecture;
import sii.itconference.model.dto.ReservationDto;
import sii.itconference.repository.IBlockRepository;
import sii.itconference.repository.ILectureRepository;
import sii.itconference.services.IBlockService;

import java.util.List;

@Service
public class BlockServiceImpl implements IBlockService {

    private IBlockRepository blockRepository;
    private ILectureRepository lectureRepository;
    private ModelMapper modelMapper;

    @Autowired
    public BlockServiceImpl(IBlockRepository blockRepository, ILectureRepository lectureRepository, ModelMapper modelMapper) {
        this.blockRepository = blockRepository;
        this.lectureRepository = lectureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Block> findAll() {
        return blockRepository.findAll();
    }

    @Override
    public Block getBlockByBlockId(Long blockId) {
        return blockRepository.getById(blockId);
    }

    @Override
    public boolean isLectureInBlock(ReservationDto reservationDto) {
        Block block = modelMapper.map(reservationDto, Block.class);

        List<Lecture> allLecturesInBlock = lectureRepository.getLecturesByBlock(block);
        return allLecturesInBlock.stream()
                .map(Lecture::getBlock)
                .map(Block::getBlockId)
                .anyMatch(blockId -> blockId.equals(block.getBlockId()));
    }
}