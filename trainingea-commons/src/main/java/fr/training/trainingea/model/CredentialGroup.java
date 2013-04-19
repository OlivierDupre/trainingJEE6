package fr.training.trainingea.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author shuttle
 */
@Entity
@IdClass(CredentialGroupPK.class)
@Table(name = "credential_groups")
public class CredentialGroup implements Serializable {

    @Id
    private String login;
    @Id
    @Column(name = "groupName")
    private String group;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
