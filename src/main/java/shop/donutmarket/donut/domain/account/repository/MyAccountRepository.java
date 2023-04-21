package shop.donutmarket.donut.domain.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.account.model.MyAccount;
import shop.donutmarket.donut.domain.user.model.User;

import java.util.Optional;

public interface MyAccountRepository extends JpaRepository<MyAccount, Long>{
    @Query("select m from MyAccount m where m.user.id = :userId")
    Optional<MyAccount> findByUserId(@Param("userId") Long userId);
}
