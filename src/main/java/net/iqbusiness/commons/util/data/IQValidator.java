package net.iqbusiness.commons.util.data;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public abstract class IQValidator<DTO extends IQDTO> {

    protected abstract List<String> doFieldValidation(DTO var1, boolean existingRecord);

    public final synchronized void validate(DTO dto, boolean existingRecord) throws ValidationFailureException {
        if (dto == null) {
            throw (new ValidationFailureException("Failed to process field validations. [dto=" + dto + "]"));
        } else {
            List<String> errors = this.doFieldValidation(dto, existingRecord);
            if (errors != null && !errors.isEmpty()) {
                throw (new ValidationFailureException("Failed to process your request due to validation errors", errors));
            }
        }
    }
}
