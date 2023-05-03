package shop.donutmarket.donut.domain.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    @Query("select p from Payment p left join fetch p.user u left join fetch p.event e " +
            "left join fetch u.rate where p.user.id =:userId")
    List<Payment> findByUserId(@Param("userId") Long userId);
}
