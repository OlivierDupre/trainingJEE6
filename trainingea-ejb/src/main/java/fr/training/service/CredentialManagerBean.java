/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.training.service;

import fr.training.trainingea.model.Credential;
import fr.training.trainingea.model.CredentialGroup;
import fr.training.trainingea.model.CredentialGroupPK;
import fr.training.trainingea.service.CredentialManagerBeanLocal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author shuttle
 */
@Stateless
@Local(CredentialManagerBeanLocal.class)
public class CredentialManagerBean implements CredentialManagerBeanLocal {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createCredential(String login, String password) {
        Credential credential = new Credential();
        credential.setLogin(login);
//        try {
            credential.setPassword(password);
//            credential.setPassword(new String(MessageDigest.getInstance("MD5").digest(password.getBytes())));
//        } catch (NoSuchAlgorithmException ex) {
//            credential.setPassword(password);
//            Logger.getLogger(CredentialManagerBean.class.getName()).log(Level.SEVERE, null, ex);
//        }

        entityManager.persist(credential);
    }

    @Override
    public Credential findCredential(String login) {
        return entityManager.find(Credential.class, "login");
    }

    @Override
    public void createCredentialGroup(String login, String group) {
        CredentialGroup credentialGroup = new CredentialGroup();
        credentialGroup.setLogin(login);
        credentialGroup.setGroup(group);

        entityManager.persist(credentialGroup);
    }

    @Override
    public CredentialGroup findCredentialGroup(String login, String group) {
        CredentialGroupPK credentialGroupPK = new CredentialGroupPK();
        credentialGroupPK.setLogin(login);
        credentialGroupPK.setGroup(group);

        return entityManager.find(CredentialGroup.class, credentialGroupPK);
    }
}
