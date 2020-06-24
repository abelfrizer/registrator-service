package net.iqbusiness.app.registrator.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Setter;
import net.iqbusiness.commons.util.data.IQDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserSearchDTO implements IQDTO {
    private String firstName;
    private String surname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public String getFirstName() {
        return getWildCard(firstName);
    }

    public String getSurname() {
        return getWildCard(surname);
    }

    public Date getStartDate() {
        return getMinDate(startDate);
    }

    public Date getEndDate() {
        return getMaxDate(endDate);
    }

    private String getWildCard(String text) {
        StringBuilder sb = new StringBuilder("%");
        sb.append(StringUtils.trimToEmpty(text).toUpperCase());
        sb.append("%");
        return sb.toString();
    }

    private Date getMinDate(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date == null) {
            //defaults to 100 days ago
            Date hunDaysAgo = DateUtils.addDays(new Date(), -100);
            cal.setTime(hunDaysAgo);
        } else
            cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    private Date getMaxDate(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
}
