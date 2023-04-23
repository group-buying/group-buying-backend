package shop.donutmarket.donut.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.myPage.dto.MyPageResp;
import shop.donutmarket.donut.domain.payment.model.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    @Query("select b from Board b where b.organizer.id =:organizerId")
    List<MyPageResp.MyBoardDTO> findByOrganizerId(@Param("organizerId") Long organizerId);
}
