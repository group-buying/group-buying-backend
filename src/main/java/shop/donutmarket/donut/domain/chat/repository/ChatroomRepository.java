package shop.donutmarket.donut.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.donutmarket.donut.domain.chat.model.Chatroom;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long>{
    
}
