package shop.donutmarket.donut.domain.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import shop.donutmarket.donut.domain.user.model.User;
import shop.donutmarket.donut.domain.user.repository.UserRepository;

@DataJpaTest
@Transactional
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        User user1 = User.builder().id(1L).username("ssar").password("1234").email("ssar@ssar").name("ssar").rateId(1L).role("user").statusCode(100).createdAt(LocalDateTime.now()).build();
        userRepository.save(user1);
    }

    @Test
    void findById_Test(){
        // given
        Long id = 1L;
        
        // when
        Optional<User> user = userRepository.findById(id);
        
        // then
        assertNotNull(user);
    }
    
    @Test
    void save_Test() {
        // given
        Long id = 2L;
        User user = User.builder().id(2L).username("cos").password("1234").email("cos@").name("cos").rateId(2L).role("user").statusCode(100).createdAt(LocalDateTime.now()).build();
        
        // when
        userRepository.save(user);
        
        // then
        assertNotNull(userRepository.findById(id));
    }

    @Test
    void deleteById_Test(){
        // given
        Long id = 1L;
        
        // when
        userRepository.deleteById(id);

        // then
        assertEquals(Optional.empty(), userRepository.findById(id));
    }
}
