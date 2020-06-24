package net.iqbusiness.app.registrator.domain.validator;

import net.iqbusiness.app.registrator.model.dto.UserDTO;
import net.iqbusiness.commons.util.data.IQValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator extends IQValidator<UserDTO> {

    @Override
    protected List<String> doFieldValidation(UserDTO var1, boolean var2) {
        final List<String> errors = new ArrayList<>();

        //TODO: validateFirstName
        //TODO: validateSurname
        //TODO: validateIdentity
        //TODO: validateTelephone

        return errors;
    }
}
