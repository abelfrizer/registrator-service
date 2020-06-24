package net.iqbusiness.app.registrator.domain.validator;

import net.iqbusiness.app.registrator.model.dao.UserJpaRepo;
import net.iqbusiness.app.registrator.model.dto.UserDTO;
import net.iqbusiness.app.registrator.model.entity.User;
import net.iqbusiness.commons.util.data.IQValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserValidator extends IQValidator<UserDTO> {

    private final UserJpaRepo userJpaRepo;

    @Autowired
    public UserValidator(final UserJpaRepo userJpaRepo) {
        this.userJpaRepo = userJpaRepo;
    }

    @Override
    protected List<String> doFieldValidation(UserDTO dto, boolean existingRecord) {
        final List<String> errors = new ArrayList<>();

        validateFirstName(dto, errors);
        validateSurname(dto, errors);
        validateIdentity(dto, errors, existingRecord);
        validateTelephone(dto, errors);

        return errors;
    }

    private void validateTelephone(UserDTO dto, List<String> errors) {
        final String telNo = StringUtils.trimToEmpty(dto.getTelephone());
        //length
        if (StringUtils.isBlank(telNo) || StringUtils.trimToEmpty(telNo).length() != 10) {
            errors.add("'Tel No' is mandatory and should be 10 characters long");
        } else if(!StringUtils.isNumeric(telNo)){
            //numeric numbers only
            errors.add("'Tel No' should be made up of numbers only");
        }
    }

    private void validateIdentity(UserDTO dto, List<String> errors, boolean existingRecord) {
        final String identity = dto.getIdentity();
        if (StringUtils.isBlank(identity) || StringUtils.trimToEmpty(identity).length() != 6) {
            errors.add("'ID Number' is mandatory and should be 6 characters long");
        } else {
            Optional<User> optionalUser = userJpaRepo.findByUserIdentity(identity);
            //new duplicate
            if (!existingRecord && optionalUser.isPresent()) {
                errors.add(String.format("User ID [%s] already exists", identity));
            }
            //duplicating another existing record
            if (existingRecord && optionalUser.isPresent()) {
                User user = optionalUser.get();
                if(!Objects.equals(user.getUuid(), dto.getUuid())){
                    //diff UUID
                    errors.add(String.format("User ID [%s] already exists for a different user", identity));
                }
            }
        }
    }

    private void validateSurname(UserDTO dto, List<String> errors) {
        final String surname = StringUtils.trimToEmpty(dto.getSurname());
        if (StringUtils.isBlank(surname) || surname.length() < 3) {
            errors.add("'Surname' should be 3 or more characters long");
        }
    }

    private void validateFirstName(UserDTO dto, List<String> errors) {
        final String firstName = StringUtils.trimToEmpty(dto.getFirstName());
        if (StringUtils.isBlank(firstName) || firstName.length() < 3) {
            errors.add("'First Name' should be 3 or more characters long");
        }
    }
}
