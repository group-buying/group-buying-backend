package shop.donutmarket.donut.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.donutmarket.donut.domain.board.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
}
