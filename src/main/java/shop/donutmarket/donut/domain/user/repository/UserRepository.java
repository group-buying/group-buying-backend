package shop.donutmarket.donut.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.user.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
