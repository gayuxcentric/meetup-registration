package com.k15t.pat.controller;

import javax.validation.Valid;

import com.k15t.pat.exception.RegistrationException;
import com.k15t.pat.model.User;
import com.k15t.pat.service.RegistrationService;
import com.k15t.pat.util.RegistrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * This Controller is to perform user registration rest operations
 *
 * @author gayathri
 */
@Controller
@RequestMapping
public class RegistrationController
{
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private RegistrationUtil registrationUtil;

    /**
     * Redirects to user registration landing page template
     * @return Registration Form
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "redirect:/registration.html";
    }


    /**
     * This method returns the list of all stored registrations
     *
     * @return
     */
    @RequestMapping(value = "/registeredUsers",method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("registeredUsers", registrationService.list());
        return "success";
    }


    /**
     * This method saves the user data to the database
     * @param bindingResult BindingResult checks for any errors in the fields and form is returned to the user if errors
     * @param user user data object
     * @return error page or success page
     */
    @RequestMapping(value = "/save/registration", method = RequestMethod.POST)
    public String newRegistration(Model model, @Valid @ModelAttribute User user, BindingResult bindingResult) {

        boolean passwordMatched = registrationUtil.matchPasswords(user);
        if (!passwordMatched) {
            FieldError error = new FieldError("confirmPassword", "confirmPassword", "passwords are not identical");
            model.addAttribute("passwordMismatch", true);
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors()) {
            return "error";
        }
        else {
            try {
                User userNew = registrationService.saveUser(user);
                Iterable<User> registeredUsers = registrationService.list();
                model.addAttribute("registeredUsers", registeredUsers);
                model.addAttribute("userNew", userNew);
                model.addAttribute("user", user);
                model.addAttribute("success", true);
                return "success";
            } catch (RegistrationException e) {
                model.addAttribute("userExists", true);
                model.addAttribute("userNew", user);
                return "error";
            }
        }
    }

    /**
     * Loads the user registration landing page template
     * @return Registration Form
     */
    @RequestMapping(value = "/registration.html", method = RequestMethod.GET)
    public String registration(Model model) {
        return "registration";
    }

}
