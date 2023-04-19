package shop.donutmarket.donut.domain.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.board.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

    @Query("select * from tag where board_id = :id")
    List<Tag> findAllByBoardId(@Param("id") Long id);
    
}
