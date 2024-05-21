package uz.pdp.g34springbootsecurity.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.g34springbootsecurity.domain.UserEntity;
import uz.pdp.g34springbootsecurity.projection.UserProjection;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u")
    List<UserProjection> findAllUsers();

}
