package shop.donutmarket.donut.domain.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import shop.donutmarket.donut.domain.payment.model.Payment;
import shop.donutmarket.donut.domain.payment.repository.PaymentRepository;

@DataJpaTest
@Transactional
public class PaymentRepositoryTest {
    
    @Autowired
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp(){
        Payment payment = Payment.builder().id(1L).participantId(1L).paymentType("직거래").statusCode(400).confirmed(true).createdAt(LocalDateTime.now()).build();
        paymentRepository.save(payment);
    }

    @Test
    void findById_Test(){
        // given
        Long id = 1L;
        
        // when
        Optional<Payment> payment = paymentRepository.findById(id);
        
        // then
        assertNotNull(payment);
    }
    
    @Test
    void save_Test() {
        // given
        Long id = 2L;
        Payment payment = Payment.builder().id(id).participantId(2L).paymentType("직거래").statusCode(400).confirmed(true).createdAt(LocalDateTime.now()).build();
        
        // when
        paymentRepository.save(payment);

        // then
        assertNotNull(paymentRepository.findById(id));
    }

    @Test
    void deleteById_Test(){
        // given
        Long id = 1L;
        
        // when
        paymentRepository.deleteById(id);

        // then
        assertEquals(Optional.empty(), paymentRepository.findById(id));
    }
}
