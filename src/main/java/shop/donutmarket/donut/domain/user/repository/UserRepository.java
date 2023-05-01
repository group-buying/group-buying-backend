package shop.donutmarket.donut.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.user.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>{
    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);
    
    Optional<User> findByProviderAndProviderId(String provider, String providerId);
    
    
    @Query("select u from User u join fetch u.rate join fetch u.statusCode")
    List<User> findAllWithAllArg();
}
