package com.k15t.pat.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private static Validator validator;

    @Before
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void createUserWithValidInputs() {
        User user = new User();
        user.setName("Gayathri");
        user.setEmail("gayathrisekar@test.com");
        user.setAddress("12,xyz");
        user.setCity("kornwestheim");
        user.setPassword("12345678");
        user.setPhone("0123456789");
        user.setCountry("DE");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void createUserInvalidEmail() {
        User user = new User();
        user.setName("Gayathri");
        user.setEmail("gayathrisekar");
        user.setAddress("12,xyz");
        user.setCity("kornwestheim");
        user.setPassword("12345678");
        user.setPhone("0123456789");
        user.setCountry("DE");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void createUserInvalidName() {
        User user = new User();
        user.setName("X");
        user.setEmail("gayathrisekar");
        user.setAddress("12,xyz");
        user.setCity("kornwestheim");
        user.setPassword("12345678");
        user.setPhone("0123456789");
        user.setCountry("DE");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void createUserInvalidNameWithNumbers() {
        User user = new User();
        user.setName("X1234");
        user.setEmail("gayathrisekar@gmail.com");
        user.setAddress("12,xyz");
        user.setCity("kornwestheim");
        user.setPhone("0123456789");
        user.setPassword("12345678");
        user.setCountry("DE");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void createUserInvalidPassword()  {
        User user = new User();
        user.setName("X1234");
        user.setEmail("gayathrisekar@gmail.com");
        user.setAddress("12,xyz");
        user.setCity("kornwestheim");
        user.setPassword("12");
        user.setPhone("0123456789");
        user.setCountry("DE");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void createUserInvalidPhone() {
        User user = new User();
        user.setName("X1234");
        user.setEmail("gayathrisekar@gmail.com");
        user.setAddress("12,xyz");
        user.setCity("kornwestheim");
        user.setPassword("12");
        user.setPhone("012345678K()=9");
        user.setCountry("DE");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

}
