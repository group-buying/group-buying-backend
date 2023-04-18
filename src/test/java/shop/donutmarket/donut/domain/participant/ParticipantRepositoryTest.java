package shop.donutmarket.donut.domain.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.participant.repository.ParticipantRepository;

@DataJpaTest
@Transactional
public class ParticipantRepositoryTest {
    
    @Autowired
    private ParticipantRepository participantRepository;

    @BeforeEach
    void setUp(){
        Participant participant = Participant.builder().id(1L).eventId(1L).userId(1L).qty(2).createdAt(LocalDateTime.now()).limitTime(LocalDateTime.now()).statusCode(300).build();
        participantRepository.save(participant);
    }
    
    @Test
    void findById_Test(){
        // given
        Long id = 1L;
        
        // when
        Optional<Participant> participant = participantRepository.findById(id);
        
        // then
        assertNotNull(participant);
    }
    
    @Test
    void save_Test() {
        // given
        Long id = 2L;
        Participant participant = Participant.builder().id(id).eventId(1L).userId(2L).qty(2).createdAt(LocalDateTime.now()).limitTime(LocalDateTime.now()).statusCode(300).build();
        
        // when
        participantRepository.save(participant);

        // then
        assertNotNull(participantRepository.findById(id));
    }

    @Test
    void deleteById_Test(){
        // given
        Long id = 1L;
        
        // when
        participantRepository.deleteById(id);

        // then
        assertEquals(Optional.empty(), participantRepository.findById(id));
    }
}
