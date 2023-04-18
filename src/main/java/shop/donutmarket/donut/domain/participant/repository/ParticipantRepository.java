package shop.donutmarket.donut.domain.participant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.donutmarket.donut.domain.participant.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{
    
}
