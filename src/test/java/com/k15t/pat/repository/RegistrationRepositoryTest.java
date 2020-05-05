package com.k15t.pat.repository;

import com.k15t.pat.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RegistrationRepositoryTest
{

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void saveUser(){
        User user = new User();
        user.setName("XYZ");
        user.setEmail("gayathrisekar@gmail.com");
        user.setAddress("12,xyz");
        user.setCity("kornwestheim");
        user.setPassword("12345678");
        user.setPhone("0123456789");
        user.setCountry("DE");
        testEntityManager.persist(user);
        assertNotNull(registrationRepository.getOne(1L));
    }

}
