package fr.training.trainingea.service;

import fr.training.trainingea.model.Credential;
import fr.training.trainingea.model.CredentialGroup;
import javax.ejb.Local;

/**
 *
 * @author shuttle
 */
@Local
public interface CredentialManagerBeanLocal {

    public void createCredential(String login, String password);

    public Credential findCredential(String login);

    public void createCredentialGroup(String login, String group);

    public CredentialGroup findCredentialGroup(String login, String group);
}
