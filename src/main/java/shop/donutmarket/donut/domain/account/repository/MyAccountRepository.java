package shop.donutmarket.donut.domain.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.account.model.MyAccount;

public interface MyAccountRepository extends JpaRepository<MyAccount, Long>{
    
}
