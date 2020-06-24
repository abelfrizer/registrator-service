package net.iqbusiness.app.registrator.domain.converter;

import net.iqbusiness.app.registrator.model.dto.UserDTO;
import net.iqbusiness.app.registrator.model.entity.User;
import net.iqbusiness.commons.util.data.AbstractConverter;
import net.iqbusiness.commons.util.data.ConverterException;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConverter<User, UserDTO> {

    @Override
    public User toEntity(UserDTO dto) throws ConverterException {
        User entity = null;
        if(dto!=null){
            entity = new User();
            entity.setFirstName(dto.getFirstName());
            entity.setSurname(dto.getSurname());
            entity.setTelephone(dto.getTelephone());
            entity.setUserIdentity(dto.getIdentity());
            entity.setDateCreated(dto.getDateCreated());
            entity.setUuid(dto.getUuid());
        }
        return entity;
    }

    @Override
    public UserDTO toDTO(User entity) throws ConverterException {
        UserDTO dto = null;
        if(entity!=null){
            dto = new UserDTO();
            dto.setFirstName(entity.getFirstName());
            dto.setSurname(entity.getSurname());
            dto.setTelephone(entity.getTelephone());
            dto.setIdentity(entity.getUserIdentity());
            dto.setDateCreated(entity.getDateCreated());
            dto.setUuid(entity.getUuid());
        }
        return dto;
    }
}
