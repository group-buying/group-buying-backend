package shop.donutmarket.donut.domain.participant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.participant.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{

    @Query("select p from Participant p where p.user.id = :userId")
    List<Participant> findAllByUserId(@Param("userId") Long userId);
    
    @Query("select p from Participant p left join fetch p.user u left join fetch p.event left join fetch u.rate where p.user.id = :userId")
    List<Participant> findAllByUserIdwithEvent(@Param("userId") Long userId);

    @Query("select p from Participant p left join fetch p.user u left join fetch p.event left join fetch u.rate where p.id = :id")
    Optional<Participant> findByIdJoinFetch(@Param("id") Long id);

    @Query("select p from Participant p left join fetch p.user u left join fetch p.event left join fetch u.rate where p.user.id = :userId and p.event.id = :eventId")
    Optional<Participant> findByUserIdAndEvendId(@Param("userId") Long userId, @Param("eventId") Long eventId);
}
