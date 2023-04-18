package shop.donutmarket.donut.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.board.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
