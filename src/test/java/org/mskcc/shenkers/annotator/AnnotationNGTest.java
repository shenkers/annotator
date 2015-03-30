/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import com.google.inject.name.Names;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author sol
 */
public class AnnotationNGTest {

    public AnnotationNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getPrimaryKey method, of class Annotation.
     */
    @Test
    public void testGetPrimaryKey2() {
        String annotations = "annotations2";
        EntityManager annotationEntityManager = Persistence.createEntityManagerFactory(annotations).createEntityManager();
        AnnotationId id = new AnnotationId();
        id.setUsername("sol");
        id.setSource("abc");
        id.setChr("X");
        id.setS(2);
        id.setE(2);
        id.setNeg(false);
        
        Annotation a = new Annotation(id,"sol", Status.false_pos, "does it work?");
        
        EntityTransaction transaction = annotationEntityManager.getTransaction();
        transaction.begin();
        annotationEntityManager.persist(a);
        transaction.commit();
//        new StandardServiceRegistryBuilder().applySetting(configuration.getProperties()).
//       String annotations = "annotations";
//         String userHomeDir = System.getProperty("user.home", ".");
//        String systemDir = userHomeDir + "/annotator";
// 
//        // Set the db system directory.
//        System.setProperty("derby.system.home", systemDir);
////        EntityManager userEntityManager = Persistence.createEntityManagerFactory(users).createEntityManager();
////        EntityManager sourceEntityManager = Persistence.createEntityManagerFactory(sources).createEntityManager();
//        EntityManager annotationEntityManager = Persistence.createEntityManagerFactory(annotations).createEntityManager();

    }
    
     @Test
    public void testGetPrimaryKey() {
        String annotations = "annotations2";
        EntityManager annotationEntityManager = Persistence.createEntityManagerFactory(annotations).createEntityManager();
        AnnotationId id = new AnnotationId();
        id.setUsername("sol");
        id.setSource("abc");
        id.setChr("X");
        id.setS(2);
        id.setE(2);
        id.setNeg(false);
        
        Annotation find = annotationEntityManager.find(Annotation.class, id);
        EntityTransaction transaction = annotationEntityManager.getTransaction();
        transaction.begin();
        find.setNotes("changed notes");
        annotationEntityManager.persist(find);
        transaction.commit();
//        new StandardServiceRegistryBuilder().applySetting(configuration.getProperties()).
//       String annotations = "annotations";
//         String userHomeDir = System.getProperty("user.home", ".");
//        String systemDir = userHomeDir + "/annotator";
// 
//        // Set the db system directory.
//        System.setProperty("derby.system.home", systemDir);
////        EntityManager userEntityManager = Persistence.createEntityManagerFactory(users).createEntityManager();
////        EntityManager sourceEntityManager = Persistence.createEntityManagerFactory(sources).createEntityManager();
//        EntityManager annotationEntityManager = Persistence.createEntityManagerFactory(annotations).createEntityManager();

    }

}
