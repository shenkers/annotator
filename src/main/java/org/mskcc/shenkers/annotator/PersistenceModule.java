/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mskcc.shenkers.annotator;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sol
 */
public class PersistenceModule extends AbstractModule {

     @Override
    protected void configure() {
        String users = "users";
        String sources = "sources";
        String annotations = "annotations";
         String userHomeDir = System.getProperty("user.home", ".");
        String systemDir = userHomeDir + "/annotator";
 
        // Set the db system directory.
        System.setProperty("derby.system.home", systemDir);
//        EntityManager userEntityManager = Persistence.createEntityManagerFactory(users).createEntityManager();
//        EntityManager sourceEntityManager = Persistence.createEntityManagerFactory(sources).createEntityManager();
        EntityManager annotationEntityManager = Persistence.createEntityManagerFactory(annotations).createEntityManager();
//        bind(EntityManager.class).annotatedWith(Names.named(users)).toInstance(userEntityManager);
//        bind(EntityManager.class).annotatedWith(Names.named(sources)).toInstance(sourceEntityManager);
        bind(EntityManager.class).annotatedWith(Names.named(annotations)).toInstance(annotationEntityManager);
    }
    
    
}
