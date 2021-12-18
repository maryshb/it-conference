package sii.itconference.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sii.itconference.model.Block;
import sii.itconference.repository.IBlockRepository;
import sii.itconference.services.IBlockService;

import java.util.List;

@Service
public class BlockServiceImpl implements IBlockService {

    private IBlockRepository blockRepository;

    @Autowired
    public BlockServiceImpl(IBlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @Override
    public List<Block> findAll() {
        return blockRepository.findAll();
    }

}
