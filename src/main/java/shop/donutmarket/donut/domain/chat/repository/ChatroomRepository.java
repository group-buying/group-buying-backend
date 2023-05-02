package shop.donutmarket.donut.domain.chat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.chat.model.Chatroom;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long>{
    
    @Query("select c from Chatroom c join fetch c.event where c.id = :chatroomId")
    Optional<Chatroom> findByIdwithEvent(@Param("chatroomId") Long chatroomId);
}
