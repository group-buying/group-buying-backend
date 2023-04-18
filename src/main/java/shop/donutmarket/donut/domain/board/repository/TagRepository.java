package shop.donutmarket.donut.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.board.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
    
}
