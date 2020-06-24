package net.iqbusiness.app.registrator.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import net.iqbusiness.commons.util.data.IQDTO;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO implements IQDTO {

    private String firstName;
    private String surname;
    private String telephone;
    private String identity;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;
    private String uuid;
}
