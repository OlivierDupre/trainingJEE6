package fr.training.listener;

import fr.training.trainingea.model.Credential;
import fr.training.trainingea.model.CredentialGroup;
import fr.training.trainingea.service.CredentialManagerBeanLocal;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author shuttle
 */
public class PopulateCredentialTablesListener implements ServletContextListener {

    @EJB
    CredentialManagerBeanLocal credentialManagerBean;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Credential toto = credentialManagerBean.findCredential("toto");
        if (toto == null) {
            credentialManagerBean.createCredential("toto", "toto");
        }


        Credential dudu = credentialManagerBean.findCredential("dudu");
        if (dudu == null) {
            credentialManagerBean.createCredential("dudu", "dudu");
        }

        CredentialGroup ce = credentialManagerBean.findCredentialGroup("toto", "ce");
        if (ce == null) {
            credentialManagerBean.createCredentialGroup("toto", "ce");
        }

        CredentialGroup direction = credentialManagerBean.findCredentialGroup("dudu", "direction");
        if (direction == null) {
            credentialManagerBean.createCredentialGroup("dudu", "direction");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context destroyed !");
    }
}
