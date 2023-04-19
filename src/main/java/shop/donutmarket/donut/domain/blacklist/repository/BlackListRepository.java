package shop.donutmarket.donut.domain.blacklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.donutmarket.donut.domain.blacklist.model.Blacklist;

public interface BlackListRepository extends JpaRepository<Blacklist, Long>{
    
}
