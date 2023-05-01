package shop.donutmarket.donut.domain.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.donutmarket.donut.domain.admin.model.Category;
import shop.donutmarket.donut.domain.admin.model.StatusCode;

import java.util.Optional;

public interface StatusCodeRepository extends JpaRepository<StatusCode, Long> {
    Optional<StatusCode> findByStatusNumber(Long statusNumber);
}
