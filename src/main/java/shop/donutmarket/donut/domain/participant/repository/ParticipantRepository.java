package shop.donutmarket.donut.domain.participant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.participant.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{

    @Query("select p from Participant p where p.user.id = :userId")
    List<Participant> findAllByUserId(@Param("userId") Long userId);
    
}
