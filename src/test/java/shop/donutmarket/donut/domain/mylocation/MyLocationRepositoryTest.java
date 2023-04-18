package shop.donutmarket.donut.domain.mylocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import shop.donutmarket.donut.domain.myLocation.model.MyLocation;
import shop.donutmarket.donut.domain.myLocation.repository.MyLocationRepository;
import shop.donutmarket.donut.domain.user.UserConst;

@DataJpaTest
@Transactional
public class MyLocationRepositoryTest {
    
    @Autowired
    private MyLocationRepository myLocationRepository;

    @BeforeEach
    void setUp(){
        MyLocation myLocation = MyLocation.builder().id(1L).user(new UserConst()).state("부산광역시").city("부산진구").town("부전동").build();
        myLocationRepository.save(myLocation);
    }

    @Test
    void findById_Test(){
        // given
        Long id = 1L;
        
        // when
        Optional<MyLocation> myLocation = myLocationRepository.findById(id);
        
        // then
        assertNotNull(myLocation);
    }

    @Test
    void save_Test() {
        // given
        Long id = 2L;
        MyLocation myLocation = MyLocation.builder().id(2L).user(new UserConst()).state("부산광역시").city("부산진구").town("부암동").build();
        
        // when
        myLocationRepository.save(myLocation);

        // then
        assertNotNull(myLocationRepository.findById(id));
    }

    @Test
    void deleteById_Test(){
        // given
        Long id = 1L;
        
        // when
        myLocationRepository.deleteById(id);

        // then
        assertEquals(Optional.empty(), myLocationRepository.findById(id));
    }
}
