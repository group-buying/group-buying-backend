package shop.donutmarket.donut.domain.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.account.model.MyAccount;

public interface MyAccountRepository extends JpaRepository<MyAccount, Long>{
    @Query("select m from MyAccount m left join fetch m.user where m.user.id = :userId")
    Optional<MyAccount> findByUserId(@Param("userId") Long userId);
}
