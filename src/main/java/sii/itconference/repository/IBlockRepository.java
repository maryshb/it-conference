package sii.itconference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sii.itconference.model.Block;

@Repository
public interface IBlockRepository extends JpaRepository<Block, Long> {
}
