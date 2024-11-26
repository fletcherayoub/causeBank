package causebankgrp.causebank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import causebankgrp.causebank.Entity.User;
import java.util.Optional;
import java.util.UUID;

// 4
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
