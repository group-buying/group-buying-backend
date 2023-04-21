package shop.donutmarket.donut.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.donutmarket.donut.domain.board.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
