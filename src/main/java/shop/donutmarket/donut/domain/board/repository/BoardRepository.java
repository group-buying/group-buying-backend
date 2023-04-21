package shop.donutmarket.donut.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.board.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

    @Query("select b.organizer.id from Board b where b.event.Id = :eventId")
    public Long findByEventId(@Param("eventId") Long eventId);
}
