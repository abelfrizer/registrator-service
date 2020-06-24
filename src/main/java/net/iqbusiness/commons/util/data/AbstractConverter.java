package net.iqbusiness.commons.util.data;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConverter<ENTITY extends IQEntity, DTO extends IQDTO> implements IQConverter<ENTITY, DTO> {

    public List<ENTITY> toEntityList(List<DTO> dtoList) throws ConverterException {
        List<ENTITY> entities = new ArrayList();
        if (dtoList != null && !dtoList.isEmpty()) {
            dtoList.forEach((dto) -> {
                ENTITY entity = this.toEntity(dto);
                entities.add(entity);
            });
        }
        return entities;
    }

    public List<DTO> toDTOList(List<ENTITY> entities) throws ConverterException {
        List<DTO> dtoList = new ArrayList();
        if (entities != null && !entities.isEmpty()) {
            entities.forEach((entity) -> {
                DTO dto = this.toDTO(entity);
                dtoList.add(dto);
            });
        }
        return dtoList;
    }
}
