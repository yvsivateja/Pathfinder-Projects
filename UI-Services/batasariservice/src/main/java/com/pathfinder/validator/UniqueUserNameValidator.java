package com.pathfinder.validator;

import java.io.Serializable;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pathfinder.dao.UserDAO;

public class UniqueUserNameValidator implements
		ConstraintValidator<Unique, Serializable> {

	HttpSession session;

	@Autowired
	UserDAO userDAO;

	public void initialize(Unique unique) {

	}

	public boolean isValid(Serializable serializable,
			ConstraintValidatorContext constraintValidatorContext) {
		// TODO Auto-generated method stub
		if (!isStringEmpty(serializable)) {
			/*
			 * ServletRequestAttributes servletRequestAttributes =
			 * (ServletRequestAttributes) RequestContextHolder
			 * .currentRequestAttributes(); session =
			 * servletRequestAttributes.getRequest().getSession();
			 * 
			 * SessionBean sessionBean = (SessionBean) session
			 * .getAttribute("sessionUserDetails");
			 */
			System.out.println("Val : " + serializable);
			if (userDAO.isUniqueUserName(serializable.toString())) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean isStringEmpty(Serializable serializable) {

		if (serializable == null
				|| String.valueOf(serializable).trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
