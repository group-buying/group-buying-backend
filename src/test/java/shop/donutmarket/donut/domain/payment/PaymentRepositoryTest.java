package shop.donutmarket.donut.domain.payment;

import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import shop.donutmarket.donut.domain.account.model.MyAccount;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.participant.model.Participant;
import shop.donutmarket.donut.domain.payment.model.Payment;
import shop.donutmarket.donut.domain.payment.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PaymentRepositoryTest {
    
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private TestEntityManager tem; // 테스트하기 위한 EntityManager

    @BeforeEach
    void setUp(){
        autoincrementReset(); // autoincrement 보장해주는 메서드
        dataSetting(); // 초기 dummy 데이터 세팅
        tem.clear(); // 영속성 컨텍스트 비우기
    }

    @Test
    @DisplayName("Payment 개별 id조회 테스트")
    void findById_Test(){
        // given
        Long id = 1L;
        
        // when
        Optional<Payment> payment = paymentRepository.findById(id);

        // then
        payment.ifPresent(payment1 -> {
            assertNotNull(payment1);
            assertEquals(payment1.getId(), 1L);
        });
    }
    
    @Test
    @DisplayName("Payment 생성 테스트")
    void save_Test() {
        // given
        Participant participant = Participant.builder().build();
        StatusCode statusCode = StatusCode.builder().build();
        Payment payment = Payment.builder().participant(participant).paymentType("직거래").statusCode(statusCode).confirmed(true).createdAt(LocalDateTime.now()).build();

        // when
        paymentRepository.save(payment);

        // then
        assertNotNull(payment);
        assertEquals(payment.getId(), 2L);
    }

    @Test
    @DisplayName("Payment 삭제 테스트")
    void deleteById_Test(){
        // given
        Long id = 1L;
        Payment payment = tem.find(Payment.class, id);

        // when
        if (payment != null) {
            tem.remove(payment);
            tem.flush();
        }

        // then
        assertNull(tem.find(Payment.class, id));
    }

    @Test
    @DisplayName("Payment 수정 테스트")
    void updateById_Test() {
        // given
        Long id = 1L;
        Payment payment = tem.find(Payment.class, id);
        Participant participant = Participant.builder().build();
        StatusCode statusCode = StatusCode.builder().build();
        LocalDateTime time = LocalDateTime.now();
        tem.persist(participant);
        tem.persist(statusCode);

        // when
        payment.updatePayment(participant, "직거래", statusCode, false, time);
        tem.persistAndFlush(payment);

        // then
        assertEquals(payment.getPaymentType(), "직거래");
    }

    private void dataSetting() {
        Participant participant = Participant.builder().build();
        StatusCode statusCode = StatusCode.builder().build();
        Payment payment = Payment.builder().id(1L).participant(participant).paymentType("직거래").statusCode(statusCode).confirmed(true).createdAt(LocalDateTime.now()).build();
        paymentRepository.save(payment);
    }

    private void autoincrementReset() {
        em.createNativeQuery("ALTER TABLE payment ALTER COLUMN id RESTART WITH 1").executeUpdate();
    }
}
