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

    @Query("select b from Board b join fetch b.event where b.id = :boardId")
    Optional<Board> findByIdWithEvent(@Param("boardId") Long boardId);

    @Query("select b from Board b join fetch b.category c join fetch b.event e join fetch b.organizer o join fetch o.rate join fetch o.statusCode join fetch b.statusCode where b.id = :boardId")
    Optional<Board> findByIdWithAll(@Param("boardId") Long boardId);
}
