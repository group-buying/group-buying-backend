package shop.donutmarket.donut.domain.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.board.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

    @Query("select b from Board b where b.event.id = :eventId")
    Optional<Board> findByEventId(@Param("eventId") Long eventId);

    @Query("select b from Board b left join fetch b.category c left join fetch b.event e left join fetch b.organizer o left join fetch o.rate where b.organizer.id =:organizerId")
    List<Board> findByOrganizerId(@Param("organizerId") Long organizerId);

    @Query("select b from Board b join fetch b.event where b.id = :boardId")
    Optional<Board> findByIdWithEvent(@Param("boardId") Long boardId);

    @Query("select b from Board b left join fetch b.category c left join fetch b.event e left join fetch b.organizer o left join fetch o.rate where b.id = :boardId")
    Optional<Board> findByIdWithAll(@Param("boardId") Long boardId);

    @Query("select b from Board b where b.statusCode in(200, 201, 202)")
    List<Board> findBoardAndStatusCode();
}
