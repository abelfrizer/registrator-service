package net.iqbusiness.app.registrator.domain;

import net.iqbusiness.app.registrator.domain.converter.UserConverter;
import net.iqbusiness.app.registrator.domain.validator.UserValidator;
import net.iqbusiness.app.registrator.model.dao.UserJpaRepo;
import net.iqbusiness.app.registrator.model.dto.UserDTO;
import net.iqbusiness.app.registrator.model.entity.User;
import net.iqbusiness.commons.util.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserJpaRepo jpaRepo;
    private final UserValidator validator;
    private final UserConverter converter;

    @Autowired
    public UserService(UserJpaRepo jpaRepo, UserValidator validator, UserConverter converter) {
        this.jpaRepo = jpaRepo;
        this.validator = validator;
        this.converter = converter;
    }

    public UserDTO create(UserDTO dto) throws BusinessException {
        validator.validate(dto, false);
        User newUser = jpaRepo.save(converter.toEntity(dto));
        return converter.toDTO(newUser);
    }
}
