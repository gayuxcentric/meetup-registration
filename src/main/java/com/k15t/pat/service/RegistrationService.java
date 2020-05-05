package com.k15t.pat.service;

import com.k15t.pat.exception.RegistrationException;
import com.k15t.pat.model.User;

/**
 * Registration service to perform crud operations
 *
 * @author gayathri
 */
public interface RegistrationService
{
    /**
     * Save a new user to DB
     * @param user
     * @return User
     * @throws RegistrationException
     */
    User saveUser(User user) throws RegistrationException;

    /**
     * This method returns the list of all registered users
     * @return list of Registration
     */
    Iterable<User> list();

}
