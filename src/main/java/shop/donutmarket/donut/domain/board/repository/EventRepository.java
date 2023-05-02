package shop.donutmarket.donut.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import shop.donutmarket.donut.domain.board.model.Event;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>{
}
