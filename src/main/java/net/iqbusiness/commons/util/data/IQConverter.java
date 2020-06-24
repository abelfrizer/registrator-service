package net.iqbusiness.commons.util.data;

import java.util.List;

public interface IQConverter<ENTITY extends IQEntity, DTO extends IQDTO> {
    ENTITY toEntity(DTO dto) throws ConverterException;

    DTO toDTO(ENTITY entity) throws ConverterException;

    List<ENTITY> toEntityList(List<DTO> dtos) throws ConverterException;

    List<DTO> toDTOList(List<ENTITY> entities) throws ConverterException;
}
