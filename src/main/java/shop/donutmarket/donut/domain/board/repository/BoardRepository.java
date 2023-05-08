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

    @Query("select b from Board b left join fetch b.category c left join fetch b.event e left join fetch b.organizer o left join fetch o.rate where b.id = :boardId")
    Optional<Board> findByIdWithEvent(@Param("boardId") Long boardId);

    @Query("select b from Board b left join fetch b.category c left join fetch b.event e left join fetch b.organizer o left join fetch o.rate where b.id = :boardId")
    Optional<Board> findByIdWithAll(@Param("boardId") Long boardId);

    @Query("select b from Board b where b.statusCode in(200, 201, 202)")
    List<Board> findBoardAndStatusCode();

    @Query("select b.id FROM Board b where (b.id IN (SELECT b2.id FROM Board b2 WHERE b2.title LIKE %:searchWord% or b2.content like %:searchWord%) OR b.id IN (SELECT t.boardId FROM Tag t WHERE t.comment LIKE %:searchWord%)) AND b.statusCode IN (200, 201, 202)")
    List<Long> findIdsBySearchWord(@Param("searchWord") String searchWord);
    
    @Query("select b.id from Board b where b.state = :state and b.city = :city and b.town = :town and b.statusCode in(200, 201, 202)")
    List<Long> findByLocation(@Param("state") String state, @Param("city") String city, @Param("state") String town);

    @Query("select b from Board b left join fetch b.category c left join fetch b.event e left join fetch b.organizer o left join fetch o.rate where b.category.id = :categoryId and b.statusCode in(200, 201, 202)")
    List<Board> findByCategory(@Param("categoryId") Long categoryId);

    @Query("select b from Board b left join fetch b.category c left join fetch b.event e left join fetch b.organizer o left join fetch o.rate where b.state = :state and b.city = :city and b.town = :town and b.category.id = :categoryId and b.statusCode in(200, 201, 202)")
    List<Board> findByMain(@Param("state") String state, @Param("city") String city, @Param("town") String town, @Param("categoryId") Long categoryId);
}
