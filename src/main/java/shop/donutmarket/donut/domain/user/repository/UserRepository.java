package shop.donutmarket.donut.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.donutmarket.donut.domain.user.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>{
    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("select u from User u join fetch u.rate where u.id = :id")
    Optional<User> findByIdJoinFetch(@Param("id") Long id);

    Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
