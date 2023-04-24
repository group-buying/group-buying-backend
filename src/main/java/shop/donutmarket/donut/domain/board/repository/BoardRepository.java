package shop.donutmarket.donut.domain.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.myPage.dto.MyPageResp;

public interface BoardRepository extends JpaRepository<Board, Long>{

    @Query("select b from Board b where b.event.id = :eventId")
    Optional<Board> findByEventId(@Param("eventId") Long eventId);

    @Query("select b from Board b where b.organizer.id =:organizerId")
    List<MyPageResp.MyBoardDTO> findByOrganizerId(@Param("organizerId") Long organizerId);
}
