package com.k15t.pat.service;

import com.k15t.pat.exception.RegistrationException;
import com.k15t.pat.model.User;
import com.k15t.pat.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Registration service to perform crud operations
 *
 * @author Gayathri
 */
@Service
public class RegistrationServiceImpl implements RegistrationService
{
    @Autowired
    private final RegistrationRepository registrationRepository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.registrationRepository = registrationRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Save a new user to DB
     * @param user
     * @return User
     * @throws RegistrationException
     */
    @Override
    public User saveUser(User user) throws RegistrationException
    {
        /* Validate if email id has been registered already */
        User isRegisteredUser = registrationRepository.findByEmail(user.getEmail());
        if (isRegisteredUser != null) {
            throw new RegistrationException("Registration Already Exists",user.getName());
        }
        else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return registrationRepository.save(user);
        }
    }

    /**
     * This method returns the list of all registered users
     * @return list of Registration
     */
    @Override
    public Iterable<User> list() {
        return registrationRepository.findAll();
    }

}
