package com.k15t.pat.util;

import com.k15t.pat.model.User;
import org.springframework.stereotype.Component;

/**
 * Validation util class
 * @author Gayathri
 *
 */
@Component
public class RegistrationUtil
{
	
	/**
	 * This method matches the password and confirmPassword entered by the user
	 * @param user
	 * @return boolean
	 */
	public boolean matchPasswords(User user){
		if(user.getPassword().equals(user.getConfirmPassword())){
			return true;	
		}else{
			return false;
		}	
	}

}
