package net.iqbusiness.app.registrator.model.entity;

import lombok.Data;
import net.iqbusiness.commons.util.data.IQEntity;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Entity
@Table(name = "USER", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_identity"}),
        @UniqueConstraint(columnNames = {"uuid"})})
@XmlRootElement
public class User implements Serializable, IQEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "first_name", nullable = false, length = 150)
    private String firstName;

    @Basic(optional = false)
    @Column(name = "surname", nullable = false, length = 150)
    private String surname;

    @Basic(optional = false)
    @Column(name = "telephone", nullable = false, length = 15)
    private String telephone;

    @Basic(optional = false)
    @Column(name = "user_identity", nullable = false, length = 45)
    private String userIdentity;

    @Basic(optional = false)
    @Column(name = "date_created", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Basic(optional = false)
    @Column(name = "uuid", nullable = false, updatable = false, length = 36)
    private String uuid;

    @PrePersist
    public void prePersist() {
        this.dateCreated = Calendar.getInstance().getTime();
        if (StringUtils.isBlank(uuid)) {
            this.uuid = UUID.randomUUID().toString();
        }
    }
}
