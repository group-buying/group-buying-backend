package shop.donutmarket.donut.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.myPage.dto.MyPageResp;
import shop.donutmarket.donut.domain.payment.model.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    @Query("select p from Payment p left join fetch p.user u left join fetch p.event e " +
            "left join fetch u.rate where p.user.id =:userId")
    List<Payment> findByUserId(@Param("userId") Long userId);
}
