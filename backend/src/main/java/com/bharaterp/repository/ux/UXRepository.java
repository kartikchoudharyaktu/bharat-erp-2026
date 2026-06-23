package com.bharaterp.repository.ux;

import com.bharaterp.model.ux.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UXRepository extends JpaRepository<UserPreference, Long> {
    
    Optional<UserPreference> findByUserId(Long userId);
}
