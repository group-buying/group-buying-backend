package shop.donutmarket.donut.domain.blacklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.blacklist.model.BlackList;

public interface BlackListRepository extends JpaRepository<BlackList, Long>{
    
}
