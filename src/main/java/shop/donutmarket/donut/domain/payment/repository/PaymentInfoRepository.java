package shop.donutmarket.donut.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.donutmarket.donut.domain.payment.model.PaymentInfo;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {
}
