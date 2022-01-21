package sii.itconference.services.impl;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sii.itconference.model.Block;
import sii.itconference.model.Lecture;
import sii.itconference.model.User;
import sii.itconference.model.dto.ReservationDto;
import sii.itconference.repository.IBlockRepository;
import sii.itconference.repository.ILectureRepository;
import sii.itconference.repository.IUserRepository;
import sii.itconference.services.IBlockService;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


@DataJpaTest
@ComponentScan(basePackages = {"sii.itconference"})
@RunWith(SpringRunner.class)
class BlockServiceImplTest {

    @Autowired
    IBlockRepository blockRepository;

    @Autowired
    ILectureRepository lectureRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IBlockService blockService;

    @Autowired
    private TestEntityManager entityManager;

    List<Block> blocks;

    @Before
    public void setUp() {

    }



    @Test
    void findAll_Should_Find_No_Blocks_For_Empty_Repository() {
        List<Block> blocks = blockRepository.findAll();

        Assertions.assertEquals(0, blocks.size());
    }


    @Test
    void findAll_Should_Save_And_Return_Block() {
        Block block1 = new Block(1L,new GregorianCalendar(2021, Calendar.JUNE, 1, 10, 0, 0), new GregorianCalendar(2021, Calendar.JUNE, 1, 11, 45, 0), "1:45");
        blockRepository.save(block1);
        List<Block> blocks = blockRepository.findAll();

       Assertions.assertEquals(1, blocks.size());
       Assertions.assertTrue(blocks.contains(block1));
    }

    @Test
    void getBlockByBlockId_Should_Find_Block_By_Id() {
        Block block1 = new Block(1L,new GregorianCalendar(2021, Calendar.JUNE, 1, 10, 0, 0), new GregorianCalendar(2021, Calendar.JUNE, 1, 11, 45, 0), "1:45");
        Block block2 = new Block(2L,new GregorianCalendar(2021, Calendar.JUNE, 1, 10, 0, 0), new GregorianCalendar(2021, Calendar.JUNE, 1, 11, 45, 0), "1:45");

        blockRepository.save(block1);
        blockRepository.save(block2);

        Block foundBlock = blockService.getBlockByBlockId(block2.getBlockId());

        Assertions.assertEquals(foundBlock, block2 );
    }


    @Test
    void isLectureInBlock_Should_Return_True_If_Lecture_Is_In_Block() {
        Block block1 = new Block(1L,new GregorianCalendar(2021, Calendar.JUNE, 1, 10, 0, 0), new GregorianCalendar(2021, Calendar.JUNE, 1, 11, 45, 0), "1:45");
        blockRepository.save(block1);

        Lecture lecture1 = new Lecture(1L, "Programowanie", "Wykład o programowaniu w języku Java",10, block1);
        lectureRepository.save(lecture1);

        User user1 = new User(1L, "jannowak", "jannowak@gmail.com");
        userRepository.save(user1);

        ReservationDto reservationDto1 = new ReservationDto(1L, user1, lecture1, 1L, 1L, "jannowak", "jannowak@gmail.com");

        Assertions.assertTrue(blockService.isLectureInBlock(reservationDto1));

    }
}