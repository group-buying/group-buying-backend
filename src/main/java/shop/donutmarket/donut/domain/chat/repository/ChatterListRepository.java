package shop.donutmarket.donut.domain.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.chat.model.ChatterList;
import shop.donutmarket.donut.domain.user.model.User;

public interface ChatterListRepository extends JpaRepository<ChatterList, Long>{

    @Query("select c from ChatterList c where c.chatroomId = chatroomId")
    List<ChatterList> findAllByChatroomId(@Param("chatroomId") Long chatroomId);

    @Query("select c from ChatterList c join fetch c.chatroomId where c.userId = :userId")
    List<ChatterList> findAllByUserId(@Param("userId") User userId);
    
}
