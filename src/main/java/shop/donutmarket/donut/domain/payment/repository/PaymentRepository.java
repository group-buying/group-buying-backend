package shop.donutmarket.donut.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
}
