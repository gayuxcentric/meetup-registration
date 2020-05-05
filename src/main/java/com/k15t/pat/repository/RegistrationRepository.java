package com.k15t.pat.repository;

import com.k15t.pat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository to perform CRUD operations
 *
 * @author gayathri
 */
@Repository
public interface RegistrationRepository extends JpaRepository<User, Long> {
    /**
     * Query DB for an existing user with the provided username
     * @param email
     * @return the user
     */
    User findByEmail(String email);
}
