package ro.tuc.ds2020.dtos.validators;

import ro.tuc.ds2020.controllers.handlers.exceptions.model.RegistrationException;

public class RegistrationValidator {

    public static boolean validatePasswords(String password, String confirmationPassword) {
        if (!password.equals(confirmationPassword) || password.equals(""))
            throw new IllegalArgumentException(RegistrationException.PASSWORD_NOT_MATCH_EXCEPTION);
        else return true;
    }
}
