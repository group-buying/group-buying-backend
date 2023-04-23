package shop.donutmarket.donut.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.myPage.dto.MyPageResp;

import javax.swing.plaf.PanelUI;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>{

    @Query("select b.organizer.id from Board b where b.event.id = :eventId")
    public Long findByEventId(@Param("eventId") Long eventId);

    @Query("select b from Board b where b.organizer.id =:organizerId")
    List<MyPageResp.MyBoardDTO> findByOrganizerId(@Param("organizerId") Long organizerId);
}
