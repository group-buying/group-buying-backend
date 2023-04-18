package shop.donutmarket.donut.domain.blacklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import shop.donutmarket.donut.domain.blacklist.model.BlackList;
import shop.donutmarket.donut.domain.blacklist.repository.BlackListRepository;

@DataJpaTest
@Transactional
public class BlacklistRepositoryTest {
    
    @Autowired
    private BlackListRepository blackListRepository;

    @BeforeEach
    void setUp(){
        BlackList blackList = BlackList.builder().id(1L).userId(1L).blockedUserId(2L).createdAt(LocalDateTime.now()).build();
        blackListRepository.save(blackList);
    }
    
    @Test
    void findById_Test(){
        // given
        Long id = 1L;
        // when
        Optional<BlackList> blacklist = blackListRepository.findById(id);
        // then
        assertNotNull(blacklist);
    }
    
    
    @Test
    void save_Test(){
        // given
        Long id = 2L;
        BlackList blackList = BlackList.builder().id(id).userId(1L).blockedUserId(3L).createdAt(LocalDateTime.now()).build();
        
        // when
        blackListRepository.save(blackList);
        
        // then
        assertNotNull(blackListRepository.findById(id));
    }

    @Test
    void deleteById_Test(){
        // given
        Long id = 1L;

        // when
        blackListRepository.deleteById(id);

        // then
        assertEquals(Optional.empty(), blackListRepository.findById(id));
    }
}