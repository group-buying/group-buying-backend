package shop.donutmarket.donut.domain.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.board.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

    @Query("select t from Tag t where t.boardId = :BoardId")
    List<Tag> findAllByBoardId(@Param("BoardId") Long BoardId);

    @Modifying
    @Query("delete from Tag t where t.boardId = :BoardId")
    void deleteAllByBoardId(@Param("BoardId") Long BoardId);
    
}
